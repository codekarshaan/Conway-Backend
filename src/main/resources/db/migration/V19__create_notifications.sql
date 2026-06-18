CREATE TABLE notifications (

                               id BIGINT PRIMARY KEY AUTO_INCREMENT,

                               admin_id BIGINT NOT NULL,

                               type VARCHAR(100) NOT NULL,

                               title VARCHAR(255) NOT NULL,

                               message TEXT NOT NULL,

                               reference_type VARCHAR(100),

                               reference_id BIGINT,

                               is_read BOOLEAN NOT NULL DEFAULT FALSE,

                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                               CONSTRAINT fk_notifications_admin
                                   FOREIGN KEY (admin_id)
                                       REFERENCES admins(id)

);