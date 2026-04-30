igin# User Service — ShopSphere

Short, practical README for the User Service. This service manages user data and authentication for the ShopSphere platform.

## What this service does
- Provides  operations for users
- Issues JWTs for authentication (login)
- Secures endpoints with role-based access control (ADMIN / CUSTOMER)
- Hashes passwords with BCrypt
- Uses DTOs and MapStruct for clean object mapping

This README reflects the current implementation: JWT-based stateless auth, RBAC via Spring Security annotations, and a MySQL database (usually run in Docker during local development).

## Tech stack
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (custom token handling)
- MapStruct
- Lombok
- MySQL (commonly run in Docker)
- Gradle

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

Primary configuration file: `src/main/resources/application.yml`.

Important properties (example placeholders):

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3307/user_db
    username: root
    password: 1234

jwt:
  secret: your-secret-key
  expiration: 3600000 # ms
```

Replace placeholders with secure values for production (don’t commit secrets).

## Running locally

1) Start MySQL (the repository includes a `docker-compose.yml` that spins a MySQL instance):

```powershell
docker compose up -d
```

2) Run the application

On Windows (recommended since this workspace is Windows-based):

```powershell
.\gradlew.bat bootRun
```

On Unix/macOS:

```bash
./gradlew bootRun
```

Useful notes:
- If you prefer a jar, build with `./gradlew bootJar` and run the generated jar.
- If Docker is not available, point `spring.datasource` to a local MySQL instance and create the DB.

## API (implemented endpoints)

Base path: `/v1/api/users`

Authentication:
- POST /v1/api/users/login
  - Body: { "email": "user@example.com", "password": "secret" }
  - Response: { "token": "<jwt>", "expiresIn": 3600000 }

User management (protected):
- GET /v1/api/users           — list users (requires appropriate role)
- GET /v1/api/users/{id}     — get a single user by id
- POST /v1/api/users         — create a new user
- PUT /v1/api/users/{id}     — update user data
- PUT /v1/api/users/{id}/password — change password

Authentication header for protected endpoints:

```
Authorization: Bearer <token>
```

Example curl (login):

```bash
curl -X POST http://localhost:8080/v1/api/users/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@example.com","password":"admin"}'
```

Example: call a protected endpoint with the token

```bash
curl http://localhost:8080/v1/api/users -H "Authorization: Bearer <token>"
```

## Security and implementation notes

- Authentication: JWT tokens are generated on successful login and must be sent in the Authorization header.
- Passwords are stored hashed with BCrypt.
- Role-based access control is enforced via Spring Security annotations (e.g. `@PreAuthorize`).
- The service is stateless — no HTTP session is used.

## Tests

Unit tests and simple integration tests are available under `src/test/java`. Run tests with:

```powershell
.\gradlew.bat test
```

## Troubleshooting

- Database connection errors: ensure the MySQL container is up and credentials match `application.yml`.
- Missing JWT secret: set `jwt.secret` before running in non-development environments.
- Port conflicts: the service defaults to port 8080 — change `server.port` in `application.yml` if needed.

## Where to look in the code

- Controllers: `src/main/java/.../controller`
- Security config: `src/main/java/.../Config` (contains `SecurityConfig` / filters)
- DTOs and mappers: `src/main/java/.../dto` and `.../mapper`
- Repositories & entities: `src/main/java/.../repository` and `.../entity`

If you want, I can also update README examples to include real DTO field names from the code (email, name, roles) — tell me and I’ll pull those directly from the sources.
