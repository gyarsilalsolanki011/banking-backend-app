-- =========================
-- SAMPLE USERS
-- =========================
INSERT INTO users (user_id, address, password, created_at, email, name, online_banking, phone)
VALUES
(1, '123 Main Street, Jabalpur, India', '$2a$10$KX.ChinssTxQD8S6h/6N1ubN2km5WMPIeu353rgh.jklY1RgIzx1y', '2025-04-09 14:41:45.937000', 'tiger@yahu.in', 'Tiger Kumar', 'ACTIVE', '+917552545845'),
(2, '111 Old colony, Jabalpur, India', '$2a$10$HFFwS4ZBg6dXO8bTqgSH0eicI1mbOIymoHjgVs1mz0BrtDOAsODeK', '2025-04-30 19:15:41.335000', 'kamlesh@gmail.com', 'Kamlesh', 'ACTIVE', '+919302543108'),
(5, '123 Real street, Indore, India', '$2a$10$d6grF4jzsFAi.Om8sN/6sOFmcny2FdzsahGu6n7jeQFIPYHC2W0rO', '2025-08-01 22:07:16.882000', 'vasudev@yahu.in', 'Vasu Dev', 'ACTIVE', '+917552545845');

-- =========================
-- SAMPLE ADMINS
-- =========================
INSERT INTO admins (admin_id, created_at, email, password, admin_role, username)
VALUES
(1, '2025-04-09 14:43:03.509000', 'tiger@bank.com', '$2a$10$MdN/pdYOorDX1h8.EZLeduC/7a2zie80zrZzV05Fk9wak3UTnTc4q', 'SUPER_ADMIN', 'Tiger Das'),
(2, '2025-04-09 15:00:00.000000', 'nitinrai@bank.com', '$2a$10$abc123examplehashmanager', 'MANAGER', 'Nitin Rai'),
(3, '2025-04-09 15:05:00.000000', 'akshay@bank.com', '$2a$10$xyz456examplehashadmin', 'ADMIN', 'Akshay Malviya'),
(4, '2025-04-30 19:30:00.000000', 'newadmin@bank.com', '$2a$10$pqr789examplehashnew', 'ADMIN', 'New Admin');


-- =========================
-- SAMPLE ACCOUNTS
-- =========================
INSERT INTO accounts (account_id, account_number, account_type, balance, created_at, user_id)
VALUES
(1, '952500044212', 'SAVINGS', 406186000, '2025-04-09 14:45:10.932000', 1),
(2, '952500036706', 'CURRENT', 95000, '2025-04-09 15:07:19.791000', 1),
(3, '952500078384', 'SAVINGS', 7800, '2025-04-09 15:17:59.865000', 1),
(4, '952500064119', 'SAVINGS', 111231, '2025-04-30 19:18:53.372000', 2),
(5, '952500069371', 'CURRENT', 2574000000, '2025-04-30 19:23:41.028000', 2);

-- =========================
-- SAMPLE TRANSACTIONS
-- =========================
INSERT INTO transactions (amount, transaction_date, status, to_account_number, transaction_type, account_id)
VALUES
(1, '2025-04-09 14:48:52.731000', 'COMPLETED', 'selfAccount', 'WITHDRAWAL', 1),
(1, '2025-04-09 15:55:25.932000', 'COMPLETED', '952500044212', 'TRANSFER', 2),
(3, '2025-04-09 15:55:25.969000', 'COMPLETED', '952500044212', 'TRANSFER', 3),
(1, '2025-04-09 16:07:57.472000', 'COMPLETED', '952500044212', 'TRANSFER', 4),
(3, '2025-04-09 16:07:57.533000', 'COMPLETED', '952500044212', 'TRANSFER', 5),
(1, '2025-04-09 16:08:49.068000', 'COMPLETED', '952500044212', 'TRANSFER', 6),
(3, '2025-04-09 16:08:49.082000', 'COMPLETED', '952500044212', 'TRANSFER', 7),
(1, '2025-04-09 16:09:29.968000', 'COMPLETED', 'selfAccount', 'WITHDRAWAL', 8),
(3, '2025-04-09 16:09:29.979000', 'COMPLETED', 'selfAccount', 'WITHDRAWAL', 9),
(4, '2025-04-30 19:27:06.828000', 'COMPLETED', 'selfAccount', 'WITHDRAWAL', 10),
(4, '2025-04-30 19:27:56.787000', 'COMPLETED', '952500044212', 'TRANSFER', 11);

