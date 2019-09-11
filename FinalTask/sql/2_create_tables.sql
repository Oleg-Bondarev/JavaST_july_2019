USE 'stock_gift_coupons_db';

CREATE TABLE 'coupon' (
    'id' INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
    'company_id' integer,
    'name' varchar(255) not null,
    'category' varchar(255) not null,
    'description' text not null,
    'price' decimal(5,2) not null,
    'rating' integer null,
    'adding_date' datetime not null,
    primary  key('id'),
    foreign  key('company_id') references 'company_provider'('id')
) default character utf8;

create table 'company_provider' (
    'id' integer not null auto_increment unique,
    'address' varchar(255) not null,
    'company_name' varchar(255) not null unique,
    primary key('id')
) default character utf8;

create table 'admin' (
    'id' INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
    'login' VARCHAR(255) NOT NULL UNIQUE,
    'password' CHAR(32) NOT NULL,
    'email' varchar(255) not null,
    'first_name' varchar(255) not null,
    'second_name' varchar(255) not null,
    'mobile_phone' varchar(255) not null,
    primary key('id')
) default character utf8;

create table 'customer' (
    'id' integer not null auto_increment unique,
    'login' VARCHAR(255) NOT NULL UNIQUE,
    'password' CHAR(32) NOT NULL,
    'email' varchar(255) not null,
    'first_name' varchar(255) not null,
    'second_name' varchar(255) not null,
    'mobile_phone' varchar(255) not null,
    'birthday' date not null,
    'registration_date_time' datetime not null,
    primary key('id')
) default character utf8;

create table 'coupon_user' (
    'id' integer not null auto_increment unique,
    'customer_id' integer,
    'coupon_id' integer,
    'registration_date_time' datetime not null,
     foreign key('customer_id') references 'customer'('id'),
     foreign key('coupon_id') references 'coupon'('id')
) default character utf8;