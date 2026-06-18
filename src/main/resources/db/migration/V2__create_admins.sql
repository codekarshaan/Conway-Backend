CREATE TABLE admins (

                        id BIGINT AUTO_INCREMENT PRIMARY KEY,

                        full_name VARCHAR(255) NOT NULL,

                        email VARCHAR(255) NOT NULL UNIQUE,

                        password_hash VARCHAR(255) NOT NULL,

                        role_id BIGINT NOT NULL,

                        is_active BOOLEAN NOT NULL DEFAULT TRUE,

                        last_login TIMESTAMP NULL,

                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                        updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

                        CONSTRAINT fk_admin_role
                            FOREIGN KEY (role_id)
                                REFERENCES roles(id)

);