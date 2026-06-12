package com.dumplydesk.modules.users.service;

import com.dumplydesk.modules.users.domain.User;
import com.dumplydesk.modules.users.dto.request.CreateUserRequest;
import com.dumplydesk.modules.users.dto.response.CreateUserResponse;
import com.dumplydesk.modules.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserCreationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEnconder;

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {
        //Valida se existe um email cadastrado antes de criar uma conta nova
        if (userRepository.existsByEmail(request.email())) {
            throw new EntityNotFoundException("Email já cadastrado");
        }

        User user = new User();
        user.setName(request.name());

        //Padroniza o email para lower case
        user.setEmail(request.email().toLowerCase());
        user.setDocument(request.document());
        user.setPassword(passwordEnconder.encode(request.password()));
        user.setRole(request.role());
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return new CreateUserResponse("Usuário criado com sucesso!");
    }
}
