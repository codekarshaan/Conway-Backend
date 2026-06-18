CREATE TABLE audit_logs
(
    id BIGINT NOT NULL AUTO_INCREMENT,

    admin_id BIGINT NOT NULL,

    action VARCHAR(100) NOT NULL,

    entity_name VARCHAR(100) NOT NULL,

    entity_id BIGINT,

    description TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    CONSTRAINT fk_audit_logs_admin
        FOREIGN KEY (admin_id)
            REFERENCES admins(id)
);