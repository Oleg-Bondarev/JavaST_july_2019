USE stock_gift_coupons_db;

DROP TABLE category;

CREATE TABLE Category (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(100),

    CONSTRAINT unique_id_category UNIQUE(id),
    CONSTRAINT PK_Category PRIMARY KEY(id)
) default charset = utf8;

CREATE TABLE CompanyProvider (
    id int NOT NULL AUTO_INCREMENT,
    address varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    mobile_phone int NOT NULL,

    CONSTRAINT unique_id_comp_provider UNIQUE(id),
    CONSTRAINT PK_CompanyProvider PRIMARY KEY(id),
    CONSTRAINT unique_mobile UNIQUE(mobile_phone),
    CONSTRAINT mobile_check CHECK(mobile_phone > 0)
) default charset = utf8;

CREATE TABLE Coupon (
    id int NOT NULL AUTO_INCREMENT,
    category_id int,
    company_provider_id int,
    name varchar(255) NOT NULL,
    description varchar(4000) NOT NULL,
    price decimal(5,2) NOT NUll,
    adding_date_time DATETIME NOT NULL,
    holding_address VARCHAR(255),

    CONSTRAINT unique_id_coupon UNIQUE(id),
    CONSTRAINT PK_Coupon PRIMARY KEY(id),
    CONSTRAINT price_check CHECK(price > 0),
    CONSTRAINT FK_Category_Coupon FOREIGN KEY(category_id) REFERENCES Category(id),
    CONSTRAINT FK_CompanyProvider_Coupon FOREIGN KEY(company_provider_id)
        REFERENCES CompanyProvider(id)
) default charset = utf8;

CREATE TABLE User (
    id int NOT NULL AUTO_INCREMENT,
     /*
      * 0 - administrator(Role.ADMINISTRATOR)
      * 1 - customer(Role.CUSTOMER)
      */
    role tinyint NOT NULL,
    login varchar(50) NOT NULL,
    password char(64) NOT NULL,
    first_name varchar(255) NOT NULL,
    second_name varchar(255) NOT NULL,
    mobile_phone int NOT NULL,
    birthday datetime NULL,
    registration_date_time datetime NULL,

    CONSTRAINT unique_id_user UNIQUE(id),
    CONSTRAINT unique_login UNIQUE(login),
    CONSTRAINT PK_User PRIMARY KEY(id),
    CONSTRAINT mobile_check_user CHECK(mobile_phone > 0),
    CONSTRAINT role_check CHECK(role IN (0, 1))
) default charset = utf8;

CREATE UNIQUE INDEX login_index ON User(id, login);

CREATE TABLE CouponUser (
    id int NOT NULL AUTO_INCREMENT,
    registration_date_time datetime NOT NULL,
    coupon_id int,
    user_id int,

    CONSTRAINT unique_id_coupon_user UNIQUE(id),
    CONSTRAINT PK_CouponUser PRIMARY KEY(id),
    CONSTRAINT FK_Coupon_UserCoupon FOREIGN KEY(coupon_id) REFERENCES Coupon(id),
    CONSTRAINT FK_User_CouponUser FOREIGN KEY(user_id) REFERENCES User(id)
) default charset = utf8;

CREATE TABLE Reviews (
    id int NOT NULL AUTO_INCREMENT,
    review varchar(4000) NULL,
    coupon_id int,
    user_id int,

    CONSTRAINT unique_id_reviews UNIQUE(id),
    CONSTRAINT PK_Reviews PRIMARY KEY(id),
    CONSTRAINT FK_Coupon_Reviews FOREIGN KEY(coupon_id) REFERENCES Coupon(id),
    CONSTRAINT FK_User_Reviews FOREIGN KEY(user_id) REFERENCES User(id)
) default charset = utf8;