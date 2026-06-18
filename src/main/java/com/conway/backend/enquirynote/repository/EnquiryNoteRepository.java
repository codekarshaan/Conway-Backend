package com.conway.backend.enquirynote.repository;

import com.conway.backend.enquirynote.entity.EnquiryNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquiryNoteRepository
        extends JpaRepository<EnquiryNote, Long> {

    List<EnquiryNote> findByEnquiryIdOrderByCreatedAtDesc(
            Long enquiryId
    );
}