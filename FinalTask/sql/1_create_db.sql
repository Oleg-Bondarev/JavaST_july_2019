CREATE DATABASE IF NOT EXISTS stock_gift_coupons_db;

CREATE USER IF NOT EXISTS 'stock_admin'@'%' IDENTIFIED BY 'password';

GRANT SELECT, INSERT, UPDATE, DELETE
ON stock_gift_coupons_db.*
TO stock_admin@'%';

GRANT SELECT, INSERT, UPDATE, DELETE
ON stock_gift_coupons_db.*
TO stock_admin@localhost;
