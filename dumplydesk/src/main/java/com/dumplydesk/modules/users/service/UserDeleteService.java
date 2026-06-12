package com.dumplydesk.modules.users.service;

import com.dumplydesk.modules.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDeleteService {

    private final UserRepository userRepository;


    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        userRepository.deleteById(userId);
    }

}
