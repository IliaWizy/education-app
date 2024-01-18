INSERT INTO users (email, firstname, lastname, password, active)
VALUES ('dmitry@gmail.com', 'Dmitry', 'Sursin', '$2a$10$XK03jwC1v.OR547gLqyORuVrrpN8jzknIkOuC1BKjJTm203ulJKea', true),
       ('joedoe@gmail.com', 'Joe', 'Doe', '$2a$10$XK03jwC1v.OR547gLqyORuVrrpN8jzknIkOuC1BKjJTm203ulJKea', true),
       ('mikesmith@gmail.com', 'Mike', 'Smith', '$2a$10$XK03jwC1v.OR547gLqyORuVrrpN8jzknIkOuC1BKjJTm203ulJKea', true);

insert into users_roles (user_id, role)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_TUTOR'),
       (3, 'ROLE_USER');