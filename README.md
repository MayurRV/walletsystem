# walletsystem
Basic CRUD operation Transaction Based REST API used project
# 🚀 Wallet Transaction System

A Spring Boot backend system that manages client wallets and order transactions with external fulfillment API integration.

---

## 📖 Project Overview

This project implements a transactional wallet system where:

- Admin can credit or debit client wallets
- Clients can create orders
- Wallet balance is deducted atomically
- External fulfillment API is called
- Fulfillment ID is stored with the order
- All transactions are logged in a ledger table

This project demonstrates:

- Transaction Management (@Transactional)
- REST API Design
- PostgreSQL Integration
- External API Integration
- Error Handling & Validation
- Clean Architecture (Controller → Service → Repository)

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Hibernate
- Lombok
- Maven

---

## 🗄️ Database Schema

### Wallet Table

| Column     | Type   |
|------------|--------|
| client_id  | bigint (PK) |
| balance    | double |

---

### Orders Table

| Column          | Type   |
|-----------------|--------|
| id              | bigint (PK) |
| client_id       | bigint |
| amount          | double |
| status          | CREATED / FULFILLED / FAILED |
| fulfillment_id  | varchar |

---

### Ledger Table

| Column     | Type   |
|------------|--------|
| id         | bigint (PK) |
| client_id  | bigint |
| amount     | double |
| type       | CREDIT / DEBIT |

---

## 🔥 Features

✔ Admin wallet credit  
✔ Admin wallet debit  
✔ Client order creation  
✔ Atomic transaction handling  
✔ External fulfillment API integration  
✔ Fulfillment ID storage  
✔ Wallet balance check  
✔ Order access restriction  
✔ Ledger transaction logging  
✔ DTO validation  
✔ Global exception handling  

---

## ⚙️ Setup Instructions

### 1️⃣ Clone Repository

```bash
git clone https://github.com/yourusername/wallet-transaction-system.git
```

---

### 2️⃣ Create PostgreSQL Database

```sql
CREATE DATABASE wallet_system;
```

---

### 3️⃣ Configure application.properties

Update the following:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/wallet_system
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```

---

### 4️⃣ Run the Application

Using STS or terminal:

```bash
mvn spring-boot:run
```

Application runs at:

```
http://localhost:8080
```

---

## 📡 API Endpoints

---

### 🔹 Admin – Credit Wallet

POST `/admin/wallet/credit`

Body:
```json
{
  "clientId": 1,
  "amount": 1000
}
```

---

### 🔹 Admin – Debit Wallet

POST `/admin/wallet/debit`

Body:
```json
{
  "clientId": 1,
  "amount": 200
}
```

---

### 🔹 Client – Create Order

POST `/orders`

Header:
```
client-id: 1
```

Body:
```json
{
  "amount": 200
}
```

---

### 🔹 Get Order Details

GET `/orders/{id}`

Header:
```
client-id: 1
```

---

### 🔹 Get Wallet Balance

GET `/wallet/balance`

Header:
```
client-id: 1
```

---

## 🔄 Transaction Flow

1. Admin credits wallet
2. Client creates order
3. Wallet balance is validated
4. Amount deducted atomically
5. Order created
6. External fulfillment API called
7. Fulfillment ID stored
8. Ledger entry recorded

---

## 🧠 Key Design Decisions

- Used `@Transactional` for atomic operations
- Used Enum for order status consistency
- Used Global Exception Handler for clean API responses
- Used DTO with validation for request safety
- Ensured client authorization while fetching order

---

## 🚀 Future Improvements

- JWT Authentication
- Docker Support
- Swagger Documentation
- Unit & Integration Testing
- Concurrency control with optimistic locking

---

## 👨‍💻 Author

Mayur Vetal  
Java | Spring Boot | Backend Developer
