# 🖼️ XMeme – Meme Sharing Backend

**XMeme** is a simple backend web application where users can post and view memes. Built using Spring Boot and MongoDB, this project focuses on API design, modular architecture, and clean backend implementation.

## 🔍 Overview

- Built from scratch using Spring Boot and MongoDB.
- Designed and implemented REST APIs to post and fetch memes.
- Includes a minimal HTML frontend for interaction with the backend.

## 🎯 Scope of Work

- Created a backend server using Spring Boot with REST API handlers.
- Implemented:
  - `POST /memes` – to post new memes
  - `GET /memes` – to retrieve the latest 100 memes
  - `GET /memes/{id}` – to fetch an individual meme by ID
- Used MongoDB to persist meme data.
- Designed APIs to return: `name`, `caption`, and `url`.
- Followed proper HTTP standards for status codes and error messages.
- Tested endpoints using Postman.
- Wrote modular code using MVCS architecture.

## 💻 Technologies Used

- Java 11+
- Spring Boot
- MongoDB
- REST API
- HTML/CSS (frontend)
- Jackson
- Lombok

## 🚀 How to Run

### Prerequisites

- Java 11+
- Maven or Gradle
- MongoDB running on `localhost:27017`

### Setup

1. Rename `application-template.properties` to `application.properties` in `src/main/resources/`.
2. Update MongoDB URI if needed:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/memesDB
server.port=8080
```

### Run the App

```bash
./mvnw spring-boot:run
```

Visit frontend at: [http://localhost:8080/index.html](http://localhost:8080/index.html)

## 🧪 API Endpoints

| Method | Endpoint         | Description                      |
|--------|------------------|----------------------------------|
| POST   | `/memes`         | Submit a new meme                |
| GET    | `/memes`         | Get the latest 100 memes         |
| GET    | `/memes/{id}`    | Get details of a meme by its ID  |