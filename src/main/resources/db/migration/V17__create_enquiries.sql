CREATE TABLE enquiries
(
    id BIGINT NOT NULL AUTO_INCREMENT,

    customer_name VARCHAR(100) NOT NULL,

    phone_number VARCHAR(20) NOT NULL,

    email VARCHAR(150),

    city_id BIGINT NOT NULL,

    truck_type_id BIGINT NOT NULL,

    cargo_type_id BIGINT NOT NULL,

    message TEXT,

    status VARCHAR(30) NOT NULL,

    assigned_admin_id BIGINT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    CONSTRAINT fk_enquiry_city
        FOREIGN KEY (city_id)
            REFERENCES cities(id),

    CONSTRAINT fk_enquiry_truck
        FOREIGN KEY (truck_type_id)
            REFERENCES truck_types(id),

    CONSTRAINT fk_enquiry_cargo
        FOREIGN KEY (cargo_type_id)
            REFERENCES cargo_types(id),

    CONSTRAINT fk_enquiry_admin
        FOREIGN KEY (assigned_admin_id)
            REFERENCES admins(id)
);