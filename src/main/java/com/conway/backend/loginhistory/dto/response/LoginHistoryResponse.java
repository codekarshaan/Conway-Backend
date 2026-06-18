package com.conway.backend.loginhistory.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LoginHistoryResponse {

    private Long id;

    private Long adminId;

    private String adminName;

    private String email;

    private LocalDateTime loginTime;

    private String ipAddress;

    private String userAgent;
}