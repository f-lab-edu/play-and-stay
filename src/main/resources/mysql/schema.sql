DROP TABLE IF EXISTS host CASCADE;
CREATE TABLE if not exists host
(
    id                 BIGINT                              NOT NULL AUTO_INCREMENT,
    first_name         VARCHAR(30)                         NOT NULL,
    last_name          VARCHAR(30)                         NOT NULL,
    nick_name          VARCHAR(20)                         NOT NULL,
    phone              VARCHAR(20)                         NOT NULL,
    email              VARCHAR(100)                        NOT NULL,
    encrypted_password VARCHAR(255)                        NOT NULL,
    auth_status        INT       DEFAULT 0                 NOT NULL,
    membership_status  INT       DEFAULT 0                 NOT NULL,
    created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);

DROP TABLE IF EXISTS guest CASCADE;
CREATE TABLE if not exists guest
(
    id                  BIGINT                              NOT NULL AUTO_INCREMENT,
    first_name          VARCHAR(30)                         NOT NULL,
    last_name           VARCHAR(30)                         NOT NULL,
    nick_name           VARCHAR(20)                         NOT NULL,
    phone               VARCHAR(20)                         NOT NULL,
    email               VARCHAR(100)                        NOT NULL,
    encrypted_password  VARCHAR(255)                        NOT NULL,
    auth_status         INT       DEFAULT 0                 NOT NULL,
    birth               DATE                                NOT NULL,
    country_code        INT,
    count_monthly_stamp INT       DEFAULT 0                 NOT NULL,
    count_coupon        INT       DEFAULT 0                 NOT NULL,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);