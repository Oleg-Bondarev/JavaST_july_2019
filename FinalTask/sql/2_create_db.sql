CREATE DATABASE IF NOT EXISTS stock_gift_coupons_db; #default charset utf8;

CREATE USER IF NOT EXISTS 'stock_user'@'localhost' IDENTIFIED BY 'password';

GRANT SELECT, INSERT, UPDATE, DELETE
    ON stock_gift_coupons_db.*
    TO 'stock_user'@'localhost';