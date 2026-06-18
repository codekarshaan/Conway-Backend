package com.conway.backend.audit.entity;

import com.conway.backend.admin.entity.Admin;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "admin_id",
            nullable = false
    )
    private Admin admin;

    @Column(nullable = false)
    private String action;

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}