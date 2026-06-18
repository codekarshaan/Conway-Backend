package com.conway.backend.enquirynote.service.impl;

import com.conway.backend.admin.entity.Admin;
import com.conway.backend.enquiry.entity.Enquiry;
import com.conway.backend.enquiry.repository.EnquiryRepository;
import com.conway.backend.enquirynote.dto.request.CreateEnquiryNoteRequest;
import com.conway.backend.enquirynote.dto.response.EnquiryNoteResponse;
import com.conway.backend.enquirynote.entity.EnquiryNote;
import com.conway.backend.enquirynote.mapper.EnquiryNoteMapper;
import com.conway.backend.enquirynote.repository.EnquiryNoteRepository;
import com.conway.backend.enquirynote.service.EnquiryNoteService;
import com.conway.backend.exception.ResourceNotFoundException;
import com.conway.backend.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.conway.backend.notification.service.NotificationService;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnquiryNoteServiceImpl
        implements EnquiryNoteService {

    private final EnquiryNoteRepository
            enquiryNoteRepository;

    private final EnquiryRepository
            enquiryRepository;
    private final NotificationService
            notificationService;

    @Override
    @Transactional
    public EnquiryNoteResponse createNote(
            Long enquiryId,
            CreateEnquiryNoteRequest request
    ) {

        Enquiry enquiry =
                enquiryRepository.findById(
                                enquiryId
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Enquiry not found"
                                )
                        );

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        CustomUserDetails currentUser =
                (CustomUserDetails)
                        authentication.getPrincipal();

        Admin admin =
                currentUser.getAdmin();

        EnquiryNote enquiryNote =
                EnquiryNote.builder()
                        .enquiry(enquiry)
                        .admin(admin)
                        .note(
                                request.getNote()
                        )
                        .createdAt(
                                LocalDateTime.now()
                        )
                        .build();

        EnquiryNote savedNote =
                enquiryNoteRepository.save(
                        enquiryNote
                );

        notificationService
                .createNotificationForAllActiveAdmins(
                        "ENQUIRY_NOTE_ADDED",
                        "Enquiry Note Added",
                        "A note was added to enquiry #"
                                + enquiry.getId(),
                        "ENQUIRY",
                        enquiry.getId()
                );

        return EnquiryNoteMapper.toResponse(
                savedNote
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnquiryNoteResponse> getNotes(
            Long enquiryId
    ) {

        return enquiryNoteRepository
                .findByEnquiryIdOrderByCreatedAtDesc(
                        enquiryId
                )
                .stream()
                .map(
                        EnquiryNoteMapper::toResponse
                )
                .toList();
    }
}