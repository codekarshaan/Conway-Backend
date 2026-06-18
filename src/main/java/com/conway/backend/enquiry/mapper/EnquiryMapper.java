package com.conway.backend.enquiry.mapper;

import com.conway.backend.enquiry.dto.response.EnquiryResponse;
import com.conway.backend.enquiry.entity.Enquiry;

public class EnquiryMapper {

    private EnquiryMapper() {
    }

    public static EnquiryResponse toResponse(
            Enquiry enquiry
    ) {

        return EnquiryResponse.builder()
                .id(enquiry.getId())
                .customerName(
                        enquiry.getCustomerName()
                )
                .phoneNumber(
                        enquiry.getPhoneNumber()
                )
                .email(
                        enquiry.getEmail()
                )
                .city(
                        enquiry.getCity().getCityName()
                )
                .truckType(
                        enquiry.getTruckType().getTruckName()
                )
                .cargoType(
                        enquiry.getCargoType().getCargoName()
                )
                .message(
                        enquiry.getMessage()
                )
                .status(
                        enquiry.getStatus().name()
                )
                .assignedAdminId(
                        enquiry.getAssignedAdmin() != null
                                ? enquiry.getAssignedAdmin().getId()
                                : null
                )
                .createdAt(
                        enquiry.getCreatedAt()
                )
                .build();
    }
}