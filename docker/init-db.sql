-- =====================================
-- 🏦 Banking App - MySQL Initialization
-- =====================================

-- 1️⃣ Create the database (only if not exists)
CREATE DATABASE IF NOT EXISTS banking_db;

-- 2️⃣ Create a dedicated user with a secure password (only if not exists)
CREATE USER IF NOT EXISTS '${MYSQL_USER}'@'%' IDENTIFIED BY '${MYSQL_PASSWORD}';

-- 3️⃣ Grant privileges to this user ONLY for this database
GRANT ALL PRIVILEGES ON banking_db.* TO '${MYSQL_USER}'@'%';

-- 4️⃣ Apply the changes immediately
FLUSH PRIVILEGES;

-- =====================================
-- 5️⃣ Create essential tables
-- =====================================

-- config_banking_app table for storing configuration values
CREATE TABLE IF NOT EXISTS banking_db.config_banking_app (
    id INT AUTO_INCREMENT PRIMARY KEY,
    config_key VARCHAR(100) NOT NULL UNIQUE,
    config_value VARCHAR(255) NOT NULL
);

-- Insert default configuration values
INSERT INTO banking_db.config_banking_app (config_key, config_value)
VALUES
    ('SMS_API_KEY', '${SMS_API_KEY}'),
    ('JWT_SECRET_KEY', '${JWT_SECRET_KEY}'),
    ('JWT_EXPIRATION_TIME', '${JWT_EXPIRATION_TIME}')
ON DUPLICATE KEY UPDATE config_value = VALUES(config_value);
