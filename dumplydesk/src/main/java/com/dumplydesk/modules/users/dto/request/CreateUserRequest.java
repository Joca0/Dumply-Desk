package com.dumplydesk.modules.users.dto.request;

import com.dumplydesk.shared.enums.Role;

public record CreateUserRequest(
        String name,
        String email,
        String document,
        String password,
        Role role
) {
}
