package com.dumplydesk.modules.users.service;

import com.dumplydesk.modules.users.domain.User;
import com.dumplydesk.modules.users.dto.response.UserListResponse;
import com.dumplydesk.modules.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserListService {

    private final UserRepository userRepository;


    public List<UserListResponse> listUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserListResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado. "));
        return mapToResponse(user);
    }

    private UserListResponse mapToResponse(User user) {
        return new UserListResponse(
                user.getName(),
                user.getDocument(),
                user.getEmail()
        );
    }
}
