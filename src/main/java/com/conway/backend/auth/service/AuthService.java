package com.conway.backend.auth.service;

import com.conway.backend.auth.dto.request.LoginRequest;
import com.conway.backend.auth.dto.response.CurrentUserResponse;
import com.conway.backend.auth.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(
            LoginRequest request,
            String ipAddress,
            String userAgent
    );

    CurrentUserResponse getCurrentUser();

}