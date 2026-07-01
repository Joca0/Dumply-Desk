package com.dumplydesk.modules.auth.controller;

import com.dumplydesk.modules.auth.dto.request.LoginRequest;
import com.dumplydesk.modules.auth.dto.response.LoggedUserResponse;
import com.dumplydesk.modules.auth.dto.response.LoginResponse;
import com.dumplydesk.modules.auth.interfaces.AuthInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthInterface authInterface;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        return ResponseEntity.ok(authInterface.login(loginRequest));
    };

    @GetMapping("/profile")
    public ResponseEntity<LoggedUserResponse> loggedUser() {
        return ResponseEntity.ok(authInterface.loggedUser());
    };
}
