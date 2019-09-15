CREATE TABLE category
(
    id   int NOT NULL AUTO_INCREMENT,
    name varchar(100),

    CONSTRAINT PK_category PRIMARY KEY (id)
);

CREATE TABLE company_provider
(
    id           int          NOT NULL AUTO_INCREMENT,
    address      varchar(255) NOT NULL,
    name         varchar(255) NOT NULL,
    mobile_phone int          NOT NULL,

    CONSTRAINT PK_company_provider PRIMARY KEY (id),
    CONSTRAINT UC_company_provider_mobile UNIQUE (mobile_phone),
    CONSTRAINT CH_company_provider_mobile CHECK (mobile_phone > 0)
);

CREATE TABLE coupon
(
    id                  int           NOT NULL AUTO_INCREMENT,
    category_id         int,
    company_provider_id int,
    name                varchar(255)  NOT NULL,
    picture             varchar(4096)  NOT NULL,
    description         varchar (1000) NOT NULL,
    price               decimal(5, 2) NOT NUll,
    adding_date_time    DATETIME      NOT NULL,
    holding_address     VARCHAR(255),

    CONSTRAINT PK_coupon PRIMARY KEY (id),
    CONSTRAINT CH_coupon_price CHECK (price > 0),
    CONSTRAINT FK_coupon_category FOREIGN KEY (category_id) REFERENCES category (id),
    CONSTRAINT FK_coupon_company_provider FOREIGN KEY (company_provider_id)
        REFERENCES company_provider (id)
);

CREATE TABLE user
(
    id                     int          NOT NULL AUTO_INCREMENT,
    login                  varchar(50)  NOT NULL,
    #Argon2 hashing algorithm
    password               CHAR(99)     NOT NULL,
    /*
     * 0 - administrator(Role.ADMINISTRATOR)
     * 1 - customer(Role.CUSTOMER)
     */
    role                   tinyint      NOT NULL,
    email                  varchar(50)  NOT NULL,
    avatar                 varchar(4096) NOT NULL,
    first_name             varchar(50)  NOT NULL,
    second_name            varchar(50)  NOT NULL,
    mobile_phone           int          NOT NULL,
    registration_date_time datetime     NOT NULL,

    CONSTRAINT UC_user_email UNIQUE (email),
    CONSTRAINT PK_user PRIMARY KEY (id),
    CONSTRAINT CH_user_mobile CHECK (mobile_phone > 0),
    CONSTRAINT CH_user_role CHECK (role IN (0, 1))
);

CREATE UNIQUE INDEX IDX_user_login ON user (login);

CREATE TABLE coupon_user
(
    id                     int      NOT NULL AUTO_INCREMENT,
    registration_date_time datetime NOT NULL,
    coupon_id              int,
    user_id                int,

    CONSTRAINT PK_coupon_user PRIMARY KEY (id),
    CONSTRAINT FK_coupon_user_coupon FOREIGN KEY (coupon_id) REFERENCES coupon (id),
    CONSTRAINT FK_coupon_user_user FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE reviews
(
    id        int  NOT NULL AUTO_INCREMENT,
    review    varchar (1000) NULL,
    coupon_id int,
    user_id   int,

    CONSTRAINT PK_reviews PRIMARY KEY (id),
    CONSTRAINT FK_reviews_coupon FOREIGN KEY (coupon_id) REFERENCES coupon (id),
    CONSTRAINT FK_reviews_user FOREIGN KEY (user_id) REFERENCES user (id)
);