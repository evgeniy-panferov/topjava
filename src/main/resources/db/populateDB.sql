DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2020-06-19 15:00', 'Завтрак', 300, 100000),
       ('2020-06-19 19:00', 'Обед', 900, 100000);


INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2020-06-19 15:00', 'Обед', 1300, 100001),
       ('2020-06-19 19:00', 'Ужин', 750, 100001);