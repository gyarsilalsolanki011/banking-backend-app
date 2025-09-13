# 🏦 Banking App (Spring Boot) REST API
This is a banking application built using Spring Boot and MySQL that provides functionalities for user and account management, transactions, and admin role-based access control.

## ✨ Features
- 👤 **User Management** – Create, view, and delete users with JWT authentication
- 🏦 **Account Management** – Manage accounts (create, view, delete)
- 💰 **Transactions** – Deposit, withdraw, transfer funds, and view history
- 🛡 **Admin Control** – Role-based access (ADMIN, MANAGER, SUPER_ADMIN) to manage users, accounts, and transactions
- 🔐 **Security** – Spring Security, JWT authentication, and password encryption
- 📄 **API Documentation** – Interactive Swagger/OpenAPI docs

## 🔗 API Endpoints

#### 🌍 Public Collections
| **Method** | **Endpoint**                       | **Description**                                           |
|------------|------------------------------------|-----------------------------------------------------------|
| **POST**   | `/api/auth/register-admin`         | Register a new admin account.                             |
| **POST**   | `/api/auth/register-user`          | Register a new user account.                              |
| **POST**   | `/api/auth/request-online-banking` | Request online banking access for a user.                 |
| **POST**   | `/api/auth/login`                  | Authenticate and retrieve JWT token for further requests. |
| **POST**   | `/api/auth/message-service`        | Send notification/message service (if applicable).        |

#### 👤 User Collection
| **Method** | **Endpoint**                            | **Description**                            |
|------------|-----------------------------------------|--------------------------------------------|
| **PUT**    | `/api/users/update/{id}`                | Update user details by ID.                 |
| **DELETE** | `/api/users/delete/{id}`                | Delete a user account permanently.         |
| **GET**    | `/api/users/{id}`                       | Get user details by ID.                    |
| **GET**    | `/api/users/online-banking-status/{id}` | Check online banking activation status.    |
| **GET**    | `/api/users/all-accounts/{id}`          | Retrieve all accounts belonging to a user. |

#### 🏦 Account Collection
| Method     | Endpoint               | Description               |
|------------|------------------------|---------------------------|
| **POST**   | `/api/accounts/create` | Create a new account      |
| **GET**    | `/api/accounts/{id}`   | Get account details by ID |
| **DELETE** | `/api/accounts/{id}`   | Delete an account         |

#### 💰 Transaction Collection
| **Method** | **Endpoint**                                 | **Description**                                      |
|------------|----------------------------------------------|------------------------------------------------------|
| **POST**   | `/api/transactions/deposit/{accountId}`      | Deposit funds into an account.                       |
| **POST**   | `/api/transactions/withdraw/{accountId}`     | Withdraw funds from an account.                      |
| **POST**   | `/api/transactions/transfer/{fromId}/{toId}` | Transfer funds between two accounts.                 |
| **GET**    | `/api/transactions/account/{accountId}`      | Retrieve transaction history for a specific account. |

#### 🛠 Admin Collection
| **Method** | **Endpoint**                                     | **Description**                               |
|------------|--------------------------------------------------|-----------------------------------------------|
| **POST**   | `/api/admins/create`                             | Creates a new admin user.                     |
| **PUT**    | `/api/admins/update/{id}`                        | Updates details of an existing admin by ID.   |
| **DELETE** | `/api/admins/delete/{id}`                        | Deletes an admin by ID (irreversible).        |
| **GET**    | `/api/admins/{id}`                               | Fetches details of a specific admin by ID.    |
| **POST**   | `/api/admins/approve-withdrawal/{transactionId}` | Approves high-value withdrawal transactions.  |
| **POST**   | `/api/admins/approve-online-banking/{userId}`    | Activates online banking access for a user.   |
| **POST**   | `/api/admins/deactivate-online-banking/{userId}` | Deactivates online banking access for a user. |
| **GET**    | `/api/admins/all-users`                          | Retrieves a list of all registered users.     |
| **GET**    | `/api/admins/all-accounts`                       | Retrieves all bank accounts across users.     |


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
### 4️⃣ Access the Application
- The application will be running at: `http://localhost:8080`
- Swagger API docs: `http://localhost:8080/swagger-ui.html`

### Setup Postman Collection
- Download the Postman Collections from setup 
- Import all collections into Postman and execute each API's requests.
  - [`Account Collection.postman_collection.json`](/setup/Account%20Collection.postman_collection.json)
  - [`Admin Collection.postman_collection.json`](/setup/Admin%20Collection.postman_collection.json)
  - [`Auth Collection.postman_collection.json`](/setup/Auth%20Collection.postman_collection.json)
  - [`Transaction Collection.postman_collection.json`](/setup/Transaction%20Collection.postman_collection.json)
  - [`User Collection.postman_collection.json`](/setup/User%20Collection.postman_collection.json)

## 🤝 Contributing
Contributions are welcome! Follow these steps:
1. Fork the repository
2. Create a new branch (`feature-xyz`)
3. Commit your changes
4. Push to the branch and open a Pull Request


## 📜 License
This project is **open-source** under the [MIT License](LICENSE.md).


## 📞 Contact
For any queries, feel free to reach out:
- 📧 Email: gyarsilalsolanki011@gmail.com
- 🔗 LinkedIn: [Your Profile](https://linkedin.com/in/gyarsilalsolanki)

🚀 **Happy Coding!**

