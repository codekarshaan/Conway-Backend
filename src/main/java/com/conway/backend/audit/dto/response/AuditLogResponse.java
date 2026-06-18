package com.conway.backend.audit.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuditLogResponse {

    private Long id;

    private String adminName;

    private String action;

    private String entityName;

    private Long entityId;

    private String description;

    private LocalDateTime createdAt;
}