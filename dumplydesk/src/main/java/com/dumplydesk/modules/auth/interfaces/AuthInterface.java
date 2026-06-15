package com.dumplydesk.modules.auth.interfaces;

import com.dumplydesk.modules.auth.dto.request.LoginRequest;
import com.dumplydesk.modules.auth.dto.response.LoggedUserResponse;
import com.dumplydesk.modules.auth.dto.response.LoginResponse;

public interface AuthInterface {
    LoginResponse login(LoginRequest loginRequest);
    LoggedUserResponse loggedUser();
}
