package com.conway.backend.admin.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAdminRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String roleName;

}