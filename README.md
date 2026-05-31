# 🏙️ CityFix — Civic Complaint Management Platform

A full-stack web application that allows citizens to report urban issues like garbage, road damage, and broken streetlights to local authorities. Admins can manage, track, and resolve complaints in real time.

🔗 **Live Demo:** [city-fixsumit.vercel.app](https://city-fixsumit.vercel.app)

---

## 🚀 Features

### Citizens (USER)
- Register & login with JWT authentication
- Submit complaints with title, description, category, location & image
- Track complaint status in real time (PENDING → IN_PROGRESS → RESOLVED)
- View all personal complaints with full details

### Admin (ADMIN)
- View all complaints from all users
- Update complaint status (In Progress / Resolved)
- Delete complaints
- Pie chart analytics by status
- Search and filter complaints

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | React, Vite, Tailwind CSS |
| Backend | Spring Boot, Java |
| Database | PostgreSQL (Supabase) |
| Auth | JWT (JSON Web Tokens) |
| Storage | Image Upload via URL |
| Deployment | Docker, Render (Backend), Vercel (Frontend) |


---

## 🔐 API Endpoints

### Auth
| Method | Endpoint | Access |
|--------|----------|--------|
| POST | `/auth/signup` | Public |
| POST | `/auth/login` | Public |

### Complaints
| Method | Endpoint | Access |
|--------|----------|--------|
| POST | `/complaints` | USER |
| GET | `/complaints/my` | USER |
| GET | `/complaints/my/{id}` | USER |

### Admin
| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/admin/complaints` | ADMIN |
| PUT | `/admin/complaints/{id}/status` | ADMIN |
| DELETE | `/admin/complaints/{id}` | ADMIN |
| GET | `/admin/complaints/users` | ADMIN |

---

## ⚙️ Setup Locally

### Backend
```bash
# Clone the repo
git clone https://github.com/DubeySumit25/CityFix-Backend.git
cd CityFix-Backend

# Add your environment variables in application.properties
spring.datasource.url=YOUR_SUPABASE_JDBC_URL
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

# Run
./mvnw spring-boot:run
```

### Frontend
```bash
git clone https://github.com/DubeySumit25/CityFix-Frontend.git
cd CityFix-Frontend

# Add .env.local
VITE_API_URL=http://localhost:8080

npm install
npm run dev
```

---

## 🐳 Docker

```bash
docker build -t cityfix-backend .
docker run -p 8080:8080 cityfix-backend
```

---

## 📸 Screenshots

Login:
<img width="1145" height="903" alt="image" src="https://github.com/user-attachments/assets/f97c852c-8964-4d2a-a583-ca97f23f95f1" />

Admin-dashboard:
<img width="1889" height="834" alt="image" src="https://github.com/user-attachments/assets/1af1b270-1ae1-42e1-b8d9-f1001af89d54" />

User-dashboard:
<img width="1909" height="600" alt="image" src="https://github.com/user-attachments/assets/eb8855a9-00c1-4eeb-a68b-26c7583aaaf7" />

My-complaints:
<img width="1890" height="783" alt="image" src="https://github.com/user-attachments/assets/b923102a-a398-4290-bcc6-3274c6bfd30d" />

## 🏗️ Architecture

```
┌─────────────────────┐         ┌──────────────────────┐         ┌─────────────────────┐
│   Frontend          │  HTTPS  │   Backend            │  JDBC   │   Database          │
│   React + Vite      │ ──────► │   Spring Boot        │ ──────► │   Supabase          │
│   Vercel            │         │   Docker + Render     │         │   PostgreSQL         │
└─────────────────────┘         └──────────────────────┘         └─────────────────────┘
         │                                │
         │ JWT Token                      │ Spring Security
         │ in localStorage                │ Role-based Access
         ▼                                ▼
    USER / ADMIN                  /auth/** → Public
                                  /complaints/** → USER
                                  /admin/** → ADMIN
```


## 👨‍💻 Author

**Sumit Dubey**  
[LinkedIn](https://www.linkedin.com/in/sumit-dubey-9a0226322/) • [GitHub](https://github.com/DubeySumit25) • [LeetCode](https://leetcode.com/u/anonymousvenom/)
---
