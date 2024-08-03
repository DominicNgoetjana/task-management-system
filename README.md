# Task Management API

This project is a simple RESTful API for a Task Management System built with Spring Boot.

## Overview

The Task Management API allows users to create, read, update, and delete tasks. It's built using Spring Boot and uses an H2 in-memory database for data storage.

## Prerequisites

- Java 17 or later
- Maven 3.6.3 or later

## Setup

1. Clone the repository:
   ```
   git clone https://github.com/DominicNgoetjana/task-management-system.git
   ```

2. Navigate to the project directory:
   ```
   cd task-management-api
   ```

3. Build the project:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

The application will start running at `http://localhost:8080`.

## API Endpoints

- GET `/api/tasks`: Retrieve all tasks
- POST `/api/tasks`: Create a new task
- GET `/api/tasks/{id}`: Retrieve a specific task
- PUT `/api/tasks/{id}`: Update a specific task
- DELETE `/api/tasks/{id}`: Delete a specific task

## Usage

Here are some example curl commands to interact with the API:

1. Create a new task:
   ```
   curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{"title":"Complete project","description":"Finish the task management API","dueDate":"2023-12-31T23:59:59","status":"TODO"}'
   ```

2. Get all tasks:
   ```
   curl http://localhost:8080/api/tasks
   ```

3. Get a specific task (replace {id} with the actual task id):
   ```
   curl http://localhost:8080/api/tasks/{id}
   ```

4. Update a task:
   ```
   curl -X PUT http://localhost:8080/api/tasks/{id} -H "Content-Type: application/json" -d '{"title":"Updated task","description":"This task has been updated","dueDate":"2023-12-31T23:59:59","status":"IN_PROGRESS"}'
   ```

5. Delete a task:
   ```
   curl -X DELETE http://localhost:8080/api/tasks/{id}
   ```

## Database

This project uses an H2 in-memory database. You can access the H2 console at `http://localhost:8080/h2-console`. Use the following details to log in:

- JDBC URL: `jdbc:h2:mem:taskdb`
- User Name: `sa`
- Password: (leave blank)

## Contributing

This is a personal project, but suggestions and improvements are welcome. Please open an issue to discuss proposed changes.

## License

This is a personal project created for learning and portfolio purposes. All rights are reserved by Dominic Ngoetjana.

While this project is publicly visible, it is not licensed for distribution or modification without explicit permission. If you're interested in using any part of this code or contributing to the project, please contact me at dominicngoetjana@gmail.com.

© 2024 Dominic Ngoetjana. All Rights Reserved.