package com.conway.backend.loginhistory.entity;

import com.conway.backend.admin.entity.Admin;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "login_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "admin_id",
            nullable = false
    )
    private Admin admin;

    @Column(name = "login_time")
    private LocalDateTime loginTime;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;
}