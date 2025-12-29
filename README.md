# Advanced Web Development - HCMUS

This repository contains weekly assignments, projects, and presentations for the Advanced Web Development course at Ho Chi Minh City University of Science (HCMUS).

## 📚 Repository Structure

### Main Projects

#### 🎯 Todo App
A full-stack Todo application demonstrating progressive React development patterns:
- **V1**: Basic components (TaskList, FilterTask, AddTask) with custom hooks
- **V2**: State management using Context API and useReducer for one-way data flow
- **V3**: Full CRUD operations with API integration
- **Tech Stack**: React 19, Vite, ESLint

**Setup:**
```bash
cd "Todo App"
npm install
npm run dev
```

#### 🔧 Todo Backend
Spring Boot REST API backend for the Todo application
- RESTful endpoints for task management
- MySQL database integration
- **Tech Stack**: Spring Boot 3.4.0, Java 17, Maven

**Setup:**
```bash
cd "Todo Backend/demo"
# Configure database in .env file (see .env.example)
./mvnw spring-boot:run
```

### API Development Modules

#### 📝 RESTful API
Introduction to RESTful API development using Spring Boot
- Actor management API using Sakila database
- **Location**: `RESTful API/actor`

#### 🔐 API Security
Security implementations with different authentication strategies:
- **server-a & server-b**: Demonstrates security patterns
- **Location**: `API Security/`

#### 🔄 API Security Update
Enhanced security features:
- **Access Token implementation**
- **Refresh Token implementation**
- **Secret Key management**
- **Location**: `API Security Update/`

#### 📊 API Logger
Request/response logging middleware for Spring Boot applications
- Sakila database integration example
- **Tech Stack**: Spring Boot, Maven
- **Location**: `API Logger/sakila`

#### ⚡ API Realtime
Real-time communication implementations
- Frontend and backend components
- **Location**: `API Realtime/`

#### ✅ API Validation - API Docs
API validation patterns and documentation
- Swagger/OpenAPI integration examples
- **Location**: `API Validation - API Docs/sakila`

### Weekly Presentations

The `Weekly Present/` folder contains presentation materials and demo projects organized by topic:
- **Todo context reducer**: Context API and useReducer patterns
- **Todo fetch data**: API integration patterns
- **api logging**: Logging implementation examples
- **realtime**: Real-time communication demos
- **security**: Various security implementations
  - Basic security
  - Access token
  - Refresh token
  - Secret key management
- **swagger**: API documentation with Swagger

## 🛠️ Technologies Used

### Frontend
- React 19
- Vite
- JavaScript/ES6+
- Context API & Hooks

### Backend
- Spring Boot 3.4.0
- Java 17
- Maven
- MySQL (Sakila database)

### Tools & Libraries
- ESLint
- Spring Security
- Swagger/OpenAPI
- JWT (for authentication)

## 📖 Course Topics Covered

1. **RESTful API Design**: Creating and consuming RESTful web services
2. **Frontend Development**: Modern React patterns and state management
3. **Security**: Authentication, authorization, and secure API practices
4. **Real-time Communication**: WebSocket and real-time data updates
5. **API Documentation**: Swagger/OpenAPI specifications
6. **Logging & Monitoring**: Request/response logging and debugging
7. **Validation**: Input validation and error handling
8. **Full-stack Integration**: Connecting frontend and backend applications

## 🚀 Getting Started

### Prerequisites
- Node.js (for React applications)
- Java 17+ (for Spring Boot applications)
- Maven (for building Spring Boot projects)
- MySQL (for database-backed applications)

### General Setup Pattern

For React projects:
```bash
npm install
npm run dev
```

For Spring Boot projects:
```bash
./mvnw spring-boot:run
# or on Windows:
mvnw.cmd spring-boot:run
```

## 📝 Notes

- Each project folder may contain its own README with specific setup instructions
- Environment variables are typically configured in `.env` files (see `.env.example` files for templates)
- The Sakila database is used as a sample database for many API examples

## 👥 Course Information

**Institution**: Ho Chi Minh City University of Science (HCMUS)  
**Course**: Advanced Web Development

---

For specific project details, please refer to the README files in individual project directories.
