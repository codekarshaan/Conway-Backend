package com.conway.backend.admin.mapper;

import com.conway.backend.admin.dto.response.AdminResponse;
import com.conway.backend.admin.entity.Admin;

public class AdminMapper {

    private AdminMapper() {}

    public static AdminResponse toResponse(
            Admin admin
    ) {

        return AdminResponse.builder()
                .id(admin.getId())
                .fullName(admin.getFullName())
                .email(admin.getEmail())
                .phoneNumber(admin.getPhoneNumber())
                .role(admin.getRole().getRoleName())
                .isActive(admin.getIsActive())
                .build();
    }
}