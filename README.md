# Quotes Web API
## REST API reference
https://polar-shore-41051.herokuapp.com/swagger-ui.html
## Live API
https://polar-shore-41051.herokuapp.com
## Features
- Quotes CRUD operations
- Quote's score chart over time with the ability to change the grouping time unit
- User registration
- User profile endpoint
- Basic Authentication
- Only the quote owner can edit or delete a quote
- Vote history with grouping options with different time units
## Stack
- Spring Boot 2
- Spring Data
- Postgres
- Swagger
- Angular 6
- RxJS
- Chart.js
## Build
```
mvn clean install && npm install && npm run postinstall
```
## Run
```
mvn spring-boot:run
```