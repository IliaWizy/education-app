--liquibase formatted sql
--changeset yourname:create-users-table-tokens-table
CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    email     VARCHAR(255) UNIQUE NOT NULL,
    full_name VARCHAR(255),
    password  VARCHAR(255)        NOT NULL,
    is_tutor  BOOLEAN,
    is_active  BOOLEAN
);

CREATE TABLE verification_tokens
(
    id          SERIAL PRIMARY KEY,
    user_id     INT,
    token       VARCHAR(255),
    expiry_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);