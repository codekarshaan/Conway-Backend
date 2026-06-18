package com.conway.backend.audit.service;

import com.conway.backend.admin.entity.Admin;
import com.conway.backend.audit.dto.response.AuditLogResponse;

import java.util.List;

public interface AuditLogService {

    void log(
            Admin admin,
            String action,
            String entityName,
            Long entityId,
            String description
    );

    List<AuditLogResponse> getAuditLogs();
}