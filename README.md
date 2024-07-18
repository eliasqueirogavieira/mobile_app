# Mobile App Prototype for SongDemocracy

This is a project that combines an Angular Progressive Web App (PWA) with a Spring Boot backend. Below are the steps to set up and run the application.

## Prerequisites

### Backend (Spring Boot)
- Java 21 
- Maven 3.6+

### Frontend (Angular)
- Node.js 20.15.1 LTS
- Angular CLI 18

## Installation Instructions

### 1. Backend Setup

#### Install Java 21
- Download and install Java 21 from the [Oracle official website](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html).

#### Verify Java Installation
```sh
java -version
```
- Ensure it shows Java 21.

#### Install Maven
- Download Maven from the [official website](https://maven.apache.org/download.cgi) and follow the installation instructions.

#### Verify Maven Installation
```sh
mvn -version
```
- Ensure it shows the correct Maven version.

#### Build and Run the Backend
```sh
cd backend
mvn clean install -DskipTests
mvn spring-boot:run
```
- The backend server should now be running.

### 2. Frontend Setup

#### Install Node.js
- Download and install Node.js 18.x from the [official website](https://nodejs.org/).

#### Verify Node.js Installation
```sh
node -v
npm -v
```
- Ensure it shows the correct Node.js and npm versions.

#### Install Angular CLI
```sh
npm install -g @angular/cli@18
```

#### Verify Angular CLI Installation
```sh
ng version
```
- Ensure it shows Angular CLI version 18.

#### Build and Run the Frontend
```sh
cd frontend
npm install
ng serve
```
- The frontend application should now be running on `http://localhost:4200`.

## Building the Project for Production

### Backend
```sh
cd backend
mvn clean package
```
- The packaged backend will be in the `target` directory.

### Frontend
```sh
cd frontend
ng build --prod
```
- The built frontend will be in the `dist/frontend` directory.

## Additional Information

- This project uses a PWA setup for the Angular frontend.
- Ensure the backend server is running before accessing the frontend.
- For any issues, refer to the official documentation for [Spring Boot](https://spring.io/projects/spring-boot) and [Angular](https://angular.io/).