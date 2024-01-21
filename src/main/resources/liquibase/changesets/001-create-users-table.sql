-- liquibase formatted sql
-- add users table

-- changeset sursindmitry:1
CREATE TABLE IF NOT EXISTS users
(
    id                 BIGSERIAL PRIMARY KEY,
    email              VARCHAR(255) NOT NULL unique,
    name               VARCHAR(255) NOT NULL,
    password           VARCHAR(255) NOT NULL,
    active             BOOLEAN
);

-- rollback DROP TABLE users;
