package com.conway.backend.admin.entity;

import com.conway.backend.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="full_name",nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name="password_hash",nullable = false)
    private String passwordHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id",nullable = false)
    private Role role;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name="last_login")
    private LocalDateTime lastLogin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private Admin updatedBy;

    @Column(name = "status_changed_at")
    private LocalDateTime statusChangedAt;
}