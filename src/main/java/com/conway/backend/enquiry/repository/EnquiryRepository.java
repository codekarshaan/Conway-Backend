package com.conway.backend.enquiry.repository;

import com.conway.backend.enquiry.entity.Enquiry;
import com.conway.backend.enquiry.entity.EnquiryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnquiryRepository
        extends JpaRepository<Enquiry, Long> {

    @Override
    @EntityGraph(attributePaths = {
            "city",
            "truckType",
            "cargoType",
            "assignedAdmin"
    })
    Page<Enquiry> findAll(
            Pageable pageable
    );

    @EntityGraph(attributePaths = {
            "city",
            "truckType",
            "cargoType",
            "assignedAdmin"
    })
    Page<Enquiry> findByStatus(
            EnquiryStatus status,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {
            "city",
            "truckType",
            "cargoType",
            "assignedAdmin"
    })
    Page<Enquiry> findByCustomerNameContainingIgnoreCase(
            String customerName,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {
            "city",
            "truckType",
            "cargoType",
            "assignedAdmin"
    })
    Page<Enquiry> findByStatusAndCustomerNameContainingIgnoreCase(
            EnquiryStatus status,
            String customerName,
            Pageable pageable
    );

    long countByStatus(
            EnquiryStatus status
    );

    java.util.List<Enquiry>
    findTop5ByOrderByCreatedAtDesc();
}