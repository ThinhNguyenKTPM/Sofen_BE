package com.thesis.sofen.services.auth;

import com.thesis.sofen.common.ERole;
import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.common.ETokenType;
import com.thesis.sofen.common.EUserRanking;
import com.thesis.sofen.constant.AppConstant;
import com.thesis.sofen.entities.Role;
import com.thesis.sofen.entities.Token;
import com.thesis.sofen.entities.User;
import com.thesis.sofen.entities.UserRanking.UserRanking;
import com.thesis.sofen.exception.*;
import com.thesis.sofen.mapper.TokenMapper;
import com.thesis.sofen.repositories.RoleRepository;
import com.thesis.sofen.repositories.TokenRepository;
import com.thesis.sofen.repositories.UserRankingRepository;
import com.thesis.sofen.repositories.UserRepository;
import com.thesis.sofen.request.auth.*;
import com.thesis.sofen.response.Auth.LoginResponse;
import com.thesis.sofen.response.Auth.RefreshTokenResponse;
import com.thesis.sofen.response.Auth.TokenService;
import com.thesis.sofen.response.SuccessResponse;
import com.thesis.sofen.services.EmailService;
import com.thesis.sofen.services.JwtService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AppConstant appConstant;
    private final EmailService emailService;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepo;
    private final RoleRepository roleRepo;
    private final UserRankingRepository userRankingRepo;
    private final JwtService jwtUtils;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public User register(RegisterRequest request) throws DuplicateFieldException, NotAcceptableException {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new DuplicateFieldException("email_already_exists", "Email already exists");
        }
        Role role = roleRepo.findByRole(ERole.ROLE_USER);
        UserRanking userRanking = userRankingRepo.findByName(EUserRanking.Bronze);
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneCode(request.getPhoneCode());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAvatar(appConstant.getDefaultAvatar());
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus(EUserStatus.INACTIVE);
        user.setRole(role);
        user.setUserRanking(userRanking);
        userRepo.save(user);

        String otp = generateOTP(user, false);
        emailService.sendValidateEmail(user.getEmail(), otp);
        return user;
    }

    public String generateOTP(User user, Boolean isLogin) throws NotAcceptableException {
        final LocalDateTime now = LocalDateTime.now();
        final Integer validateTimeLimit = appConstant.getValidateTimeLimit();
        LocalDateTime expiredAt = now.plusMinutes(5);
        List<Token> tokens = tokenRepo.getTokenByUserAndTokenType(user, ETokenType.VERIFY_EMAIL_TOKEN);


        for (var token : tokens) {
            token.setRevoked(true);
            tokenRepo.save(token);
        }

        List<String> existingTokens = tokens.stream().map(Token::getToken).toList();

        Random random = new Random();
        String otp;
        do {
            otp = String.format("%06d", random.nextInt(1000000));
        } while (existingTokens.contains(otp));

        Token tokenEntity = new Token();
        tokenEntity.setToken(otp);
        tokenEntity.setTokenType(ETokenType.VERIFY_EMAIL_TOKEN);
        tokenEntity.setExpiredAt(expiredAt);
        tokenEntity.setCreatedAt(now);
        tokenEntity.setRevoked(false);
        tokenEntity.setUser(user);

        tokenRepo.save(tokenEntity);
        return otp;
    }

    /**
     * Login
     *
     * @param request LoginRequest
     * @return LoginResponse
     * @throws AccountLoginException  if account is inactive
     * @throws NotAcceptableException if token already exists
     */
    public LoginResponse login(LoginRequest request) throws AccountLoginException, NotAcceptableException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        User account = userRepo.findByEmail(user.getEmail()).get();
        if (EUserStatus.INACTIVE.equals(account.getStatus())) {
            String otp = generateOTP(account, true);
            emailService.sendValidateEmail(account.getEmail(), otp);
            throw new AccountLoginException("user.inactive", "inactive user, check your email");
        }
        if (EUserStatus.DEACTIVATED.equals(account.getStatus())) {
            throw new AccountLoginException("user.deactivate", "deactivate account");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String accessToken = jwtUtils.generateTokenFromUsername(user.getUsername());
        Token token = TokenMapper.INSTANCE.toEntity(account, accessToken, ETokenType.ACCESS_TOKEN,
                jwtUtils.getJwtExpirationMs());

        Token savedToken = tokenRepo.save(token);
        RefreshTokenResponse createdRefreshToken = tokenService.createRefreshToken(user.getEmail());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(createdRefreshToken.getRefreshToken())
                .iat(LocalDateTime.now())
                .ext(LocalDateTime.now().plusSeconds(jwtUtils.getJwtExpirationMs()))
                .build();
    }

    public SuccessResponse validate(@Valid ValidateRequest request) throws ResourceNotFoundException {
        Optional<Token> token = Optional.ofNullable(tokenRepo.findByTokenAndEmail(request.getOtp(), request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("otp.not.found", "OTP not found")));
        System.out.println(LocalDateTime.now().isAfter(token.get().getExpiredAt()));
        if (token.isEmpty() ||
            !request.getOtp().equals(token.get().getToken()) ||
            LocalDateTime.now().isAfter(token.get().getExpiredAt()) ||
            token.get().isRevoked()
        ) {
            throw new ResourceNotFoundException("otp.is.invalid", "OTP is invalid");
        }
        Optional<User> account = userRepo.findByEmail(request.getEmail());
        if (EUserStatus.INACTIVE.equals(account.get().getStatus())) {
            account.get().setStatus(EUserStatus.ACTIVE);
        }
        tokenRepo.delete(token.get());
        return new SuccessResponse("User validated successfully");
    }

    /**
     * Change password
     *
     * @param request ChangePasswordRequest
     * @return SuccessResponse
     */
    public SuccessResponse changePassword(ChangePasswordRequest request) {
        User account = userRepo.findByEmail(request.getEmail()).get();
        if (request.getPassword().equals(request.getNewPassword())) {
            account.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepo.save(account);
            return new SuccessResponse("Password changed successfully");
        }
        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new UnAuthorizedException("password.not.match", "Password not match");
        }
        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepo.save(account);
        return new SuccessResponse("Password changed successfully");
    }

    public SuccessResponse forgotPassword(ValidateRequest request) throws UnAuthorizedException, NotAcceptableException {
        Optional<User> account = userRepo.findByEmail(request.getEmail());
        if (account.isEmpty()) {
            throw new UnAuthorizedException("user.not.found", "User not found");
        }
        String otp = generateOTP(account.get(), true);
        emailService.sendValidateEmail(account.get().getEmail(), otp);
        return new SuccessResponse("Password reset code sent successfully");
    }

    public LoginResponse generateRefreshToken(RefreshTokenRequest request) throws UnAuthorizedException {
        Token refreshToken = tokenRepo.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new UnAuthorizedException("token.not.found", "Token not found"));
        if (LocalDateTime.now().isAfter(refreshToken.getExpiredAt())) {
            tokenRepo.deleteById(refreshToken.getId());
            throw new RuntimeException(refreshToken.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        User account = refreshToken.getUser();
        String accessToken = jwtUtils.generateTokenFromUsername(account.getEmail());
        refreshToken = tokenService.update(refreshToken);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .iat(LocalDateTime.now())
                .ext(LocalDateTime.now().plusSeconds(jwtUtils.getJwtExpirationMs()))
                .build();
    }

    public SuccessResponse logout(LogoutRequest request) {
        tokenRepo.deleteByTokenByEmailAndTokenType(request.getEmail(), ETokenType.ACCESS_TOKEN);
        tokenRepo.deleteByTokenByEmailAndTokenType(request.getEmail(), ETokenType.REFRESH_TOKEN);

        return new SuccessResponse("Logout successfully");
    }
}
