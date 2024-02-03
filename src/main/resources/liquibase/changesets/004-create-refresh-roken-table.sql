-- liquibase formatted sql
-- add refresh_token table

-- changeset sursindmitry:4
CREATE TABLE IF NOT EXISTS refresh_token
(
    id BIGSERIAL PRIMARY KEY,
    refresh_token VARCHAR(255) UNIQUE,
    user_id BIGSERIAL REFERENCES users(id) ON DELETE CASCADE ON UPDATE NO ACTION,
    expiration_time TIMESTAMP
);

-- rollback DROP TABLE refresh_token;
