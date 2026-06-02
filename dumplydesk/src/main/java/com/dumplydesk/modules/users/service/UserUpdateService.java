package com.dumplydesk.modules.users.service;

import com.dumplydesk.modules.users.domain.User;
import com.dumplydesk.modules.users.dto.request.UpdateUserRequest;
import com.dumplydesk.modules.users.dto.response.UserUpdateResponse;
import com.dumplydesk.modules.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserUpdateService {

    private final UserRepository userRepository;

    public UserUpdateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NullMarked
    public UserUpdateResponse updateUser(UUID id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        user.setName(request.name());
        user.setEmail(request.email());
        user.setDocument(request.document());
        user.setPassword(request.password());
        userRepository.save(user);
        return new UserUpdateResponse("Usuário atualizado com sucesso!");
    }
}
