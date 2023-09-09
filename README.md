# Book Store Application - Spring Boot Backend

I CANNOT FINISH THE PROJECT  
COURSE HAS NOT SOURCE CODE

* [Book Store Application - Spring Boot Backend](#book-store-application---spring-boot-backend)
  * [Github Repositories](#github-repositories)
  * [Features](#features)
  * [Deploy to Render](#deploy-to-render)
  * [Add JaCoCo](#add-jacoco)
  * [Add Swagger](#add-swagger)
  * [Create PostgreSQL on Render](#create-postgresql-on-render)
  * [Create Environment Variable on Render](#create-environment-variable-on-render)
  * [Create .env file](#create-env-file)
  * [Run files](#run-files)
  * [Running in prod with Postgres Database](#running-in-prod-with-postgres-database)

## Github Repositories

Backend: https://github.com/pawelNu/backend-spring-boot-bookStore

Frontend: https://github.com/pawelNu/frontend-react-book-store

## Features

Project abandoned

## Deploy to Render

1. Create Dockerfile in main directory
   ```dockerfile
   # Build stage
   FROM maven:3.8.3-openjdk-17 AS build
   WORKDIR /app
   COPY pom.xml /app/
   RUN mvn dependency:go-offline
   COPY src /app/src
   RUN mvn clean package -DskipTests
   
   # Package stage
   FROM openjdk:17-alpine
   WORKDIR /app
   COPY --from=build /app/target/*.jar /app/bookStore.jar
   EXPOSE 8080
   ENTRYPOINT ["java", "-jar", "bookStore.jar"]
   
   # Clean up unnecessary files (optional)
   RUN rm -rf /app/src /app/pom.xml

   ```
2. Go to https://render.com/
3. Login with GitHub account
4. In Render -> Create a new Web Service
5. Configure GitHub account -> choose backend-spring-boot-bookStore repository
6. Or add in: Public Git repository -> https://github.com/pawelNu/backend-spring-boot-bookStore -> button Continue
7. Runtime: Docker
8. Button Create Web Service
9. After building -> https://bookstore-k5xx.onrender.com/api/v1/books

## Add JaCoCo

Example: https://mkyong.com/maven/maven-jacoco-code-coverage-example/  
Example: https://www.baeldung.com/jacoco

1. Add to `pom.xml` file in `<build>` section inside `<plugins>`
    ```xml
    <plugin>
       <groupId>org.jacoco</groupId>
       <artifactId>jacoco-maven-plugin</artifactId>
       <version>0.8.10</version>
       <executions>
           <execution>
               <goals>
                   <goal>prepare-agent</goal>
               </goals>
           </execution>
           <execution>
               <id>jacoco-report</id>
               <phase>test</phase>
               <goals>
                   <goal>report</goal>
               </goals>
           </execution>
           <!-- attached to Maven test phase -->
           <execution>
               <id>jacoco-check</id>
               <goals>
                   <goal>check</goal>
               </goals>
               <configuration>
                   <rules>
                       <rule>
                           <element>PACKAGE</element>
                           <limits>
                               <limit>
                                   <counter>LINE</counter>
                                   <value>COVEREDRATIO</value>
                                   <minimum>1.0</minimum>
                               </limit>
                           </limits>
                       </rule>
                   </rules>
               </configuration>
           </execution>
       </executions>
    </plugin>
    ```
2. In main directory create `lombok.config` file
   ```lombok.config
   # removes com.pawelNu.bookStore.dto from report
   lombok.addLombokGeneratedAnnotation = true
   ```
3. In Intellij -> View -> Tool Windows -> Maven (or choose Maven from right side bar)
4. bookStore -> Plugins -> test
5. It creates `target/jacoco.exec` and go to: `target/site/jacoco/index.html` and open it in browser

## Add Swagger

https://www.bezkoder.com/spring-boot-swagger-3/  
https://swagger.io/specification/

1. Add dependency `springdoc-openapi-starter-webmvc-ui`
2. In controller class and `@Tag` and to endpoint add `@Operation`, more info: https://www.bezkoder.com/spring-boot-swagger-3/
3. Run Spring Boot project. Open browser with url: http://localhost:8080/swagger-ui/index.html
4. Open http://localhost:8080/v3/api-docs, to see document in Json format

https://www.bezkoder.com/swagger-3-annotations/#Swagger_3_Parameter_annotation

Documentation: https://docs.swagger.io/swagger-core/v2.2.9/apidocs/io/swagger/v3/oas/annotations/media/Schema.html

## Create PostgreSQL on Render

1. Go to https://render.com/
2. Go to Dashboard -> button New + -> PostgreSQL
3. New PostgreSQL-> fill: Name, Database, User, Region
4. Instance Type: Free
5. button Create Database -> lasted about 30 min

## Create Environment Variable on Render

1. Dashboard -> choose service -> Environment -> Environment Variables -> Add Environment Variable
2. Key: `SPRING.PROFILES.ACTIVE`, value: `prod`
3. Add the same way the rest variables from `application-prod.yaml`: databaseUrl, usernameDB, passwordDB
4. Save Changes

## Create .env file

1. Add dependency https://mvnrepository.com/artifact/me.paulschwarz/spring-dotenv
2. Create `.env` file and add values to keys
    ```
    databaseUrl=jdbc:postgresql://host-name:port/database-name
    usernameDB=user
    passwordDB=password
    ```
3. In Intellij, in Run/Debug Configuration -> choose profile to edit
4. In Environment Variables add: `SPRING.PROFILES.ACTIVE=prod`
5. This will cause to run `application-prod.yaml` file

## Configuration run files

DEV file: [BookStoreApplication.run.xml](BookStoreApplication.run.xml)

PROD file: [(PROD) BookStoreApplication.run.xml](%28PROD%29%20BookStoreApplication.run.xml)

## Running in prod with Postgres Database

1. Use DBeaver or http://localhost:8080/h2-console to connect to database
