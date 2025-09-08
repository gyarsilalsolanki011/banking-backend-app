# 🏦 Banking App (Spring Boot) REST API
This is a banking application built using Spring Boot and MySQL that provides functionalities for user and account management, transactions, and admin role-based access control.

## 🚀 Features

### 🔹 User Management
- ✅ **Create new user** (JWT Authentication)
- ✅ **View User Details** (Balance, Account Number, Type)
- ✅ **Delete Users**
- ✅ **Get all accounts** of users

### 🔹 Account Management
- ✅ **Creats Accounts**
- ✅ **Retrieve account details**
- ✅ **Delete Accounts**

### 🔹 Transaction Management
- ✅ **Deposit & Withdraw Money**
- ✅ **Transfer Funds** between accounts
- ✅ **View Transaction History**

### 🔹 Admin Features
- ✅ **Manage all Users & Accounts**
- ✅ **Approve or Monitor Transactions**
- ✅ **View All Users & Their Balances**

### 🔹 Security & Tech Stack
- ✅ **Dto's and Mappers** to secure schema
- ✅ **Spring Boot with MySQL** for Backend
- ✅ **Spring Security & JWT** for Authentication
- ✅ **Lombok & JPA (Hibernate)** for Database Management
- ✅ **Role-Based Access Control (ADMIN, MANAGER, SUPER_ADMIN)**


## 🛠️ Tech Stack

- **Backend**: Java, Spring Boot, Spring Security, JWT
- **Database**: MySQL, Hibernate (JPA)
- **Security**: JWT Authentication, Password Encryption (BCrypt)
- **API Testing**: Swagger, Postman
- **Build Tool**: Maven


## 📌 Installation & Setup

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/gyarsilalsolanki011/banking-app.git
cd banking-app
```

### 2️⃣ Configure Database (MySQL)
- Create a database in MySQL:
```sql
CREATE DATABASE banking_db;
```
- Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Build & Run the Application
```sh
mvn clean install
mvn spring-boot:run
```

---

## 🔗 API Endpoints

| Method               | Endpoint                                 | Description                                                   |
| -------------------- | ---------------------------------------- | ------------------------------------------------------------- |
| **Public APIs**      |                                          |                                                               |
| `POST`               | `/api/auth/register`                     | Register a new admin                                          |
| `POST`               | `/api/auth/login`                        | Admin login & get JWT token                                   |
| **User APIs**        |                                          |                                                               |
| `POST`               | `/api/users`                             | Create a new user                                             |
| `GET`                | `/api/users/{id}`                        | Get user details by ID                                        |
| `DELETE`             | `/api/users/{id}`                        | Delete a user                                                 |
| `GET`                | `/api/users/{id}/accounts`               | Get all accounts by userId                                    |
| **Account APIs**     |                                          |                                                               |
| `POST`               | `/api/accounts`                          | Create a new account                                          |
| `GET`                | `/api/accounts/{id}`                     | Get account details by ID                                     |
| `DELETE`             | `/api/accounts/{id}`                     | Delete an account                                             |
| **Transaction APIs** |                                          |                                                               |
| `POST`               | `/api/transactions/deposit/{accountId}`  | Deposit money into account                                    |
| `POST`               | `/api/transactions/withdraw/{accountId}` | Withdraw money from account                                   |
| `POST`               | `/api/transactions/transfer`             | Transfer funds between accounts (sourceId & targetId in body) |
| `GET`                | `/api/transactions/account/{accountId}`  | Get all transactions by accountId                             |
| **Admin APIs**       |                                          |                                                               |
| `GET`                | `/api/admins/users`                      | Get all users                                                 |
| `GET`                | `/api/admins/accounts`                   | Get all accounts                                              |
| `GET`                | `/api/admins/transactions`               | Get all transactions                                          |
| `GET`                | `/api/admins/{id}`                       | Get admin details by ID                                       |
| `DELETE`             | `/api/admins/{id}`                       | Delete admin by ID                                            |
| `PUT`                | `/api/admins/approve/{transactionId}`    | Approve big withdrawals                                       |


### ***check out postman endpoints***: 
- This collection includes all the endpoints which are there in the banking app.
- Two endpoints are public and all the others are authenticated

## Role-Based Access Control
- ADMIN: Full access to all functionalities.
- MANAGER: Can view and manage users and transactions.
- SUPER_ADMIN: Highest privileges with full control over the system.

## 🤝 Contributing
Contributions are welcome! Follow these steps:
1. Fork the repository
2. Create a new branch (`feature-xyz`)
3. Commit your changes
4. Push to the branch and open a Pull Request


## 📜 License
This project is **open-source** under the MIT License.


## 📞 Contact
For any queries, feel free to reach out:
- 📧 Email: gyarsilalsolanki011@gmail.com
- 🔗 LinkedIn: [Your Profile](https://linkedin.com/in/gyarsilalsolanki)

🚀 **Happy Coding!**

