# Blog-App

## Project Introduction
Blog-App is a Java-based blogging platform that allows users to create, edit, view, and delete blog posts. This application is built using Spring Boot, providing a robust backend system and a dynamic web interface.

## Project Information

1. *Repository Description*: Java-based blogging platform for creating and managing posts.
2. *Architecture Details*: Built with Spring Boot and utilizes Java Spring framework components.
3. *Branching Information*: The master branch uses Spring Boot 2.7+ and JDK 8; the dev-v3 branch is upgraded to Spring Boot 3.2+ and JDK 17.

## System Architecture
Blog-App is designed with a clean, scalable architecture in mind:
- *Web Layer*: Handles HTTP requests and responses.
- *Service Layer*: Provides core business logic.
- *Data Access Layer*: Manages database operations.
- *Security Layer*: Ensures authentication and protects routes.

## Technologies Used
- *Spring Boot*: Simplifies the development of new Spring applications.
- *Java*: Offers portability and extensive support for web applications.
- *Maven*: Manages dependencies and project lifecycle.
- *MySQL*: Handles data storage with reliable transaction support.
- *Spring Security*: Ensures secure authentication and authorization.

## Project Structure
- *src/main/java*: Java source files for application logic.
- *src/main/resources*: Configuration files and resources.
- *src/test/java*: Source files for unit and integration tests.

## Quick Start
Clone the repository and navigate to the directory:
```bash
git clone https://github.com/VuHSO-edu/blog-app.git
cd blog-app
./mvnw spring-boot:run
