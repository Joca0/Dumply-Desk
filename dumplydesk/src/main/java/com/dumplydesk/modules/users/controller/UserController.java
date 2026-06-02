package com.dumplydesk.modules.users.controller;

import com.dumplydesk.modules.users.dto.request.CreateUserRequest;
import com.dumplydesk.modules.users.dto.request.UpdateUserRequest;
import com.dumplydesk.modules.users.dto.response.CreateUserResponse;
import com.dumplydesk.modules.users.dto.response.UserListResponse;
import com.dumplydesk.modules.users.dto.response.UserUpdateResponse;
import com.dumplydesk.modules.users.interfaces.UserInterface; // Import da interface
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    //Injeção da interface
    private final UserInterface userInterface;

    //Criação de usuário
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userInterface.createUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserUpdateResponse> updateUser(@PathVariable UUID id, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userInterface.updateUser(id, request));
    }

    //Listagem de usuários
    @GetMapping
    public ResponseEntity<List<UserListResponse>> listUsers() {
        return ResponseEntity.ok(userInterface.listUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserListResponse> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userInterface.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userInterface.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}