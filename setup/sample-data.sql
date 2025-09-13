-- =========================
-- SAMPLE USERS
-- =========================
INSERT INTO users (name, email, phone, address, online_banking, password)
VALUES
('Rahul Sharma', 'rahul.sharma@example.com', '9876543210', 'Delhi, India', 'ACTIVE', 'passRahul123'),
('Anjali Verma', 'anjali.verma@example.com', '9123456780', 'Mumbai, India', 'NOT_ACTIVE', 'passAnjali456'),
('Vikram Singh', 'vikram.singh@example.com', '9988776655', 'Bangalore, India', 'ACTIVE', 'passVikram789');

-- =========================
-- SAMPLE ADMINS
-- =========================
INSERT INTO admins (username, email, password, admin_role)
VALUES
('Tiger Kumar', 'tiger@bank.com', 'superPass123', 'SUPER_ADMIN'),
('Nitin Rai', 'nitinrai@bank.com', 'managerPass456', 'MANAGER'),
('Akshay Malviya', 'akshay@bank.com', 'adminPass789', 'ADMIN');

-- =========================
-- SAMPLE ACCOUNTS
-- =========================
INSERT INTO accounts (user_id, account_number, account_type, balance)
VALUES
(1, 'ACC10001', 'SAVINGS', 50000.00),
(1, 'ACC10002', 'CURRENT', 150000.00),
(2, 'ACC20001', 'SAVINGS', 25000.00),
(3, 'ACC30001', 'SAVINGS', 80000.00);

-- =========================
-- SAMPLE TRANSACTIONS
-- =========================
INSERT INTO transactions (account_id, to_account_number, transaction_type, amount, status)
VALUES
(1, 'ACC10002', 'DEPOSIT', 10000.00, 'SUCCESS'),
(1, 'ACC10002', 'WITHDRAWAL', 5000.00, 'SUCCESS'),
(2, 'ACC30001', 'TRANSFER', 20000.00, 'PENDING'),
(3, 'ACC10001', 'DEPOSIT', 15000.00, 'SUCCESS'),
(4, 'ACC10001', 'TRANSFER', 10000.00, 'FAILED');
