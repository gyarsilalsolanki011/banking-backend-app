# 🏦 Banking App (Spring Boot) REST API
This is a banking application built using Spring Boot and MySQL that provides functionalities for user and account management, transactions, and admin role-based access control.

## ✨ Features
- 👤 **User Management** – Create, view, and delete users with JWT authentication
- 🏦 **Account Management** – Manage accounts (create, view, delete)
- 💰 **Transactions** – Deposit, withdraw, transfer funds, and view history
- 🛡 **Admin Control** – Role-based access (ADMIN, MANAGER, SUPER_ADMIN) to manage users, accounts, and transactions
- 🔐 **Security** – Spring Security, JWT authentication, and password encryption
- 📄 **API Documentation** – Interactive Swagger/OpenAPI docs
- 🐳 **dockerized application** - Easily deployable with Docker

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

## 🚀 Running the App with Docker
```bash
# 1️⃣ Build and start containers
docker-compose up -d --build

# 2️⃣ Check logs
docker-compose logs -f

# 3️⃣ Stop and remove containers
docker-compose down
```
### `NOTE`: Make sure to add .env file in docker folder with following content:
```env
# MySQL Database Configuration
MYSQL_ROOT_PASSWORD=your_root_password
MYSQL_DATABASE=your_database_name
MYSQL_USER=your_user
MYSQL_PASSWORD=your_password
HOST_MYSQL_PORT=3307

# Spring Boot Database Config
DATABASE_URL=jdbc:mysql://mysql-banking:3306/${MYSQL_DATABASE}
DATABASE_USERNAME=${MYSQL_USER}
DATABASE_PASSWORD=${MYSQL_PASSWORD}

# JWT Configuration
JWT_SECRET_KEY=your_jwt_secret_key
JWT_EXPIRATION_TIME=86400000
SMS_API_KEY=your_sms_api_key

# Volume Paths
MYSQL_DATA_PATH=/absolute/path/to/mysql-data
```
**Also visit docker images**:[`gyarsilalsolanki011/banking-backend-app`](https://hub.docker.com/r/gyarsilalsolanki011/banking-backend-app)


## 🤝 Contributing
Contributions are welcome! Follow these steps:
1. Fork the repository
2. Create a new branch (`feature-xyz`)
3. Commit your changes
4. Push to the branch and open a Pull Request


## 📜 License
This project is **open-source** under the [MIT License](LICENSE.md).


## 👨‍💻 Developer

`Gyarsilal Solanki`
- [![LinkedIn](https://img.shields.io/badge/LinkedIn-%230A66C2.svg?logo=LinkedIn&logoColor=white)](https://www.linkedin.com/in/gyarsilal-solanki) [![GitHub](https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=white)](https://github.com/gyarsilalsolanki011) [![Instagram](https://img.shields.io/badge/Instagram-%23E4405F.svg?logo=Instagram&logoColor=white)](https://instagram.com/itz_gsl_tiger)
- [![WhatsApp](https://img.shields.io/badge/WhatsApp-%2325D366.svg?logo=whatsapp&logoColor=white)](https://api.whatsapp.com/send/?phone=919111852267) [![Gmail](https://img.shields.io/badge/Email-D14836?logo=gmail&logoColor=white)](mailto:gyarsilalsolanki011@gmail.com)


Join us to discuss ideas, share feedback, and coordinate contributions:  
[![Join Discord](https://img.shields.io/discord/1405808666179014697?color=4CBB17&label=Join%20Us%20on%20Discord&logo=discord&logoColor=blue)](https://discord.gg/Zrc9x3ts)

***If you find this project helpful, consider giving it a ⭐ to support!***
