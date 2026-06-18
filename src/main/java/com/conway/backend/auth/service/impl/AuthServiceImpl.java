package com.conway.backend.auth.service.impl;

import com.conway.backend.admin.entity.Admin;
import com.conway.backend.admin.repository.AdminRepository;
import com.conway.backend.auth.dto.request.LoginRequest;
import com.conway.backend.auth.dto.response.CurrentUserResponse;
import com.conway.backend.auth.dto.response.LoginResponse;
import com.conway.backend.auth.service.AuthService;
import com.conway.backend.loginhistory.service.LoginHistoryService;
import com.conway.backend.security.jwt.JwtService;
import com.conway.backend.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final AdminRepository adminRepository;

    private final LoginHistoryService loginHistoryService;

    @Override
    public LoginResponse login(
            LoginRequest request,
            String ipAddress,
            String userAgent
    ) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Admin admin = adminRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("Admin not found")
                );
        loginHistoryService.recordLogin(
                admin,
                ipAddress,
                userAgent
        );

        String token = jwtService.generateToken(admin.getEmail(),admin.getRole().getRoleName());

        return LoginResponse.builder()
                .token(token)
                .type("Bearer")
                .email(admin.getEmail())
                .fullName(admin.getFullName())
                .role(admin.getRole().getRoleName())
                .build();
    }
    @Override
    public CurrentUserResponse getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (
                authentication == null ||
                        !authentication.isAuthenticated()
        ) {
            throw new UsernameNotFoundException(
                    "User not authenticated"
            );
        }

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        Admin admin = userDetails.getAdmin();

        return CurrentUserResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .fullName(admin.getFullName())
                .role(admin.getRole().getRoleName())
                .isActive(admin.getIsActive())
                .build();
    }
}