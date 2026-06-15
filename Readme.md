# User Service — ShopSphere

User management and authentication service for the ShopSphere platform.

## What this service does

- Full CRUD operations for users
- Issues JWTs on login for stateless authentication
- Role-based access control (ADMIN / CUSTOMER) via Spring Security
- Passwords hashed with BCrypt
- DTO + MapStruct based clean object mapping
- OpenAPI/Swagger documentation for all endpoints

## Tech stack

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (custom token handling)
- Springdoc OpenAPI (Swagger UI)
- MapStruct
- Lombok
- MySQL (Dockerized)
- Eureka (Service Discovery via `discovery-service`)
- Gradle
- Docker / Docker Compose

## Project structure (high level)

```
src/main/java
  ├─ controller   # REST controllers
  ├─ service      # business logic
  ├─ repository   # JPA repositories
  ├─ entity       # JPA entities
  ├─ dto          # request/response DTOs
  ├─ mapper       # MapStruct mappers
  ├─ security     # filters / config
  └─ exception    # custom exceptions & handlers
```

## Configuration

Main config file: `src/main/resources/application.yml`

```yaml
spring:
  application:
    name: user-service

  datasource:
    url: jdbc:mysql://mysql-container:3306/users_db
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD:1234}
    driver-class-name: com.mysql.cj.jdbc.Driver

jwt:
  secret: ${JWT_KEY:mysupersecretkeymysupersecretkey123}
  expiration: 60000000

eureka:
  client:
    service-url:
      defaultZone: http://discovery-service:8761/eureka
```

> Note: `mysql-container` and `discovery-service` are Docker container/service names from the shared `docker-compose.yml` — the app is expected to run **inside** the `shopsphere-network` Docker network, not directly on your host machine.

> Replace `JWT_KEY`, `DB_USERNAME`, `DB_PASSWORD` via environment variables for production. Never commit real secrets.

## Running locally

This service is part of the ShopSphere microservices stack and depends on `mysql` and `discovery-service` (Eureka) being up — both defined in the shared `docker-compose.yml`.

> The `docker-compose.yml` in this repo is shared across **all** ShopSphere microservices (org-level compose file), currently kept here in the user-service repo.

1) Build the service image (from the user-service project root):

```powershell
docker build -t user-service .
```

2) Start the full stack (MySQL, Eureka, and all services):

```powershell
docker compose up -d
```

This starts `mysql-container`, `discovery-service`, `user-service`, and the other microservices on the `shopsphere-network`.

3) Check logs:

```powershell
docker logs -f user-service
```

### Running without Docker (local dev)

If you want to run `user-service` directly on your machine (e.g. for debugging), override the datasource and Eureka URLs in `application.yml` or via environment variables to point to `localhost` instead of `mysql-container` / `discovery-service`, then:

```powershell
.\gradlew.bat bootRun
```

```bash
./gradlew bootRun
```

## API Documentation (Swagger)

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI spec (JSON): `http://localhost:8080/v3/api-docs`

### How to test secured endpoints in Swagger

1. Call `POST /v1/api/users/login` with valid email/password.
2. Copy the JWT token from the response.
3. Click the **Authorize** button (🔒) at the top of Swagger UI.
4. Paste the token (no need to type `Bearer ` — Swagger adds it automatically).
5. Now you can call any protected endpoint, e.g.:
  - `GET /v1/api/users`
  - `GET /v1/api/users/{id}`
  - `PUT /v1/api/users/{id}`
  - `PUT /v1/api/users/{id}/password`

## API Endpoints

Base path: `/v1/api/users`

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/login` | Login, returns JWT | Public |
| POST | `/` | Create new user | Public |
| GET | `/` | List all users | Required |
| GET | `/{id}` | Get user by id | Required |
| PUT | `/{id}` | Update user | Required |
| PUT | `/{id}/password` | Change password | Required |
| DELETE | `/{id}` | Delete user | ADMIN only |

### Login

```bash
curl -X POST http://localhost:8080/v1/api/users/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@gmail.com","password":"admin123"}'
```

### Calling a protected endpoint

```bash
curl http://localhost:8080/v1/api/users -H "Authorization: Bearer <token>"
```

## Security notes

- JWT tokens are generated on login and must be sent as `Authorization: Bearer <token>`.
- Passwords are stored hashed with BCrypt — never in plain text.
- Role-based access enforced with `@PreAuthorize` (e.g. delete user is ADMIN-only).
- Service is stateless — no HTTP session is used.
- A default admin user (`admin@gmail.com` / `admin123`) is auto-created on startup if it doesn't exist. **TODO: Change this in production.**


