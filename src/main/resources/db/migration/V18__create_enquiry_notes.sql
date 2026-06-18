CREATE TABLE enquiry_notes
(
    id BIGINT NOT NULL AUTO_INCREMENT,

    enquiry_id BIGINT NOT NULL,

    admin_id BIGINT NOT NULL,

    note TEXT NOT NULL,

    created_at TIMESTAMP NOT NULL
        DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    CONSTRAINT fk_note_enquiry
        FOREIGN KEY (enquiry_id)
            REFERENCES enquiries(id),

    CONSTRAINT fk_note_admin
        FOREIGN KEY (admin_id)
            REFERENCES admins(id)
);