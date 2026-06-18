CREATE TABLE login_history
(
    id BIGINT NOT NULL AUTO_INCREMENT,

    admin_id BIGINT NOT NULL,

    login_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    ip_address VARCHAR(100),

    user_agent TEXT,

    PRIMARY KEY (id),

    CONSTRAINT fk_login_history_admin
        FOREIGN KEY (admin_id)
            REFERENCES admins(id)
);