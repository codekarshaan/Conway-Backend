package com.conway.backend.audit.service.impl;

import com.conway.backend.admin.entity.Admin;
import com.conway.backend.audit.dto.response.AuditLogResponse;
import com.conway.backend.audit.entity.AuditLog;
import com.conway.backend.audit.repository.AuditLogRepository;
import com.conway.backend.audit.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl
        implements AuditLogService {

    private final AuditLogRepository
            auditLogRepository;

    @Override
    @Transactional
    public void log(
            Admin admin,
            String action,
            String entityName,
            Long entityId,
            String description
    ) {

        AuditLog auditLog =
                AuditLog.builder()
                        .admin(admin)
                        .action(action)
                        .entityName(entityName)
                        .entityId(entityId)
                        .description(description)
                        .createdAt(LocalDateTime.now())
                        .build();

        auditLogRepository.save(auditLog);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditLogResponse> getAuditLogs() {

        return auditLogRepository
                .findAllByOrderByCreatedAtDesc()
                .stream()
                .map(log ->
                        AuditLogResponse.builder()
                                .id(log.getId())
                                .adminName(
                                        log.getAdmin()
                                                .getFullName()
                                )
                                .action(
                                        log.getAction()
                                )
                                .entityName(
                                        log.getEntityName()
                                )
                                .entityId(
                                        log.getEntityId()
                                )
                                .description(
                                        log.getDescription()
                                )
                                .createdAt(
                                        log.getCreatedAt()
                                )
                                .build()
                )
                .toList();
    }
}