# Movie Catalog Service

## Overview

The Movie Catalog Service is a Spring Boot application that provides APIs to manage and retrieve movie information, movie catalogs, and movie ratings. The project uses various technologies and tools such as Spring Boot, Spring Data JPA, WebClient, Liquibase, PostgreSQL, and more.

## Technologies Used

- **Spring Boot**: Framework for building Java applications.
- **Spring Data JPA**: For data persistence.
- **WebClient**: For making HTTP requests.
- **Liquibase**: For database version control.
- **PostgreSQL**: Relational database.
- **Lombok**: To reduce boilerplate code.
- **Micrometer & Zipkin**: For tracing and monitoring.
- **Logback**: For logging.

## Prerequisites

- Java 21
- PostgreSQL
- Gradle

## Setup Instructions

### Step 1: Clone the Repository

```bash
git clone https://github.com/your-repo/movie-catalog-service.git
cd movie-catalog-service
```

### Step 2: Set Up the Database

Create and Configure the Database
Run the `setup_moviedb.sh` script to create and configure the database:

```bash
chmod +x setup_moviedb.sh
./setup_moviedb.sh
```

Clear the Database (Optional)
If you need to clear the database, run the `clear_moviedb.sh` script:

```bash
chmod +x [clear_moviedb.sh]
./clear_moviedb.sh
```

### Step 3: Configure the Application

Update the application.properties file with your database configuration:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/moviedb
spring.datasource.username=developer
spring.datasource.password=developer
spring.jpa.hibernate.ddl-auto=none
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.yaml
```

###  Step 4: Run the Application

Use Gradle to build and run the application:

```bash
./gradlew bootRun
```

### Step 5: Verify Liquibase Setup

Liquibase will automatically apply the database changesets defined in the db/changelog directory. Ensure that the database schema is correctly set up by checking the movieschema schema in the moviedb database.

## API Endpoints

### Movie Info API

* Get All Movies

```bash
GET http://localhost:8080/api/v1/movie
Accept: application/json
```

* Get All Movie Info with Pagination and Sorting

```bash
GET http://localhost:8081/api/v1/movie-info/page?page=0&size=10&sortBy=movieId&sortBy=director&sortDirection=asc&sortDirection=asc
Accept: application/json
```

* Get All Movie Info with Pagination, Sorting, and Filtering

```bash
GET http://localhost:8081/api/v1/movie-info/filter?page=0&size=10&sortBy=movieId&sortBy=director&sortDirection=asc&sortDirection=asc&director=Christopher%20Nolan&id=1
Accept: application/json
```

* Get All Movies with Pagination, Sorting, and Filtering with Multiple Values

```bash
GET http://localhost:8081/api/v1/movie-info/list-filter?page=0&size=10&sortBy=movieId&sortBy=director&sortDirection=asc&sortDirection=asc&director=Christopher%20Nolan&id=1,2
Accept: application/json
```

### Movie Catalog API

* Get All Movie Catalog

```bash
GET http://localhost:8080/api/v1/movie-catalog
Accept: application/json
```

* Get Movies by Price Range with Pagination and Sorting

```bash
GET http://localhost:8080/api/v1/movie-catalog/price-range?lowerPrice=4.0&higherPrice=5.0&page=0&size=10&sort=price,asc
Accept: application/json
```

### Movie Rating API

* Get All Movie Ratings

```bash
GET http://localhost:8082/api/v1/movie-rating
Accept: application/json
```

* Get All Movie Ratings with Pagination and Sorting

```bash
GET http://localhost:8082/api/v1/movie-rating/page?page=0&size=10&sortBy=movieId&sortBy=rating&sortDirection=asc&sortDirection=desc
Accept: application/json
```

* Get All Movie Ratings with Pagination, Sorting, and Filtering

```bash
GET http://localhost:8082/api/v1/movie-rating/filter?page=0&size=10&sortBy=movieId&sortBy=rating&sortDirection=asc&sortDirection=desc&id=1&rating=5
Accept: application/json
```

* Get All Movie Ratings with Pagination, Sorting, and Filtering with Multiple Values

```bash
GET http://localhost:8082/api/v1/movie-rating/list-filter?page=0&size=10&sortBy=movieId&sortBy=rating&sortDirection=asc&sortDirection=desc&id=1,2&rating=4,5
Accept: application/json
```

## Additional Information

* Liquibase: Liquibase is used for database version control. The changesets are defined in the db/changelog directory.

* Tracing: Micrometer and Zipkin are used for distributed tracing and monitoring.

* Logging: Logback is used for logging, with Logstash encoder for structured logging.

## Questions

1. Draw a sequence diagram for the price-range API interaction.
2. How would you improve the price-range API for performance and usability?
3. How can you modify the price-range API to include movie ratings in the response?
4. What improvements can you suggest in terms of exception handling for the price-range API?
How would you address code review feedback and document the changes?
5. How would you test the price-range API, including unit, integration, and performance tests?
6. What strategies would you use to improve the performance of the price-range API?
7. What changes would you make to scale the Movie Catalog Service for tenfold traffic growth?