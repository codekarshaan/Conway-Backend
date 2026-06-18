package com.conway.backend.dashboard.service.impl;

import com.conway.backend.dashboard.dto.response.DashboardResponse;
import com.conway.backend.dashboard.dto.response.RecentEnquiryResponse;
import com.conway.backend.dashboard.service.DashboardService;
import com.conway.backend.enquiry.entity.EnquiryStatus;
import com.conway.backend.enquiry.repository.EnquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
        implements DashboardService {

    private final EnquiryRepository enquiryRepository;

    @Override
    @Transactional(readOnly = true)
    public DashboardResponse getDashboard() {

        return DashboardResponse.builder()

                .totalEnquiries(
                        enquiryRepository.count()
                )

                .newEnquiries(
                        enquiryRepository.countByStatus(
                                EnquiryStatus.NEW
                        )
                )

                .contactedEnquiries(
                        enquiryRepository.countByStatus(
                                EnquiryStatus.CONTACTED
                        )
                )

                .inProgressEnquiries(
                        enquiryRepository.countByStatus(
                                EnquiryStatus.IN_PROGRESS
                        )
                )

                .closedEnquiries(
                        enquiryRepository.countByStatus(
                                EnquiryStatus.CLOSED
                        )
                )

                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecentEnquiryResponse>
    getRecentEnquiries() {

        return enquiryRepository
                .findTop5ByOrderByCreatedAtDesc()
                .stream()
                .map(enquiry ->
                        RecentEnquiryResponse.builder()
                                .id(
                                        enquiry.getId()
                                )
                                .customerName(
                                        enquiry.getCustomerName()
                                )
                                .phoneNumber(
                                        enquiry.getPhoneNumber()
                                )
                                .status(
                                        enquiry.getStatus().name()
                                )
                                .createdAt(
                                        enquiry.getCreatedAt()
                                )
                                .build()
                )
                .toList();
    }
}