INSERT INTO admins
(
    full_name,
    email,
    password_hash,
    role_id,
    is_active,
    created_at,
    updated_at
)
VALUES
    (
        'Super Admin',
        'admin@conway.com',
        '$2a$12$08KN6JiMm6kKU.stCfmH0OxNNS6obwC7qxcEsYGpyUkPgFF.ubb7W',
        (
            SELECT id
            FROM roles
            WHERE role_name = 'ROLE_SUPER_ADMIN'
        ),
        TRUE,
        NOW(),
        NOW()
    );