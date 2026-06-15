package com.dumplydesk.modules.auth.service;

import com.dumplydesk.modules.auth.dto.response.LoggedUserResponse;
import com.dumplydesk.modules.users.domain.User;
import com.dumplydesk.modules.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthLoggedUserService {

    private final UserRepository userRepository;

    //Pegar contexto do usuário autenticado no momento
    public User getAuthenticatedUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
    }

    //Retorna informações do usuário autenticado
    public LoggedUserResponse getLoggedUser() {
        User user = getAuthenticatedUser();
        return new LoggedUserResponse(
                user.getName(),
                user.getEmail(),
                user.getDocument(),
                user.getRole()
        );
    }
}
