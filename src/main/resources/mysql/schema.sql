DROP TABLE IF EXISTS user CASCADE;
CREATE TABLE if not exists user
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(30)  NOT NULL,
    email      VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);