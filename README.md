# 🏦 Banking App (Spring Boot) REST API
This is a banking application built using Spring Boot and MySQL that provides functionalities for user and account management, transactions, and admin role-based access control.

---

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

---

## 🛠️ Tech Stack

- **Backend**: Java, Spring Boot, Spring Security, JWT
- **Database**: MySQL, Hibernate (JPA)
- **Security**: JWT Authentication, Password Encryption (BCrypt)
- **API Documentation**: Swagger
- **Build Tool**: Maven

---

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

### **Public APIs**
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/auth/register` | `POST` | Register a new admin |
| `/api/auth/login` | `POST` | Admin login & get JWT token |

### **User APIs**
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/users/create` | `POST` | create a new user |
| `/api/users/all-accounts/{id}` | `GET` | Get all accounts by userId |
| `/api/users/{id}` | `GET` | Get user details by userId |
| `/api/users/{id}` | `DELETE` | Delete a user |

### **Account APIs**
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/accounts/create` | `POST` | Create a new account |
| `/api/accounts/{id}` | `GET` | Get account details by ID |
| `/api/accounts/{id}` | `DELETE` | Delete an account |


### **Transaction APIs**
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/transactions/deposit/{id}` | `POST` | Deposit money by accountId |
| `/api/transactions/withdraw/{id}` | `POST` | Withdraw money by accountId |
| `/api/transactions/{id}/transfer/{id}` | `POST` | Transfer funds accountId |
| `/api/transactions/account/{id}` | `GET` | Get all transactions by accountId |

### **Admin APIs**
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/admins/all-users` | `GET` | Get all users |
| `/api/admins/all-accounts` | `GET` | Get all accounts |
| `/api/admins/all-transactions` | `GET` | Get all transactions |
| `/api/admins/{id}` | `GET` | Get an admin by id |
| `/api/admins/delete/{id}` | `GET` | delete admin by Id |
| `/api/admins/approve/{id}` | `GET` | approve big withdrawals |

## Role-Based Access Control
- ADMIN: Full access to all functionalities.
- MANAGER: Can view and manage users and transactions.
- SUPER_ADMIN: Highest privileges with full control over the system.

---

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

---

🚀 **Happy Coding!**

