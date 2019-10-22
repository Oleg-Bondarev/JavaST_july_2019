USE stock_gift_coupons_db;

INSERT INTO user (login, password, user.role, email, avatar, first_name, second_name,
                  mobile_phone, registration_date_time, user.blocking)/*argon2 password*/
VALUES ('userAndrey', '$argon2id$v=19$m=65536,t=2,p=4$kBH0rGBLdeK6jY9mlb4NFQ$Q8AHCjHIkpP0mb1qDFsw1ShMWiVZBrQjk1VK01tXk/I',
        1, 'andrey@mail.ru', 'img/user/user_profile.jpg', 'Andrey', 'Flop', 290808888,
        '2017-08-10', false),/**1234567890*/
       ('evgenyUser', '$argon2id$v=19$m=65536,t=2,p=4$C8GA4I+2HgCHaQHLoRJ4wg$g07405SQxEasrYkU2qxpDQCFALGeVcojzS4H/bnNjvo',
        1, 'evg@gmail.ru', 'img/user/user_profile.jpg', 'Evgeny', 'Shevcov', 332014562,
        '2018-12-10', false),/**1234567890*/
       ('vermishelUser', '$argon2id$v=19$m=65536,t=2,p=4$KfjEL3JRCAYkiSt8sNDoYA$USElf0B6UzM/1nIiBhT3n+yten0yU69F6udkLiC4+f4',
        1, 'vermishel-anton@mail.ru', 'img/user/user_profile.jpg', 'Anton', 'Vermeshelev', 298022011,
        '2018-07-01', false),/**1234567890*/
        ('ostapUser', '$argon2id$v=19$m=65536,t=2,p=4$VNJ32V88UFWPqyqsA1DJWg$9w+3KzDqmwk2VvVfIxZShZjyxlc3KB1k+EqtL7O/PI8',
        1, 'ostap-berg@mail.ru', 'img/user/user_profile.jpg', 'Ostap', 'Berg', 333031529,
        '2018-05-01', false),
       ('nikolapUser', '$argon2id$v=19$m=65536,t=2,p=4$mmJJ3PmzsAy/Q+Gzhkr7nA$UIBSoeyki6TQXRVhsMCiQHtrxTsl3GKfQQAiKYa3iL0',
        1, 'nikola-flemeng@mail.ru', 'img/user/user_profile.jpg', 'Nikola', 'Flemen', 445021064,
        '2018-09-11', false),
       ('leonidUser', '$argon2id$v=19$m=65536,t=2,p=4$nGUu4vwiSMaxUt4CVexk7A$3yADPvz7y+LkaRPSXSIGTInAnyqEjBaCLBJTyvJ/Gg8',
        1, 'leonid@mail.ru', 'img/user/user_profile.jpg', 'Leonid', 'Davidov', 296617003,
        '2019-09-11', false);

INSERT INTO company_provider (address, name, mobile_phone, blocking)
VALUES ('ул. Бирюзова 3, Минск', 'Споти Трип', 295487301, false),
       ('ул. Молодёжная, д. 20, Минск', 'Quests box', 330560489, false),
       ('ул. Мележа, л. 4, Минск', 'Зона красоты', 334652098, false),
       ('ул. Смоленская, д. 3, корпус 4', 'Мир счастья', 330645983, false),
    	('ул. Аранская 4б, корп. 11', 'SkipAll', 294200131, false),
    	('Тимирязева 94, офис 6', 'Dark Cube', 446230303, false),
    	('Левкова 5, корпус 1',	'Квест Лэнд', 339764120, false),
     	('Купаловская площадь 34б, офис 8',	'Solaris',	441203152, false),
    	('г. Витебск, трц. Трио,  пр-т Строителей 4, пав. 130', 'Hero Park', 293005602, false),
    	('Малинина 48',	'Спортивный комплекс "Адреналин"', 290330121, false),
    	('проспект Независимоси 8',	'Барбершоп "Brutality"', 445012180, false),
    	('ул. Орды 49',	'Hero Park', 332630101,	false);

INSERT INTO coupon (category_id, company_provider_id, name, picture,
                    description, price, adding_date_time, holding_address, blocking)
VALUES (14, 1, 'Альпинизм', 'img/coupons/sport1.jpg',
        'Для кого-то альпинизм — это спорт, для кого-то романтика,
        для кого-то работа — каждый решает сам для себя. С другой стороны,
        альпинизм — это такая прикладная штука, начиная заниматься которой
        вы должны чётко понимать, что в дальнейшем от ваших знаний, навыков
        и опыта будут зависеть ваше здоровье и жизнь.', 340.90,
        '2018-03-12', 'Скаладром "Уручье"', false),
       (3, 2, 'Лабиринт минотавра', 'img/coupons/quest1.jpg',
        'По силам ли Вам повторить подвиг Тесея и выбраться из лабиринта?
        Используйте все свои возможности, предвидьте следующий шаг и не теряйте
        ни минуты. Совершив долгий путь и пройдя испытания, найденные артефакты
        укажут путь к спасению. Но разгадать направление пути по силам только
        избранным... Время покажет герой Вы или жертва.', 20.56,
        '2017-09-12', 'ул. 7-я Красноармейская, д 30', false),
       (9, 3, 'Нежные SPA-программы для лица и тела на выбор',
        'img/coupons/beauty1.jpg',
        'Красивая кожа лица и тела требует тщательного ухода и регулярных
        салонных процедур. Вернуть эластичность и молодость вам поможет SPA
        для лица и тела. Цена на процедуры и выбор программ сделает уход
        доступным для каждого. Дарите сертификат близким или приобретайте
        для себя. Такой подарок оценит каждый.', 80.75, '2017-09-14',
        'Минск, проспект Победителей, 31', false);

INSERT INTO reviews (review, coupon_id, user_id)
VALUES ('Очень круто!!! Просто захватывает сердце, когда смотришь с высоты вниз.
        Опытные инструкторы, всё хорошо объяснили. Десять из десятию Всем советую.',
        1, 2),
       ('Довольно опасно. Без хорошей физухи не подняться. Не рекомендую.',
        1, 4),
       ('Не очень страшный квест. Спокойно можно приходить вместе с детьми.',
        2, 3),
       ('Развлечение на один раз. Иногда было страшно.',
        2, 5),
       ('Отличный сервис и дружелюбный персоналю. Процедуры просто шикарные.
        Как будто заново родилась.',
        3, 3);