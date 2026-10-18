# 🧳 TripPacker

A packing-list planner built with Spring Boot and Angular. Users build reusable groups of items they always take (toiletries, electronics, hiking gear), then add those groups to a trip and check items off as they pack. JWT authentication keeps every user's groups and trips private.

![Java](https://img.shields.io/badge/Java-24-red?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen?logo=springboot)
![Angular](https://img.shields.io/badge/Angular-20-dd0031?logo=angular)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?logo=docker)

---

## Features

- **JWT authentication**: stateless login with tokens valid for one day, and passwords hashed using BCrypt
- **Groups to pack**: reusable templates such as "Toiletries" or "Camping", each with its own list of items
- **Items with quantities**: every item in a group has a name and how many of it to pack
- **Trip luggage**: create a trip, rename it, and attach any of your groups to it
- **Packing groups**: attaching a group to a trip copies its items into that trip, so each trip tracks its own progress without changing the template
- **Packing progress**: mark items as packed and record how many of each are already in the bag
- **Ownership checks**: every service verifies that the group, item or trip belongs to the current user before reading or changing it
- **Single deployable**: the production Angular build is served by Spring Boot from `static/`, so one container runs the whole app
- **Error dialogs**: the frontend error interceptor shows backend error messages in a Material dialog

---

## How It Works

```
 Group to pack  "Toiletries"          Trip luggage  "Summer in Greece"
 ├── Toothbrush   x1                   └── Packing group (from "Toiletries")
 ├── Toothpaste   x1        add to         ├── Toothbrush   0/1  ☐
 └── Shampoo      x2       ───────►        ├── Toothpaste   1/1  ☑
                                           └── Shampoo      1/2  ☐
```

1. Create a **group to pack** and fill it with **items to pack**.
2. Create a **trip**.
3. Add one or more groups to the trip. Each one becomes a **packing group** with a **packing item** for every item in the template.
4. While packing, tick items off and update the packed quantity.

---

## Architecture

```
┌──────────────────────────────────────────────────────────┐
│                   Angular Frontend (:4200)               │
│                                                          │
│  Components  ──►  Services  ──►  HttpClient              │
│      ▲                              │                    │
│   AuthGuard           Request / Error Interceptors       │
└─────────────────────────────────────┼────────────────────┘
                     REST + JWT       │  JSON
┌─────────────────────────────────────┼────────────────────┐
│                   Spring Boot Backend (:8080)            │
│                                     ▼                    │
│   JwtFilter  ──►  Controllers  ──►  Services  ──►  Repos │
│                        │               │            │    │
│                      DTOs ◄─ Mappers ─ Entities     │    │
│                                                     │    │
│   HomeController ──► static/index.html (Angular)    │    │
└─────────────────────────────────────────────────────┼────┘
                                                      ▼
                                              PostgreSQL 15
```

The frontend talks to the backend only through the REST API under `/api`. The request interceptor attaches the JWT to every call, and `JwtFilter` validates it and fills the Spring Security context. Ownership checks run in the service layer. `HomeController` forwards every non-API, non-file path to `index.html`, so Angular routes like `/trip/5` work on a page refresh.

---

## Database Schema

| Table | Description |
|---|---|
| `users` | Username, BCrypt password hash and creation date |
| `groups_to_pack` | Reusable item templates, owned by a user |
| `items_to_pack` | Items in a group, with a name and `quantity_to_pack` |
| `trip_luggage` | Trips, owned by a user |
| `packing_groups` | A group attached to a trip. Links `groups_to_pack` and `trip_luggage` |
| `packing_items` | One per item in a packing group, with `is_packed` and `packed_quantity` |

Every table has a `created_by_id` foreign key to `users` and a `created_at` timestamp. Deleting a group or a trip cascades to its children.

---

## API Overview

Every endpoint except register and login needs an `Authorization: Bearer <token>` header. Users can only access the resources they created.

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/auth/register` | Register a user (username 3–20 chars, password 6–20 chars) |
| `POST` | `/api/auth/login` | Log in and receive a JWT |
| `GET` | `/api/group-to-pack/all` | Previews of the current user's groups |
| `GET` | `/api/group-to-pack/{id}` | Group details |
| `POST` | `/api/group-to-pack` | Create a group |
| `DELETE` | `/api/group-to-pack/{id}` | Delete a group and its items |
| `GET` | `/api/item-to-pack/group/{groupId}` | Items in a group |
| `POST` | `/api/item-to-pack` | Add an item to a group |
| `DELETE` | `/api/item-to-pack/{id}` | Delete an item |
| `GET` | `/api/trip-luggage/all` | Previews of the current user's trips |
| `GET` | `/api/trip-luggage/{id}` | Trip details with its packing groups and items |
| `POST` | `/api/trip-luggage` | Create a trip |
| `PATCH` | `/api/trip-luggage/{id}` | Rename a trip and/or add groups (`newName`, `newGroup`, `newGroups`) |
| `DELETE` | `/api/trip-luggage/{id}` | Delete a trip |
| `POST` | `/api/packing-item` | Add a single packing item |
| `PATCH` | `/api/packing-item/{id}` | Update `isPacked` and/or `packedQuantity` |
| `DELETE` | `/api/packing-item/{id}` | Delete a packing item |

Create endpoints return `201 Created` with the new resource in the `Location` header.

---

## Tech Stack

**Backend**
- **Java 24** and **Spring Boot 3.5**: Web, Data JPA, Security, Validation
- **[JJWT](https://github.com/jwtk/jjwt)** to issue and validate JWTs
- **PostgreSQL 15** for the database
- **Lombok** to reduce boilerplate
- **Maven** (wrapper included) for builds

**Frontend**
- **Angular 20** with standalone components
- **Angular Material** for UI components (custom theme)
- **RxJS** for HTTP streams
- **Jasmine** and **Karma** for unit tests

**Infrastructure**
- **Docker** and **Docker Compose**: a multi-stage backend image (Maven build → JRE runtime), a Postgres container and a persistent volume for the database

---

## Getting Started

### Prerequisites

- Docker and Docker Compose
- Node.js and npm (only for frontend development)
- JDK 24 (only if you run the backend outside Docker)

### Clone

```bash
git clone https://github.com/TrayanDKirov/TripPacker.git
cd TripPacker/TripPacker-Source-Code
```

### Running the app (Docker)

```bash
cd trippacker
docker compose up --build
```

This starts PostgreSQL on `localhost:5432` and the app on `http://localhost:8080`. The backend serves the bundled frontend, so you can open `http://localhost:8080` right away. Tables are created automatically from the JPA entities.

### Running the backend locally (without Docker)

Start only the database container, then run Spring Boot. `application.properties` already points to `localhost:5432`:

```bash
cd trippacker
docker compose up -d db
./mvnw spring-boot:run
```

### Running the frontend (development)

```bash
cd trippacker-frontend
npm install
npm start
```

Open `http://localhost:4200`. The development environment points to the backend at `http://localhost:8080/api`, and CORS on the backend allows `http://localhost:4200`.

### Updating the bundled frontend

The production build uses relative URLs (`/api`, `/images`) so it works when served by Spring Boot:

```bash
cd trippacker-frontend
npm run build
```

Then copy the contents of `dist/trippacker-frontend/browser/` into `trippacker/src/main/resources/static/` and rebuild the Docker image.

---

## Project Structure

```
TripPacker-Source-Code/
├── trippacker/
│   ├── Dockerfile                  # Multi-stage build (Maven → JRE)
│   ├── docker-compose.yml          # Postgres + app + volume
│   └── src/main/
│       ├── java/bg/sofia/uni/fmi/tdkirov/trippacker/
│       │   ├── controller/         # REST controllers + HomeController (SPA forwarding)
│       │   ├── dto/                # Request/response DTOs per domain
│       │   ├── exception/          # Domain exception hierarchy
│       │   ├── handler/            # GlobalExceptionHandler
│       │   ├── mapper/             # Entity ↔ DTO mappers
│       │   ├── model/              # JPA entities
│       │   ├── repository/         # Spring Data JPA repositories
│       │   ├── security/           # JwtFilter, JwtUtil, SecurityConfiguration
│       │   └── service/            # Service interfaces and implementations
│       └── resources/
│           ├── application.properties
│           └── static/             # Production Angular build and images
└── trippacker-frontend/
    └── src/app/
        ├── components/             # dialog, group, home, item-to-pack, packing-group, trip-luggage, user
        ├── dtos/                   # TypeScript mirrors of the backend DTOs
        ├── guards/                 # AuthGuard
        ├── interceptors/           # JWT request interceptor, error interceptor
        └── services/               # API services (auth, group, item, packing item, trip)
```

---

## Potential Improvements

- **Edit groups and items**: `GroupToPackUpdateDto` is empty and items can only be created or deleted
- **Remove a group from a trip**: `PackingGroupServiceImpl.deletePackingGroupById` is not implemented yet
- **Sync templates with trips**: items added to a group after it was attached to a trip don't show up in that trip
- **Lock down `/api/v1/user`**: it lets any logged-in user create users and list all users as entities, password hashes included
- **Proper HTTP statuses**: `GlobalExceptionHandler` returns `400` for everything, so "not found" and "not yours" should map to `404` and `403`
- **Consistent API versioning**: most routes are under `/api`, but the user controller uses `/api/v1`
- **Automated frontend bundling**: build the Angular app inside the Dockerfile instead of copying it into `static/` by hand
- **Database migrations**: use Flyway or Liquibase instead of `ddl-auto=update`
- **Externalised secrets**: move the database credentials to environment variables only and load the JWT signing key from configuration. Right now it is generated at startup, so every restart logs all users out
- **Change password**: `User` has a TODO for it
- **Test coverage**: unit and integration tests for services, controllers and components