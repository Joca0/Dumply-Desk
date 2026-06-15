package com.dumplydesk.modules.auth.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
