# Booking Service Skeleton

### I. How to start this skeleton

#### Hardware Requirement
```
CPU >= Core-i3
RAM >= 8GB
```

### II. Installation
- [java - version 11.x](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [docker - 20.x](https://docs.docker.com/get-docker/)
- [docker-compose - 1.25.x](https://docs.docker.com/compose/install/)
- [Apache Maven 3.6.x](https://maven.apache.org/install.html)
- [Intellij IDEA (it's ok with community edition)](https://www.jetbrains.com/idea/download/)

### III. Setup your local dev environment

#### 1.1. Fork this project to your own project on our gitlab

- Your **Gitlab account** will be included in the `Onboard Email` that you received.
- Please add your public key into **Gitlab account**
- Select the **Fork** button on this project, and fork it to your account.

#### 1.2. Clone forked project to your local machine

> Please feel free to using any `git client` tool that you prefer or use our way to clone the project:

- If you're using a Windows machine, please use `git-bash` to run the following command. Or if you're using MacOsx/Linux, please use `Terminal` instead.

```shell
git clone https://<git-project-uri>
```

```shell
cd hack2hire-2022-java/devops
docker-compose up -d zookeeper kafka
```
- Check needed docker containers are started 
  + Make sure these ports `9092, 8080` are not conflict with any docker containers or your any background services.
  + In console or terminal type: `docker ps` or `docker compose ps`
  + If you see this, it means the required containers start successfully.
```
CONTAINER ID   IMAGE                    COMMAND                  CREATED          STATUS         PORTS                                                                   NAMES
8fe606a588a0   wurstmeister/kafka       "start-kafka.sh"         43 seconds ago   Up 3 seconds   0.0.0.0:9092->9092/tcp, :::9092->9092/tcp, 29092/tcp                    booking-kafka
5b5e90fefdeb   wurstmeister/zookeeper   "/bin/sh -c '/usr/sb…"   44 seconds ago   Up 4 seconds   22/tcp, 2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp, :::2181->2181/tcp   booking-zookeeper
```

#### 1.3. Start Service
- Use Intellij IDE open this file and start service. 
```
booking-application/src/main/java/com/onemount/BookingApplication.java
```
#### 1.4. Service is started?
- Check Swagger via browser
```shell
http://localhost:8080/swagger-ui/#/
```
or 
```shell
curl 'http://localhost:8080/messages/1'
```
- it works! if you see
```json
{
  "data": {
    "id": "1",
    "message": "Hello, it works!"
  },
  "meta": {
    "code": 200,
    "message": "OK"
  }
}
```
#### 1.5. Package structure
- In this skeleton, we apply DDD (Domain Driven Design) and Hexagonal principles.
- This skeleton is separated into three main packages

- `com.onemount.application` - contains API interface (ex: Rest/SOAP...)
- `com.onemount.domain` - contains business logic
- `com.onemount.infrastructure` - contains configuration, common or util classes, for entry service and infrastructure (ex: MySQL/Kafka/JPA ...) or third-party connection
- But you can make your own package name convention, just use this convention for reference purposes only.

```
├── booking-application
   ├── application                        -----> application
   │   ├── api                          
   │       ├── controller                 -----> Class Name (CN) suffix `Controller`, ex: BookingController
   │       ├── exceptionhandler           -----> Global exception handler
   │       ├── request
   │       │   └── dto                    -----> request dto (update/create), CN suffix `Dto`, ex: BookingCreateMapper, BookingUpdateMapper
   │       └── response
   │       │    └── dto                   -----> response dto, CN suffix `Dto`, ex: BookingDto
   │       └── swagger                    -----> Swagger config
   ├── com.onemount.domain
   │   ├── exception                      -----> exceptions
   └── com.onemount.infrastructure
       ├── kafka                          -----> Kafka config
       └── repo                           -----> Contains Implementation of Domain Repository, ex: BookingRepository (implementation of IBookingRepository)
          └── model                       -----> Jpa Entity (mapping to db table, CN prefix: `Jpa`), ex: JpaBooking
```
#### 1.6. Booking Exception Handler
- Global exception handler is defined in `com/onemount/application/api/exceptionhandler/BookingGlobalExceptionHandler.java`
  - To declare an exception:
    - Step 1: Defining it in `com/onemount/domain/exception/BookingErrors.java`
      - ex: 
        ```java
         public static final BookingBusinessError EXAMPLE_NOT_FOUND_ERROR = new BookingBusinessError(404, "Resource is not found", HttpStatus.NOT_FOUND);
        ```
    - Step 2: Creating exception class has to extend `com/onemount/domain/exception/BookingException.java`
      - ex:
        ```java
          package com.onemount.domain.exception;
        
          import static com.onemount.domain.exception.BookingErrors.EXAMPLE_NOT_FOUND_ERROR;
          
          public class ExampleNotFoundException extends BookingException {
             public ExampleNotFoundException() {
                super(EXAMPLE_NOT_FOUND_ERROR);
             }
             public ExampleNotFoundException(String message) {
                super(EXAMPLE_NOT_FOUND_ERROR, message);
             }
          }
        ```
    - Under the hood `BookingGlobalExceptionHandler` will handler all exceptions derived from `BookingException` class and transform them to predefined structure (`com/onemount/application/api/response/dto/BaseResponse.java`)
      ```java
         @ExceptionHandler(BookingException.class)
         public ResponseEntity<BaseResponse<Void>> handleBusinessException(BookingException exception) {
             BaseResponse<Void> data = BaseResponse.ofFailed(exception);
             return new ResponseEntity<>(data, exception.getErrorCode().getHttpStatus());
         }
      ```

#### 1.7. Database Migration 
- This skeleton uses [Flyway](https://www.baeldung.com/database-migrations-with-flyway) for Database Migration.
- To create new or alter existing database table :
  - Put sql migration file in this folder `src/main/resources/db/migration`
  - The filename pattern of sql migration file is `Vx.y__fileName.sql`, in which `x.y` is versioning, it should be higher than the last migration file.
  
#### 1.8. What libs that used in this skeleton
- [Java SpringBoot Framework](https://spring.io/projects/spring-boot)
- [Lombok](https://projectlombok.org/)
- [MapStruct](https://mapstruct.org/)
- [Flyway Migration](https://www.baeldung.com/database-migrations-with-flyway)

### 1.9. Build docker image in local for testing
  - Go to skeleton project 
  ```shell
  cd hack2hire-2022-java
  ```
  - Running the following scripts for testing in your local
    ```shell
    mvn --batch-mode -U clean package spring-boot:repackage -DskipTests
    cd devops
    docker build -t om/booking -f Dockerfile.local ../
    docker-compose up booking
    ```

### IV. References
- [Kafka Tools](https://www.conduktor.io/download/)
- [Kafka Fundamentals for Beginner](https://www.linkedin.com/feed/update/urn:li:activity:6943480742320427008/)
