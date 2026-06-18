package com.conway.backend.enquirynote.service;

import com.conway.backend.enquirynote.dto.request.CreateEnquiryNoteRequest;
import com.conway.backend.enquirynote.dto.response.EnquiryNoteResponse;
import com.conway.backend.notification.service.NotificationService;
import java.util.List;

public interface EnquiryNoteService {

    EnquiryNoteResponse createNote(
            Long enquiryId,
            CreateEnquiryNoteRequest request
    );

    List<EnquiryNoteResponse> getNotes(
            Long enquiryId
    );
}