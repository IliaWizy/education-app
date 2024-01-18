--liquibase formatted sql
--changeset IlyaGromov:create-users-table

--add users table
CREATE TABLE IF NOT EXISTS users
(
    id        BIGSERIAL PRIMARY KEY,
    email     VARCHAR(255) UNIQUE NOT NULL,
    full_name VARCHAR(255),
    password  VARCHAR(255) NOT NULL,
    is_tutor  BOOLEAN,
    is_active BOOLEAN
);

--rollback DROP TABLE users;

--changeset IlyaGromov:create-users-email-index
CREATE INDEX idx_users_email ON users(email);

--rollback DROP INDEX idx_users_email;
--rollback DROP TABLE users;

--changeset IlyaGromov:create-email_verification_tokens-table
CREATE TABLE IF NOT EXISTS email_verification_tokens
(
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT,
    token       VARCHAR(255),
    expiry_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

--rollback DROP TABLE email_verification_tokens;