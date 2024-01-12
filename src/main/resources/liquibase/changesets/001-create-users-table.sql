--liquibase formatted sql
--changeset yourname:create-users-table
CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    email     VARCHAR(255) UNIQUE NOT NULL,
    full_name VARCHAR(255),
    password  VARCHAR(255),
    is_tutor  BOOLEAN
);