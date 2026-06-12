package com.dumplydesk.modules.users.implement;

import com.dumplydesk.modules.users.dto.request.CreateUserRequest;
import com.dumplydesk.modules.users.dto.request.UpdateUserRequest;
import com.dumplydesk.modules.users.dto.response.CreateUserResponse;
import com.dumplydesk.modules.users.dto.response.UserListResponse;
import com.dumplydesk.modules.users.dto.response.UserUpdateResponse;
import com.dumplydesk.modules.users.interfaces.UserInterface;
import com.dumplydesk.modules.users.service.UserCreationService;
import com.dumplydesk.modules.users.service.UserDeleteService;
import com.dumplydesk.modules.users.service.UserListService;
import com.dumplydesk.modules.users.service.UserUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserImplService implements UserInterface {

    private final UserCreationService userCreationService;
    private final UserListService userListService;
    private final UserUpdateService userUpdateService;
    private final UserDeleteService userDeleteService;

    public CreateUserResponse createUser(CreateUserRequest request) {
        return userCreationService.createUser(request);
    }

    public List<UserListResponse> listUsers() {
        return userListService.listUsers();
    }

    public UserListResponse getUserById(UUID id) {
        return userListService.getUserById(id);
    }

    public UserUpdateResponse updateUser(UUID id, UpdateUserRequest request) {
        return userUpdateService.updateUser(id, request);
    }

    public void deleteUser(UUID id) {
        userDeleteService.deleteUser(id);
    }
}