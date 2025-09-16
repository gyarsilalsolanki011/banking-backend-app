-- =========================
-- CREATE DATABASE
-- =========================
CREATE DATABASE IF NOT EXISTS banking_db;
USE banking_db;

-- =========================
-- DROP TABLES IF EXISTS (SAFE ORDER)
-- =========================
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS users;

-- =========================
-- USERS TABLE
-- =========================
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    online_banking VARCHAR(20) NOT NULL, -- Enum: ACTIVE, NOT_ACTIVE
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- =========================
-- ADMINS TABLE
-- =========================
CREATE TABLE admins (
    admin_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    admin_role VARCHAR(50) NOT NULL, -- Enum: SUPER_ADMIN, MANAGER, ADMIN
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- =========================
-- ACCOUNTS TABLE
-- =========================
CREATE TABLE accounts (
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    account_number VARCHAR(20) NOT NULL UNIQUE,
    account_type VARCHAR(20) NOT NULL, -- Enum: SAVINGS, CURRENT, FIXED
    balance DECIMAL(15,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- =========================
-- TRANSACTIONS TABLE
-- =========================
CREATE TABLE transactions (
    transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    to_account_number VARCHAR(20) NOT NULL,
    transaction_type VARCHAR(20) NOT NULL, -- Enum: DEPOSIT, WITHDRAWAL, TRANSFER
    amount DECIMAL(15,2) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL, -- Enum: SUCCESS, PENDING, FAILED
    CONSTRAINT fk_transaction_account FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

