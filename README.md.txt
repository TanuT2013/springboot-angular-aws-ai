# Spring Boot Full Stack App (Java + Angular + JWT + H2)

This is a full stack Spring Boot backend application that supports:

- User Registration & Login
- JWT Token-based Authentication
- H2 In-memory Database Integration
- Swagger UI for API Documentation
- RESTful endpoints secured with Spring Security

---

## 🔧 Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- H2 Database
- JWT (using `jjwt`)
- Swagger/OpenAPI (`springdoc-openapi`)

---

## 🚀 Features

- Register new users (`/api/register`)
- Login and receive JWT token (`/api/login`)
- Fetch all users (`/api/users`)
- Delete user by name (`/api/user/{name}`)
- JWT protection on private endpoints
- View API docs with Swagger

---

## 🛡️ Authentication

Once logged in, you will receive a JWT token like:

Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

Include it in `Authorization` header for all secure endpoints.

Example header:

Authorization: Bearer <your_token_here>
---

## 📂 API Endpoints

| Method | Endpoint           | Description            | Auth Required |
|--------|--------------------|------------------------|----------------|
| POST   | `/api/register`    | Register a new user    | ❌ No          |
| POST   | `/api/login`       | Login and get JWT token| ❌ No          |
| GET    | `/api/users`       | Get list of users      | ✅ Yes         |
| DELETE | `/api/user/{name}` | Delete user by name    | ✅ Yes         |

---

## 🚀 Run Locally

### Prerequisites

- Java 17+
- Maven 3.x

### Steps

```bash
# Clone the repository
git clone https://github.com/your-username/your-repo-name.git

# Navigate to project directory
cd your-repo-name

# Build and run
mvn spring-boot:run
