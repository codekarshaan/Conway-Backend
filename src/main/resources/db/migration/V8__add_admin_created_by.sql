ALTER TABLE admins
    ADD COLUMN created_by BIGINT NULL;

ALTER TABLE admins
    ADD CONSTRAINT fk_admin_created_by
        FOREIGN KEY (created_by)
            REFERENCES admins(id);