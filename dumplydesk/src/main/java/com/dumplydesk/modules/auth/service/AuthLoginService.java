package com.dumplydesk.modules.auth.service;

import com.dumplydesk.modules.auth.dto.request.LoginRequest;
import com.dumplydesk.modules.auth.dto.response.LoginResponse;
import com.dumplydesk.modules.users.domain.User;
import com.dumplydesk.modules.users.repository.UserRepository;
import com.dumplydesk.shared.security.token.GenerateTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenerateTokenService generateTokenService;

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email()).orElse(null);

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("Credenciais inválidas.");
        }

        return new LoginResponse(generateTokenService.generateToken(user));

    }
}
