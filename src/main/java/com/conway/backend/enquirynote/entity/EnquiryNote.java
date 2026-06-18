package com.conway.backend.enquirynote.entity;

import com.conway.backend.admin.entity.Admin;
import com.conway.backend.enquiry.entity.Enquiry;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "enquiry_notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnquiryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "enquiry_id",
            nullable = false
    )
    private Enquiry enquiry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "admin_id",
            nullable = false
    )
    private Admin admin;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String note;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}