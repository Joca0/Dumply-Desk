package com.dumplydesk.modules.users.dto.request;

public record UpdateUserRequest(
        String name,
        String email,
        String document,
        String password
) {
}
