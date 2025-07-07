# Spring Boot JWT Authentication API

A simple REST API built with **Spring Boot**, **JWT-based authentication**, **H2 in-memory database**, and **Spring Security**.

## 🚀 Features

- User registration with password encryption
- JWT-based login authentication
- Protected endpoints requiring a valid JWT token
- In-memory H2 database for quick testing
- Compatible with Spring Boot 3.x / 6.x security standards

---

## 🛠 Technologies Used

- Java 17+
- Spring Boot
- Spring Security
- JWT (JJWT)
- H2 Database
- Maven

---

## 🔐 API Endpoints

| Endpoint           | Method | Description                            | Auth Required |
|--------------------|--------|----------------------------------------|----------------|
| `/api/register`    | POST   | Register a new user                    | ❌ No          |
| `/api/login`       | POST   | Authenticate and receive JWT token     | ❌ No          |
| `/api/users`       | GET    | Get list of all users                  | ✅ Yes         |
| `/api/user/{name}` | DELETE | Delete a user by name                  | ✅ Yes         |
| `/h2-console`      | GET    | View H2 database console               | ❌ No          |

---

## 🧪 How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/your-repo-name.git
   cd your-repo-name
