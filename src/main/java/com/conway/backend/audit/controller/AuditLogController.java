package com.conway.backend.audit.controller;

import com.conway.backend.audit.dto.response.AuditLogResponse;
import com.conway.backend.audit.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogService
            auditLogService;

    @GetMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public List<AuditLogResponse>
    getAuditLogs() {

        return auditLogService
                .getAuditLogs();
    }
}