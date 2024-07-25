package com.thesis.sofen.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtService {
    @Value("${app.jwt_secret}")
    private String jwtSecret;

    @Value("${app.jwt_expiration_ms}")
    private Long jwtExpirationMs;

    public Long getJwtExpirationMs() {
        return jwtExpirationMs;
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        final String requestHeader = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(requestHeader) && requestHeader.startsWith("Bearer")) {
            return requestHeader.replace("Bearer ", "");
        } else {
            return null;
        }
    }
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String generateTokenFromUsername(String username) {
        Date issueAt = new Date(System.currentTimeMillis());
        Date expired = new Date(System.currentTimeMillis() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(issueAt)
                .setExpiration(expired)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
}
