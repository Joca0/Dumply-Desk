package com.dumplydesk.modules.auth.dto.response;

import com.dumplydesk.shared.enums.Role;

public record LoggedUserResponse(
        String name,
        String email,
        String document,
        Role role
) {
}
