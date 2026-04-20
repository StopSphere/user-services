# User Service – ShopSphere

##  Overview

User Service is a core microservice in the ShopSphere e-commerce system.
It is responsible for managing user data and provides basic operations for users.

---

##  Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* MapStruct
* Lombok
* MySQL (Dockerized)
* Gradle

---

##  Features

* Create User
* Get User by ID
* Get All Users
* Update User
* DTO-based request/response handling
* Global exception handling

---

##  Architecture

Follows layered architecture:

```text
Controller → Service → Repository → Database
```

---

##  Configuration

Application configuration is located in:

```
src/main/resources/application.yml
```

### Default Database Config

* URL: `jdbc:mysql://localhost:3307/user_db`
* Username: `${DB_USERNAME:root}`
* Password: `${DB_PASSWORD:1234}`

---

##  Run Locally

### 1. Start MySQL (Docker)

```bash
docker compose up -d
```

### 2. Run Application

```bash
./gradlew bootRun
```

### 3. Run Tests

```bash
./gradlew test
```

---

##  API Endpoints

Base URL: `/v1/api/users`

### Get All Users

* `GET /v1/api/users`

### Get User by ID

* `GET /v1/api/users/{id}`

### Create User

* `POST /v1/api/users`

### Update User

* `PUT /v1/api/users`

---

##  Error Handling

Global exception handling is implemented for:

* Resource not found
* Generic server errors

---

##  Notes

* Authentication (login/JWT) is not implemented yet.
* Service will be extended with authentication and service discovery (Eureka) in future.
