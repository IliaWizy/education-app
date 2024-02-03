-- liquibase formatted sql
-- add users table

-- changeset sursindmitry:3
CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGSERIAL    not null,
    role    VARCHAR(255) not null,
    PRIMARY KEY (user_id, role),
    CONSTRAINT fk_users_roles_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE NO ACTION
);

-- rollback DROP TABLE users_roles;
