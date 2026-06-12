package com.dumplydesk.shared.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.dumplydesk.modules.users.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class GenerateTokenService {

    @Value("${token.secret}")
    private String secret;

    //Geração do token JWT, com claim da ROLE
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-login")
                    .withSubject(user.getEmail())
                    .withClaim("role", user.getRole().name())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao autenticar", exception);
        }
    }

    // Método da geração da expiração do token JWT
    public Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.ofHours(-3));
    }
}
