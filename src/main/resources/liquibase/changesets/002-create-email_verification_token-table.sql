-- liquibase formatted sql
-- add email_verification_token table

-- changeset sursindmitry:2
CREATE TABLE IF NOT EXISTS email_verification_token
(
    id              BIGSERIAL PRIMARY KEY,
    token           VARCHAR(255),
    user_id         BIGSERIAL REFERENCES users (id) ON DELETE CASCADE ON UPDATE NO ACTION,
    expiration_time TIMESTAMP
);

-- rollback DROP TABLE email_verification_token;




