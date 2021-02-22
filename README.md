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
## Technologies
- Spring Boot
- Spring Data
- Postgres/H2
- Swagger
- Angular
- RxJS
- Chart.js
## Build
```
gradle assemble && npm install && npm run build
```
## Run
```
mvn spring-boot:run
```