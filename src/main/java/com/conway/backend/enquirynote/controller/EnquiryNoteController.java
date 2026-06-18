package com.conway.backend.enquirynote.controller;

import com.conway.backend.enquirynote.dto.request.CreateEnquiryNoteRequest;
import com.conway.backend.enquirynote.dto.response.EnquiryNoteResponse;
import com.conway.backend.enquirynote.service.EnquiryNoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enquiries/{enquiryId}/notes")
@RequiredArgsConstructor
public class EnquiryNoteController {

    private final EnquiryNoteService
            enquiryNoteService;

    @PostMapping
    public EnquiryNoteResponse createNote(
            @PathVariable Long enquiryId,
            @Valid
            @RequestBody
            CreateEnquiryNoteRequest request
    ) {

        return enquiryNoteService.createNote(
                enquiryId,
                request
        );
    }

    @GetMapping
    public List<EnquiryNoteResponse> getNotes(
            @PathVariable Long enquiryId
    ) {

        return enquiryNoteService.getNotes(
                enquiryId
        );
    }
}