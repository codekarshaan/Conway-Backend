CREATE TABLE roles (
                       id BIGINT NOT NULL AUTO_INCREMENT,

                       role_name VARCHAR(100) NOT NULL,

                       description VARCHAR(255),

                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                       updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

                       PRIMARY KEY (id),

                       CONSTRAINT uk_roles_role_name UNIQUE (role_name)
);