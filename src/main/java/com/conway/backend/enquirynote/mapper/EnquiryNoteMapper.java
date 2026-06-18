package com.conway.backend.enquirynote.mapper;

import com.conway.backend.enquirynote.dto.response.EnquiryNoteResponse;
import com.conway.backend.enquirynote.entity.EnquiryNote;

public class EnquiryNoteMapper {

    private EnquiryNoteMapper() {
    }

    public static EnquiryNoteResponse toResponse(
            EnquiryNote note
    ) {

        return EnquiryNoteResponse.builder()
                .id(note.getId())
                .enquiryId(
                        note.getEnquiry().getId()
                )
                .adminId(
                        note.getAdmin().getId()
                )
                .adminName(
                        note.getAdmin().getFullName()
                )
                .note(
                        note.getNote()
                )
                .createdAt(
                        note.getCreatedAt()
                )
                .build();
    }
}