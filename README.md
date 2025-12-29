🌐 LegalHelp – Full-Stack Web Platform

A platform to connect Users with Chartered Accountants, Lawyers, and Consultants

🚀 Overview

LegalHelp is a full-stack web application designed to help users easily connect with CAs, Lawyers, and Consultants for tax filing, legal assistance, and professional services.
It includes role-based registration, authentication, assignment workflows, and a clean, scalable architecture built using modern technologies.

🛠️ Tech Stack
Backend (Spring Boot)

Java 17

Spring Boot

Spring Security (JWT Authentication)

Hibernate / JPA

MySQL / PostgreSQL

ModelMapper

Maven

Frontend (React)

React + Vite

TypeScript

Tailwind CSS

Axios / React Query

Machine Learning (Future Integration)

TensorFlow

Hybrid ML + Rule-based tax prediction approach

📌 Key Features
🔐 Authentication & Authorization

JWT-based login & secure token flow

Role-based access for:

User

CA

Lawyer

Consultant

👥 User & Professional Management

Users can register as CA, Lawyer, Consultant, or Normal User

Auto-creation of respective detail records:

ca_details

lawyer_details

consultant_details

📋 Assignment Module

Admin/system can assign a CA to a user

Assignment table includes:

Title

Description

Status

Date

Service Type (optional)

Full backend + frontend integration

📡 REST APIs

Register/Login

Get list of CAs, Lawyers, Consultants

Assign CA to user

Fetch profile & details

Update services

🎨 Frontend UI Screens

Login / Register

User Dashboard

CA List Page

Assignment Page

Lawyer / Consultant List

Profile

🧠 Hybrid ML Approach

Designed ML concept to predict:

User behavior or category

Service requirement patterns

Final tax calculation handled by backend slab rule engine for accuracy.

🏗️ Architecture

Follows clean Layered Architecture

Uses DTO → Mapper → Entity pattern

Modular service structure

Scalable endpoints ready for microservices migration

## 📁 Project Structure  

### **Backend**
```txt
src/
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
├── mapper/
└── config/
```

### **Frontend**
```txt
src/
 ├─ components/
 ├─ pages/
 ├─ services/
 ├─ hooks/
 ├─ context/
```
🏃 Getting Started
Backend Setup
cd backend
mvn clean install
mvn spring-boot:run

Frontend Setup
cd frontend
npm install
npm run dev

🤝 Contributions

Contributions, issues, and feature requests are welcome!
Feel free to submit a pull request.

⭐ Show Your Support

If you like this project, consider giving it a ⭐ star on GitHub. It helps a lot!

📬 Contact

Ankit
Full-Stack Developer — Spring Boot | React | ML
Let’s connect on LinkedIn!
