ALTER TABLE admins
    ADD COLUMN updated_by BIGINT NULL;

ALTER TABLE admins
    ADD CONSTRAINT fk_admin_updated_by
        FOREIGN KEY (updated_by)
            REFERENCES admins(id);