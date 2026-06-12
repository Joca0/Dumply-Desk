package com.dumplydesk.shared.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dumplydesk.modules.users.domain.User;
import com.dumplydesk.modules.users.repository.UserRepository;
import com.dumplydesk.shared.security.token.ValidateTokenService;
import com.zaxxer.hikari.HikariConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final ValidateTokenService validateTokenService;

    public SecurityFilter(UserRepository userRepository, ValidateTokenService validateTokenService) {
        this.userRepository = userRepository;
        this.validateTokenService = validateTokenService;
    }

    //Filtro de segurança
    //Todo request passa por
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = recoverToken(request);
        try {
            var decodedJWT = validateTokenService.validateToken(token);
            User user = new User();

            if (decodedJWT == null) {
                writeInvalidTokenResponse(response);
                return;
            }
            //Claim da role via JWT
            String role = decodedJWT.getClaim("role").asString();

            var authorities = Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_" + role)
            );

            var userDetails =
                    new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

            var authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, token, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JWTVerificationException ex) {
            //TOKEN INVÁLIDO OU EXPIRADO
            writeInvalidTokenResponse(response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    //Captação do token via header
    public String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.substring(7);
    }

    //Retorno de erro para token inválido ou expirado
    private void writeInvalidTokenResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        Map<String, String> error = new HashMap<>();
        error.put("message", "Token inválido ou expirado.");
        response.getWriter().write(error.toString());
    }
}
