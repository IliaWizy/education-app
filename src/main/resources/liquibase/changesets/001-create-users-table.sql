-- liquibase formatted sql
-- add users table

-- changeset sursindmitry:1
CREATE TABLE IF NOT EXISTS users
(
    id                 BIGSERIAL PRIMARY KEY,
    email              VARCHAR(255) NOT NULL unique,
    name               VARCHAR(255) NOT NULL,
    password           VARCHAR(255) NOT NULL,
    account_expired    BOOLEAN,
    account_locked     BOOLEAN,
    credential_expired BOOLEAN,
    active             BOOLEAN
);

-- rollback DROP TABLE users;
