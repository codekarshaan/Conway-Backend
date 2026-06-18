package com.conway.backend.admin.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminResponse {

    private Long id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String role;

    private Boolean isActive;
}