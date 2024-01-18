CREATE TABLE IF NOT EXISTS users
(
    id        BIGSERIAL PRIMARY KEY,
    email     VARCHAR(255) NOT NULL unique,
    firstname VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    active    BOOLEAN
);


CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGSERIAL    not null,
    role    VARCHAR(255) not null,
    PRIMARY KEY (user_id, role),
    CONSTRAINT fk_users_roles_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS email_verification_token
(
    id              BIGSERIAL PRIMARY KEY,
    token           VARCHAR(255),
    user_id         BIGSERIAL REFERENCES users (id) ON DELETE CASCADE ON UPDATE NO ACTION,
    expiration_time TIMESTAMP
);
