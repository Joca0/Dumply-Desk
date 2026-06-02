package com.dumplydesk.modules.users.dto.response;

public record UserListResponse(
        String username,
        String document,
        String email
) {
}
