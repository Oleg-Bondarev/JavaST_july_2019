USE stock_gift_coupons_db;

INSERT INTO category (name)
VALUES ('entertainment and showprogram'),
       ('exclusive'),
       ('quests'),
       ('leisure'),
       ('pets'),
       ('photo and video'),
       ('travels'),
       ('romantic'),
       ('health and beauty'),
       ('gourments'),
       ('motorists'),
       ('training and master classes'),
       ('gift baskets'),
       ('fitness and sport');

INSERT INTO user (login, password, role, email, avatar, first_name, second_name,
                  mobile_phone, registration_date_time, blocking)/*argon2 password*/
VALUES ('admin1', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',
        0, 'admin1@mail.ru', 'img/users/admin1.jpg', 'Виктория', 'Елисеева', 296887733,
        '2017-08-13', false);