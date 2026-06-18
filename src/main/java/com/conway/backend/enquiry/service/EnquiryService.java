package com.conway.backend.enquiry.service;

import com.conway.backend.enquiry.dto.request.CreateEnquiryRequest;
import com.conway.backend.enquiry.dto.request.UpdateEnquiryStatusRequest;
import com.conway.backend.enquiry.dto.response.EnquiryResponse;
import org.springframework.data.domain.Page;

public interface EnquiryService {

    EnquiryResponse createEnquiry(
            CreateEnquiryRequest request
    );

    Page<EnquiryResponse> getAllEnquiries(
            String status,
            String search,
            int page,
            int size
    );

    EnquiryResponse getEnquiryById(
            Long id
    );

    EnquiryResponse updateStatus(
            Long id,
            UpdateEnquiryStatusRequest request
    );
}