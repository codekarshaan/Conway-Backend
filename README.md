# Conway Backend

A secure and scalable logistics enquiry management backend built with **Spring Boot 3**, **Java 21**, **MySQL**, and **JWT Authentication**.

The system enables customers to submit shipment enquiries while providing administrators with a centralized portal to manage enquiries, track activities, monitor analytics, and maintain operational visibility.

---

## Project Overview

Conway Backend is designed to streamline logistics enquiry management by providing:

* Public enquiry submission for customers
* Admin portal APIs for enquiry management
* Dashboard analytics and reporting
* Notification management
* Audit logging
* Login history tracking
* Role-based access control
* Secure JWT authentication

The backend follows a layered architecture with clear separation of concerns and database versioning through Flyway migrations.

---

## Features

### Authentication & Security

* JWT Authentication
* Stateless Session Management
* Role-Based Access Control (RBAC)
* Spring Security Integration
* Protected Admin APIs
* Swagger JWT Authorization Support

### Admin Management

* Create Admin
* Update Admin
* Activate / Deactivate Admin
* Prevent Self-Deactivation
* Prevent Deactivation of Last Active Super Admin
* Role-Based Access Restrictions

### Enquiry Management

* Create Enquiry
* View Enquiries
* Search Enquiries
* Filter Enquiries by Status
* Pagination Support
* Update Enquiry Status

### Enquiry Notes

* Add Internal Notes
* View Enquiry Notes
* Admin Activity Tracking

### Dashboard

* Total Enquiries
* Enquiries by Status
* Active Admin Count
* Recent Enquiries

### Notifications

* Notification Creation
* Unread Notification Count
* Mark Single Notification as Read
* Mark All Notifications as Read
* Notification Ownership Security

### Audit Logs

* Admin Activity Tracking
* Secure Access for Super Admins

### Login History

* Login Monitoring
* IP Address Tracking
* User Agent Tracking

### Master Data

* Cities
* Truck Types
* Cargo Types

---

## Tech Stack

### Backend

* Java 21
* Spring Boot 3.5
* Spring Security
* Spring Data JPA
* Hibernate
* Flyway

### Database

* MySQL

### Authentication

* JWT (JSON Web Token)

### Documentation

* Swagger / OpenAPI

### Build Tool

* Maven

---

## Architecture

```text
Client
   │
   ▼
React Frontend
   │
   ▼
Spring Boot REST APIs
   │
   ▼
Service Layer
   │
   ▼
Repository Layer (JPA)
   │
   ▼
MySQL Database
   │
   ▼
Flyway Migrations
```

---

## Roles

### SUPER_ADMIN

* Manage Admins
* View Audit Logs
* View Login History
* Access Dashboard
* Manage Enquiries

### ADMIN

* Manage Enquiries
* Add Notes
* View Dashboard
* Manage Notifications

---

## Database Modules

### Core Tables

* admins
* roles
* enquiries
* enquiry_notes
* notifications
* audit_logs
* login_history

### Master Tables

* cities
* truck_types
* cargo_types

---

## API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI Documentation:

```text
http://localhost:8080/v3/api-docs
```

---

## Running the Project

### Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/conway-backend.git
cd conway-backend
```

### Configure Database

Create a MySQL database:

```sql
CREATE DATABASE conway_db;
```
## Configuration

Copy the example configuration:

```bash
cp src/main/resources/application-example.properties src/main/resources/application.properties
```

Update database credentials and JWT secret before running the application.

Update application.properties with your database credentials.

### Run Application

```bash
mvn clean install
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

---

## Flyway Migrations

The project uses Flyway for database version control.

Current migrations include:

```text
V1  - Create Roles
V2  - Create Admins
V3  - Seed Roles
V4  - Create Cities
V5  - Create Truck Types
V6  - Create Cargo Types
V7  - Seed Truck Types
V8  - Seed Cities
V9  - Seed Cargo Types
V10 - Create Enquiries
V11 - Create Enquiry Notes
V12 - Create Notifications
V13 - Create Login History
V14 - Create Audit Logs
V15 - Dashboard Enhancements
V16 - Master Data Enhancements
V17 - Enquiry Improvements
V18 - Recent Enquiries
V19 - Notification Improvements
```

---

## Security Highlights

* JWT Authentication
* Role-Based Access Control
* Notification Ownership Validation
* Self-Deactivation Protection
* Last Super Admin Protection
* Global Exception Handling
* Stateless Authentication

---

## Future Enhancements

* React Frontend Integration
* Email Notifications
* Password Reset Flow
* Refresh Token Support
* Docker Deployment
* Cloud Deployment
* Advanced Reporting Dashboard

---

## Author

**Sushaan Mehta**

Backend Developer | Java | Spring Boot | SQL | Security | REST APIs
