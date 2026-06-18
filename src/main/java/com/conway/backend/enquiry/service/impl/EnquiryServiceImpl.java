package com.conway.backend.enquiry.service.impl;

import com.conway.backend.enquiry.dto.request.CreateEnquiryRequest;
import com.conway.backend.enquiry.dto.response.EnquiryResponse;
import com.conway.backend.enquiry.entity.Enquiry;
import com.conway.backend.enquiry.entity.EnquiryStatus;
import com.conway.backend.enquiry.mapper.EnquiryMapper;
import com.conway.backend.enquiry.repository.EnquiryRepository;
import com.conway.backend.enquiry.service.EnquiryService;
import com.conway.backend.exception.BadRequestException;
import com.conway.backend.exception.ResourceNotFoundException;
import com.conway.backend.master.entity.CargoType;
import com.conway.backend.master.entity.City;
import com.conway.backend.master.entity.TruckType;
import com.conway.backend.master.repository.CargoTypeRepository;
import com.conway.backend.master.repository.CityRepository;
import com.conway.backend.master.repository.TruckTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.conway.backend.enquiry.dto.request.UpdateEnquiryStatusRequest;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.conway.backend.admin.repository.AdminRepository;
import com.conway.backend.notification.service.NotificationService;

@Service
@RequiredArgsConstructor
public class EnquiryServiceImpl
        implements EnquiryService {

    private final EnquiryRepository enquiryRepository;

    private final CityRepository cityRepository;

    private final TruckTypeRepository truckTypeRepository;

    private final CargoTypeRepository cargoTypeRepository;
    private final NotificationService notificationService;

    private final AdminRepository adminRepository;

    @Override
    @Transactional
    public EnquiryResponse createEnquiry(
            CreateEnquiryRequest request
    ) {

        City city =
                cityRepository.findById(
                                request.getCityId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "City not found"
                                )
                        );

        TruckType truckType =
                truckTypeRepository.findById(
                                request.getTruckTypeId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Truck type not found"
                                )
                        );

        CargoType cargoType =
                cargoTypeRepository.findById(
                                request.getCargoTypeId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cargo type not found"
                                )
                        );

        Enquiry enquiry =
                Enquiry.builder()
                        .customerName(
                                request.getCustomerName()
                        )
                        .phoneNumber(
                                request.getPhoneNumber()
                        )
                        .email(
                                request.getEmail()
                        )
                        .city(city)
                        .truckType(truckType)
                        .cargoType(cargoType)
                        .message(
                                request.getMessage()
                        )
                        .status(
                                EnquiryStatus.NEW
                        )
                        .build();

        Enquiry savedEnquiry =
                enquiryRepository.save(
                        enquiry
                );

        notificationService
                .createNotificationForAllActiveAdmins(
                        "ENQUIRY_CREATED",
                        "New Enquiry",
                        "New enquiry received from "
                                + savedEnquiry.getCustomerName(),
                        "ENQUIRY",
                        savedEnquiry.getId()
                );

        return EnquiryMapper.toResponse(
                savedEnquiry
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EnquiryResponse> getAllEnquiries(
            String status,
            String search,
            int page,
            int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size);

        if (
                status != null &&
                        !status.isBlank() &&
                        search != null &&
                        !search.isBlank()
        ) {

            EnquiryStatus enquiryStatus;

            try {

                enquiryStatus =
                        EnquiryStatus.valueOf(
                                status.trim()
                                        .toUpperCase()
                        );

            } catch (Exception ex) {

                throw new BadRequestException(
                        "Invalid enquiry status"
                );
            }

            return enquiryRepository
                    .findByStatusAndCustomerNameContainingIgnoreCase(
                            enquiryStatus,
                            search,
                            pageable
                    )
                    .map(EnquiryMapper::toResponse);
        }

        if (
                status != null &&
                        !status.isBlank()
        ) {

            EnquiryStatus enquiryStatus;

            try {

                enquiryStatus =
                        EnquiryStatus.valueOf(
                                status.trim()
                                        .toUpperCase()
                        );

            } catch (Exception ex) {

                throw new BadRequestException(
                        "Invalid enquiry status"
                );
            }

            return enquiryRepository
                    .findByStatus(
                            enquiryStatus,
                            pageable
                    )
                    .map(EnquiryMapper::toResponse);
        }

        if (
                search != null &&
                        !search.isBlank()
        ) {

            return enquiryRepository
                    .findByCustomerNameContainingIgnoreCase(
                            search,
                            pageable
                    )
                    .map(EnquiryMapper::toResponse);
        }

        return enquiryRepository
                .findAll(pageable)
                .map(EnquiryMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public EnquiryResponse getEnquiryById(
            Long id
    ) {

        Enquiry enquiry =
                enquiryRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Enquiry not found"
                                )
                        );

        return EnquiryMapper.toResponse(
                enquiry
        );
    }
    @Override
    @Transactional
    public EnquiryResponse updateStatus(
            Long id,
            UpdateEnquiryStatusRequest request
    ) {

        Enquiry enquiry =
                enquiryRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Enquiry not found"
                                )
                        );

        EnquiryStatus status;

        try {

            status =
                    EnquiryStatus.valueOf(
                            request.getStatus()
                                    .trim()
                                    .toUpperCase()
                    );

        } catch (Exception ex) {

            throw new BadRequestException(
                    "Invalid enquiry status"
            );
        }

        enquiry.setStatus(status);

        Enquiry savedEnquiry =
                enquiryRepository.save(
                        enquiry
                );

        notificationService
                .createNotificationForAllActiveAdmins(
                        "ENQUIRY_STATUS_CHANGED",
                        "Enquiry Status Updated",
                        "Enquiry #"
                                + savedEnquiry.getId()
                                + " moved to "
                                + savedEnquiry.getStatus(),
                        "ENQUIRY",
                        savedEnquiry.getId()
                );

        return EnquiryMapper.toResponse(
                savedEnquiry
        );
    }
}