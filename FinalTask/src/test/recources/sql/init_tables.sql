USE coupons_test;

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
VALUES ('olegAdmin', '$argon2id$v=19$m=65536,t=2,p=4$ivR5jACDbtSDwV8pEFAkpQ$0GwdQV9NOa1nVrNS2G4nDcAzG/DYA6pogFYzJ4TR7Gk',
        0, 'oleg@mail.ru', 'img/user/user_profile.jpg', 'Oleg', 'Bondarev', 336494933,
        '2017-10-14', false);/**1234567890*/