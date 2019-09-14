CREATE TABLE category
(
    id   int NOT NULL AUTO_INCREMENT,
    name varchar(100),

    CONSTRAINT unique_id_category UNIQUE (id),
    CONSTRAINT PK_category PRIMARY KEY (id)
) default charset = utf8;

CREATE TABLE company_provider
(
    id           int          NOT NULL AUTO_INCREMENT,
    address      varchar(255) NOT NULL,
    name         varchar(255) NOT NULL,
    mobile_phone int          NOT NULL,

    CONSTRAINT unique_id_comp_provider UNIQUE (id),
    CONSTRAINT PK_company_provider PRIMARY KEY (id),
    CONSTRAINT unique_mobile UNIQUE (mobile_phone),
    CONSTRAINT mobile_check CHECK (mobile_phone > 0)
) default charset = utf8;

CREATE TABLE coupon
(
    id                  int           NOT NULL AUTO_INCREMENT,
    category_id         int,
    company_provider_id int,
    name                varchar(255)  NOT NULL,
    picture             varchar(255)  NOT NULL,
    description         text          NOT NULL,
    price               decimal(5, 2) NOT NUll,
    adding_date_time    DATETIME      NOT NULL,
    holding_address     VARCHAR(255),

    CONSTRAINT unique_id_coupon UNIQUE (id),
    CONSTRAINT PK_coupon PRIMARY KEY (id),
    CONSTRAINT price_check CHECK (price > 0),
    CONSTRAINT FK_category_coupon FOREIGN KEY (category_id) REFERENCES category (id),
    CONSTRAINT FK_company_provider_Coupon FOREIGN KEY (company_provider_id)
        REFERENCES company_provider (id)
) default charset = utf8;

CREATE TABLE user
(
    id                     int          NOT NULL AUTO_INCREMENT,
    /*
     * 0 - administrator(Role.ADMINISTRATOR)
     * 1 - customer(Role.CUSTOMER)
     */
    role                   tinyint      NOT NULL,
    email                  varchar(50)  NOT NULL,
    login                  varchar(50)  NOT NULL,
    password               char(64)     NOT NULL,
    avatar                 varchar(255) NOT NULL,
    first_name             varchar(255) NOT NULL,
    second_name            varchar(255) NOT NULL,
    mobile_phone           int          NOT NULL,
    registration_date_time datetime     NOT NULL,

    CONSTRAINT unique_id_user UNIQUE (id),
    CONSTRAINT unique_email UNIQUE (email),
    CONSTRAINT PK_user PRIMARY KEY (id),
    CONSTRAINT mobile_check_user CHECK (mobile_phone > 0),
    CONSTRAINT role_check CHECK (role IN (0, 1))
) default charset = utf8;

CREATE UNIQUE INDEX login_index ON user (id, login);

CREATE TABLE coupon_user
(
    id                     int      NOT NULL AUTO_INCREMENT,
    registration_date_time datetime NOT NULL,
    coupon_id              int,
    user_id                int,

    CONSTRAINT unique_id_coupon_user UNIQUE (id),
    CONSTRAINT PK_coupon_user PRIMARY KEY (id),
    CONSTRAINT FK_coupon_userCoupon FOREIGN KEY (coupon_id) REFERENCES coupon (id),
    CONSTRAINT FK_User_CouponUser FOREIGN KEY (user_id) REFERENCES user (id)
) default charset = utf8;

CREATE TABLE reviews
(
    id        int  NOT NULL AUTO_INCREMENT,
    review    text NULL,
    coupon_id int,
    user_id   int,

    CONSTRAINT unique_id_reviews UNIQUE (id),
    CONSTRAINT PK_reviews PRIMARY KEY (id),
    CONSTRAINT FK_coupon_reviews FOREIGN KEY (coupon_id) REFERENCES coupon (id),
    CONSTRAINT FK_user_reviews FOREIGN KEY (user_id) REFERENCES user (id)
) default charset = utf8;