USE stock_gift_coupons_db;

INSERT INTO user (id, user.role, email, login, password, avatar, first_name, second_name,
                  mobile_phone, registration_date_time)
VALUES (2, 0, 'admin2@gmail.com', 'admin2',
        '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8',
        'img/users/admin2.jpg', 'Эдуард', 'Ттимофеев', 339433529,
        '2017-07-03 11:37:22'), /**sha256 password*/
       (3, 1, 'user1@mail.ru',  'user1',
        'f6ee94ecb014f74f887b9dcc52daecf73ab3e3333320cadd98bcb59d895c52f5',
        'img/users/user1.jpg', 'Артём', 'Родионов', 336000033,
        '2017-08-10 15:30:22'),/**qwerty12345*/
       (4, 1, 'user2@gmail.com', 'user2',
        'd82494f05d6917ba02f7aaa29689ccb444bb73f20380876cb05d1f37b7892',
        'img/users/user2.jpg', 'Данила', 'Ларионов', 297650977,
        '2018-12-10 18:37:22'),/**adminadmin*/
       (5, 1, 'user3@mail.ru', 'user3',
        '920d9ec4b054155d18ef6e03016f208ba7ce5b0016c6e4191f2a48a36d6d5bbe',
        'img/users/user3.jpg', 'Валентина', 'Богданова', 298031324,
        '2018-07-01 11:01:22'),/**1234qwerty*/
       (6, 1, 'user4@gmail.com', 'user4',
        '3700adf1f25fab8202c1343c4b0b4e3fec706d57cad574086467b8b3ddf273ec',
        'img/users/user4.jpg', 'Евгения', 'Павлова', 335091563,
        '2019-09-10 19:30:22'),/**password12345*/
       (7, 1, 'user5@mail.ru', 'user5',
        'bead3423142344085e40ba9c5f8511b6031be76d65f669843caa0fe1fbf5c45e',
        'img/users/user5.jpg', 'Дмитрий', 'Дкрасов', 296038734,
        '2019-01-15 12:00:22');/**qwedsa12345*/

INSERT INTO company_provider (address, name, mobile_phone)
VALUES ('ул. Бирюзова 3, Минск', 'Споти Трип', 295487301),
       ('ул. Молодёжная, д. 20, Минск', 'Quests box', 330560489),
       ('ул. Мележа, л. 4, Минск', 'Зона красоты', 334652098);

INSERT INTO coupon (category_id, company_provider_id, name, picture,
                    description, price, adding_date_time, holding_address)
VALUES (14, 1, 'Альпинизм', 'img/coupons/sport1.jpg',
        'Для кого-то альпинизм — это спорт, для кого-то романтика,
        для кого-то работа — каждый решает сам для себя. С другой стороны,
        альпинизм — это такая прикладная штука, начиная заниматься которой
        вы должны чётко понимать, что в дальнейшем от ваших знаний, навыков
        и опыта будут зависеть ваше здоровье и жизнь.', 340.90,
        '2018-03-12 12:00:00', 'Скаладром "Уручье"'),
       (3, 2, 'Лабиринт минотавра', 'img/coupons/quest1.jpg',
        'По силам ли Вам повторить подвиг Тесея и выбраться из лабиринта?
        Используйте все свои возможности, предвидьте следующий шаг и не теряйте
        ни минуты. Совершив долгий путь и пройдя испытания, найденные артефакты
        укажут путь к спасению. Но разгадать направление пути по силам только
        избранным... Время покажет герой Вы или жертва.', 20.56,
        '2017-09-12 12:00:00', 'ул. 7-я Красноармейская, д 30'),
       (9, 3, 'Нежные SPA-программы для лица и тела на выбор',
        'img/coupons/beauty1.jpg',
        'Красивая кожа лица и тела требует тщательного ухода и регулярных
        салонных процедур. Вернуть эластичность и молодость вам поможет SPA
        для лица и тела. Цена на процедуры и выбор программ сделает уход
        доступным для каждого. Дарите сертификат близким или приобретайте
        для себя. Такой подарок оценит каждый.', 80.75, '2017-09-14 12:00:00',
        'Минск, проспект Победителей, 31');

INSERT INTO reviews (review, coupon_id, user_id)
VALUES ('Очень круто!!! Просто захватывает сердце, когда смотришь с высоты вниз.
        Опытные инструкторы, всё хорошо объяснили. Десять из десятию Всем советую.',
        1, 2),
       ('Довольно опасно. Без хорошей физухи не подняться. Не рекомендую.',
        1, 4),
       ('Не очень страшный увест. Спокойно можно приходить вместе с детьми.',
        2, 3),
       ('Развлечение на один раз. Иногда было страшно.',
        2, 5),
       ('Отличный сервис и дружелюбный персоналю. Процедуры просто шикарные.
        Как будто заново родилась.',
        3, 3);