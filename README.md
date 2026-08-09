# Task Service

Task Service is a Spring Boot microservice for task lifecycle management. It is implemented with Maven, PostgreSQL, JPA, and Liquibase, and it exposes a REST API for create, read, update, and delete operations. The codebase is organized as a multi-module Maven project, with a runtime application module and a separate API contract module.

## Overview

Capabilities:

- create task records
- retrieve task records by identifier
- modify existing task records
- remove task records

Modules:

- `task-service` - application runtime
- `task-service-api` - shared API contract

## Technology Stack

- Java 17
- Spring Boot 3.5.6
- Spring Web
- Spring Data JPA
- Spring Validation
- Liquibase
- PostgreSQL
- Lombok
- MapStruct
- Testcontainers

## Environment Requirements

- JDK 17
- Maven or Maven Wrapper
- PostgreSQL accessible on `localhost:5433`

## Configuration

Default runtime parameters:

- server port: `8010`
- datasource URL: `jdbc:postgresql://localhost:5433/task?currentSchema=task`
- datasource username: `task_user`
- datasource password: `task_password`

Liquibase applies the schema migration on startup and provisions the `task` table.

## Build and Run

```bash
./mvnw clean test
./mvnw -pl task-service spring-boot:run
```

Alternative packaged execution:

```bash
./mvnw clean package
java -jar task-service/target/task-service-0.0.1.jar
```

## API Surface

Base path: `/tasks/task`

### Retrieve task

```http
GET /tasks/task/{id}
```

### Create task

```http
POST /tasks/task
Content-Type: application/json
```

```json
{
  "name": "Buy milk",
  "description": "2 liters"
}
```

### Update task

```http
PATCH /tasks/task/{id}
Content-Type: application/json
```

```json
{
  "name": "Buy bread",
  "description": "Whole grain"
}
```

### Delete task

```http
DELETE /tasks/task/{id}
```

## Validation Constraints

- `name` is mandatory on create requests
- `name` length must be between 1 and 150 characters
- `description` remains optional

## Persistence Model

The migration creates the `task` table with the following columns:

- `id`
- `name`
- `description`
- `created_at`
- `updated_at`

## Test Coverage

Execute the test suite with:

```bash
./mvnw test
```

Test layers included in the repository:

- controller tests
- service tests
- application context test

## Operational Notes

- `POST` returns `201 Created`
- `PATCH` returns `201 Created`
- The exposed API path is `/tasks/task`, not `/tasks`

