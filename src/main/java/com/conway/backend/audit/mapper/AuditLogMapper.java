package com.conway.backend.audit.mapper;

import com.conway.backend.audit.dto.response.AuditLogResponse;
import com.conway.backend.audit.entity.AuditLog;

public class AuditLogMapper {

    private AuditLogMapper() {
    }

    public static AuditLogResponse toResponse(
            AuditLog auditLog
    ) {

        return AuditLogResponse.builder()
                .id(auditLog.getId())
                .adminName(
                        auditLog.getAdmin()
                                .getFullName()
                )
                .action(auditLog.getAction())
                .entityName(auditLog.getEntityName())
                .entityId(auditLog.getEntityId())
                .description(auditLog.getDescription())
                .createdAt(auditLog.getCreatedAt())
                .build();
    }
}