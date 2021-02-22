# Quotes: Kotlin, Spring Boot and Angular based Quotes Web App
## Features
- Quotes CRUD operations
- Quote's score chart over time with the ability to change the grouping time unit
- User registration
- User profile endpoint
- Basic Authentication
- Only the quote owner can edit or delete a quote
- Vote history with grouping options with different time units
## Technologies
- Spring Boot
- Spring Data
- H2/MySQL/Postgres
- Swagger
- Angular
- RxJS
- Chart.js
## Build 🔨
```shell
./gradlew assemble && npm install && npm run build
```
## Run ⚡️
```shell
java -jar ./build/libs/quotes-1.0.0.jar
```
Open browser at: http://localhost:8080