⚙️ LearnSphere – Backend (Spring Boot + MySQL)

The Spring Boot backend service for LearnSphere, handling authentication, course management, payments, and user progress tracking. It exposes REST APIs consumed by the React frontend.

✨ Features

🔐 User Authentication & Authorization (JWT)

👨‍🏫 Instructor APIs to create/manage courses

🎓 Student APIs for enrollment & progress

💳 Payment API integration

📊 Admin APIs for reports & analytics

🗄️ MySQL database integration

🛠 Tech Stack

Backend Framework: Spring Boot

Database: MySQL

ORM: Hibernate / JPA

Security: Spring Security + JWT

Build Tool: Maven

API: RESTful

⚙️ Installation & Setup
git clone https://github.com/Anish1424/learnsphere-backend.git
cd learnsphere-backend
mvn clean install


Create a MySQL database:

CREATE DATABASE learnsphere;


Update application.properties with your DB credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/learnsphere
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
jwt.secret=your-secret-key


Run the backend:

mvn spring-boot:run


Backend will run on: http://localhost:8080
