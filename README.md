# 🏦 Banking App (Spring Boot)

A secure and efficient **Spring Boot** banking application that allows users to manage their accounts, perform transactions, and provides admin control over the system.

---

## 🚀 Features

### 🔹 User Features
- ✅ **User Registration & Login** (JWT Authentication)
- ✅ **View Account Details** (Balance, Account Number, Type)
- ✅ **Deposit & Withdraw Money**
- ✅ **Transfer Funds** between accounts
- ✅ **View Transaction History**

### 🔹 Admin Features
- ✅ **Manage Users & Accounts**
- ✅ **Approve or Monitor Transactions**
- ✅ **View All Users & Their Balances**

### 🔹 Security & Tech Stack
- ✅ **Spring Boot with MySQL** for Backend
- ✅ **Spring Security & JWT** for Authentication
- ✅ **Lombok & JPA (Hibernate)** for Database Management
- ✅ **Role-Based Access Control (User/Admin)**

---

## 🛠️ Tech Stack

- **Backend**: Java, Spring Boot, Spring Security, Hibernate (JPA)
- **Database**: MySQL
- **Security**: JWT Authentication, Password Encryption (BCrypt)
- **API Documentation**: Swagger
- **Build Tool**: Maven

---

## 📌 Installation & Setup

### 1️⃣ Clone the Repository
```sh
 git clone https://github.com/your-repo/banking-app.git
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

### **User APIs**
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/users/register` | `POST` | Register a new user |
| `/api/users/login` | `POST` | User login & get JWT token |
| `/api/users/{id}` | `GET` | Get user details by ID |
| `/api/users/{id}` | `PUT` | Update user details |
| `/api/users/{id}` | `DELETE` | Delete a user |

### **Account APIs**
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/accounts/create` | `POST` | Create a new account |
| `/api/accounts/{id}` | `GET` | Get account details by ID |
| `/api/accounts/deposit` | `POST` | Deposit money |
| `/api/accounts/withdraw` | `POST` | Withdraw money |
| `/api/accounts/transfer` | `POST` | Transfer funds |

### **Admin APIs**
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/admin/users` | `GET` | Get all users |
| `/api/admin/accounts` | `GET` | Get all accounts |
| `/api/admin/transactions` | `GET` | Get all transactions |

---

## 📜 License
This project is **open-source** under the MIT License.

---

## 🤝 Contributing
Contributions are welcome! Follow these steps:
1. Fork the repository
2. Create a new branch (`feature-xyz`)
3. Commit your changes
4. Push to the branch and open a Pull Request

---

## 📞 Contact
For any queries, feel free to reach out:
- 📧 Email: your-email@example.com
- 🔗 LinkedIn: [Your Profile](https://linkedin.com/in/your-profile)

---

🚀 **Happy Coding!**

