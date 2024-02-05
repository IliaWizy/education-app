-- liquibase formatted sql
-- add email_verification_tokens table

-- changeset sursindmitry:2
CREATE TABLE IF NOT EXISTS email_verification_tokens
(
    id              BIGSERIAL PRIMARY KEY,
    token           VARCHAR(255),
    user_id         BIGSERIAL REFERENCES users (id) ON DELETE CASCADE ON UPDATE NO ACTION,
    expiration_time TIMESTAMP
);

-- rollback DROP TABLE email_verification_tokens;




