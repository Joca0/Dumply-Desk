package com.dumplydesk.modules.users.interfaces;

import com.dumplydesk.modules.users.dto.request.CreateUserRequest;
import com.dumplydesk.modules.users.dto.request.UpdateUserRequest;
import com.dumplydesk.modules.users.dto.response.CreateUserResponse;
import com.dumplydesk.modules.users.dto.response.UserListResponse;
import com.dumplydesk.modules.users.dto.response.UserUpdateResponse;

import java.util.List;
import java.util.UUID;

public interface UserInterface {
    CreateUserResponse createUser(CreateUserRequest request);
    List<UserListResponse> listUsers();
    UserListResponse getUserById(UUID id);
    UserUpdateResponse updateUser(UUID id, UpdateUserRequest request);
    void deleteUser(UUID id);
}