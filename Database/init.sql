-- init.sql
CREATE DATABASE IF NOT EXISTS sompopo;
USE sompopo;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    msisdn VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    deleted_at TIMESTAMP NULL,
    deleted BOOLEAN  DEFAULT FALSE 
);

CREATE USER 'admin'@'%' IDENTIFIED BY 'adminpssd'; 
GRANT SELECT, INSERT, UPDATE, DELETE ON sompopo.* TO 'admin'@'%';
FLUSH PRIVILEGES;

INSERT INTO users (username, email, msisdn, password, created_at, updated_at, deleted) VALUES
('john_doe', 'john.doe@example.com', '123-456-7890', '$2a$10$somehashedpassword', NOW(), NOW(), FALSE);

INSERT INTO users (username, email, msisdn, password, created_at, updated_at, deleted) VALUES
('jane_doe', 'jane.doe@example.com', '987-654-3210', '$2a$10$anotherhashedpassword', NOW(), NOW(), FALSE);

INSERT INTO users (username, email, msisdn, password, created_at, updated_at, deleted) VALUES
('marie_dane', 'test1@example.net', '555-123-4567', '$2a$10$testhashedpassword1', NOW(), NOW(), FALSE);

INSERT INTO users (username, email, msisdn, password, created_at, updated_at, deleted) VALUES
('oscar_roe', 'test2@example.net', '555-789-0123', '$2a$10$testhashedpassword2', NOW(), NOW(), FALSE);

INSERT INTO users (username, email, msisdn, password, created_at, updated_at, deleted) VALUES
('michelle_cary', 'admin@example.org', '555-789-0122', '$2a$10$adminhashedpassword', NOW(), NOW(), FALSE);


CREATE OR REPLACE VIEW active_users_view AS
SELECT
    id,
    username,
    email,
    msisdn,
    password,  
    created_at,
    updated_at,
    deleted_at

FROM
    users
WHERE
    deleted = FALSE;