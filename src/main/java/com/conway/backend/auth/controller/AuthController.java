package com.conway.backend.auth.controller;

import com.conway.backend.auth.dto.request.LoginRequest;
import com.conway.backend.auth.dto.response.CurrentUserResponse;
import com.conway.backend.auth.dto.response.LoginResponse;
import com.conway.backend.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest requestHttp
    ){

        LoginResponse response =
                authService.login(
                        request,
                        requestHttp.getRemoteAddr(),
                        requestHttp.getHeader("User-Agent")
                );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/me")
    public ResponseEntity<CurrentUserResponse> getCurrentUser() {

        return ResponseEntity.ok(
                authService.getCurrentUser()
        );

    }
}