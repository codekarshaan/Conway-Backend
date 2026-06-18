package com.conway.backend.enquiry.entity;

import com.conway.backend.admin.entity.Admin;
import com.conway.backend.common.entity.BaseEntity;
import com.conway.backend.master.entity.CargoType;
import com.conway.backend.master.entity.City;
import com.conway.backend.master.entity.TruckType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enquiries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "customer_name",
            nullable = false
    )
    private String customerName;

    @Column(
            name = "phone_number",
            nullable = false
    )
    private String phoneNumber;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "city_id",
            nullable = false
    )
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "truck_type_id",
            nullable = false
    )
    private TruckType truckType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "cargo_type_id",
            nullable = false
    )
    private CargoType cargoType;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnquiryStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_admin_id")
    private Admin assignedAdmin;
}