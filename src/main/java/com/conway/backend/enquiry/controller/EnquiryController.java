package com.conway.backend.enquiry.controller;

import com.conway.backend.enquiry.dto.request.CreateEnquiryRequest;
import com.conway.backend.enquiry.dto.response.EnquiryResponse;
import com.conway.backend.enquiry.service.EnquiryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import com.conway.backend.enquiry.dto.request.UpdateEnquiryStatusRequest;
import java.util.List;

@RestController
@RequestMapping("/api/enquiries")
@RequiredArgsConstructor
public class EnquiryController {

    private final EnquiryService enquiryService;

    @PostMapping
    public EnquiryResponse createEnquiry(
            @Valid
            @RequestBody
            CreateEnquiryRequest request
    ) {

        return enquiryService
                .createEnquiry(request);
    }

    @GetMapping
    public Page<EnquiryResponse>
    getAllEnquiries(

            @RequestParam(
                    required = false
            )
            String status,

            @RequestParam(
                    required = false
            )
            String search,

            @RequestParam(
                    defaultValue = "0"
            )
            int page,

            @RequestParam(
                    defaultValue = "10"
            )
            int size

    ) {

        return enquiryService
                .getAllEnquiries(
                        status,
                        search,
                        page,
                        size
                );
    }

    @GetMapping("/{id}")
    public EnquiryResponse
    getEnquiryById(
            @PathVariable Long id
    ) {

        return enquiryService
                .getEnquiryById(id);
    }
    @PatchMapping("/{id}/status")
    public EnquiryResponse updateStatus(
            @PathVariable Long id,
            @Valid
            @RequestBody
            UpdateEnquiryStatusRequest request
    ) {

        return enquiryService.updateStatus(
                id,
                request
        );
    }
}