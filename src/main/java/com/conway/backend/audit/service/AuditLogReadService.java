package com.conway.backend.audit.service;

import com.conway.backend.audit.dto.response.AuditLogResponse;

import java.util.List;

public interface AuditLogReadService {

    List<AuditLogResponse> getAllAuditLogs();

    AuditLogResponse getAuditLogById(Long id);
}