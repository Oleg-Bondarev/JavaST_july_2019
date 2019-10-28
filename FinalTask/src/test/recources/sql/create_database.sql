CREATE DATABASE IF NOT EXISTS coupons_test; #default charset utf8;

CREATE USER IF NOT EXISTS 'coupons_user'@'localhost' IDENTIFIED BY 'password';

GRANT SELECT, INSERT, UPDATE, DELETE
    ON coupons_test.*
    TO 'coupons_user'@'localhost';