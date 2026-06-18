package com.conway.backend.audit.service.impl;

import com.conway.backend.audit.dto.response.AuditLogResponse;
import com.conway.backend.audit.entity.AuditLog;
import com.conway.backend.audit.mapper.AuditLogMapper;
import com.conway.backend.audit.repository.AuditLogRepository;
import com.conway.backend.audit.service.AuditLogReadService;
import com.conway.backend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogReadServiceImpl
        implements AuditLogReadService {

    private final AuditLogRepository auditLogRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AuditLogResponse> getAllAuditLogs() {

        return auditLogRepository.findAll()
                .stream()
                .map(AuditLogMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AuditLogResponse getAuditLogById(
            Long id
    ) {

        AuditLog auditLog =
                auditLogRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Audit log not found"
                                )
                        );

        return AuditLogMapper.toResponse(
                auditLog
        );
    }
}