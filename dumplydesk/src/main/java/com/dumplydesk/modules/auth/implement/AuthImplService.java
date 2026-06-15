package com.dumplydesk.modules.auth.implement;

import com.dumplydesk.modules.auth.dto.request.LoginRequest;
import com.dumplydesk.modules.auth.dto.response.LoggedUserResponse;
import com.dumplydesk.modules.auth.dto.response.LoginResponse;
import com.dumplydesk.modules.auth.interfaces.AuthInterface;
import com.dumplydesk.modules.auth.service.AuthLoggedUserService;
import com.dumplydesk.modules.auth.service.AuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthImplService implements AuthInterface {

    private final AuthLoginService authLoginService;
    private final AuthLoggedUserService authLoggedUserService;


    public LoginResponse login(LoginRequest loginRequest) {
        return authLoginService.login(loginRequest);
    }

    public LoggedUserResponse loggedUser() {
        return authLoggedUserService.getLoggedUser();
    }
}
