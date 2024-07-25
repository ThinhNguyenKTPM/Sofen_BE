package com.thesis.sofen.repositories;

import com.thesis.sofen.common.ETokenType;
import com.thesis.sofen.entities.Token;
import com.thesis.sofen.entities.User;
import com.thesis.sofen.request.auth.LogoutRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> getTokenByUserAndTokenType(@Param("user") User user, @Param("tokenType") ETokenType tokenType);

    @Modifying
    @Transactional
    void deleteByUserAndTokenType(@Param("user") User user, @Param("tokenType") ETokenType tokenType);

    @Query("SELECT t FROM Token t WHERE t.token = :token AND t.user.email = :email")
    Optional<Token> findByTokenAndEmail(@Param("token") String token, @Param("email") String email);


    Optional<Token> findByToken(@Param("token") String token);

    @Modifying
    @Transactional
    void deleteByToken(@Param("token") String token);

    @Modifying
    @Transactional
    @Query("DELETE FROM Token t WHERE t.user.email = :email AND t.tokenType = :tokenType")
    void deleteByTokenByEmailAndTokenType(String email, ETokenType tokenType);
}
