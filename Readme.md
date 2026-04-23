# User Service – ShopSphere

## Overview

This is the User Service for the ShopSphere e-commerce project.

It handles everything related to users — creating users, updating them, and now also authentication using JWT.

Initially this was just a basic CRUD service, but later I extended it with proper security (JWT + RBAC).

---

## Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* Spring Security
* JWT
* MapStruct
* Lombok
* MySQL (Docker)
* Gradle

---

## Features

### User APIs

* Create user
* Get user by ID
* Get all users
* Update user

### Authentication

* Login API (JWT token generation)
* Password hashing using BCrypt
* Stateless authentication

### Authorization

* Role-based access (ADMIN / CUSTOMER)
* Secured endpoints using Spring Security
* Admin-only operations supported

### Other

* DTO-based design
* MapStruct for mapping
* Global exception handling
* Validation added

---

## Architecture

Simple layered structure:

```text
Controller → Service → Repository → DB
```

Security flow:

```text
Request → JWT Filter → SecurityContext → Controller
```

---

## Configuration

Config file:

```
src/main/resources/application.yml
```

### Database

* URL: `jdbc:mysql://localhost:3307/user_db`
* Username: root
* Password: 1234

### JWT

```yaml
jwt:
  secret: your-secret-key
  expiration: 3600000
```

---

## How to Run

Start MySQL:

```bash
docker compose up -d
```

Run app:

```bash
./gradlew bootRun
```

---

## API Endpoints

Base URL: `/v1/api/users`

### Public

* `POST /login` → login user

---

### Protected

* `GET /` → get all users
* `GET /{id}` → get user
* `POST /` → create user
* `PUT /{id}` → update user
* `PUT /{id}/password` → update password

---

## Auth Usage

For protected APIs:

```
Authorization: Bearer <token>
```

---

## Notes

* JWT is used, no session management
* Role is stored in DB and used for authorization
* Default role is CUSTOMER
* RBAC is implemented using `@PreAuthorize`

---

## TODOs

* Refresh tokens
* API Gateway integration
* Eureka service discovery
* Logging & monitoring

---


