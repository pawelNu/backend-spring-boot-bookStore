# Book Store Application - Spring Boot Backend

## Table of content

TODO create table of content

[//]: # (TODO create table of content)

## Github Repositories

Backend: https://github.com/pawelNu/backend-spring-boot-bookStore

Frontend: https://github.com/pawelNu/frontend-react-book-store

## Features

TODO add discription of application features

[//]: # (TODO add discription of application features)

## Deploy to Vercel

1. Go to https://vercel.com/

TODO finish the deploy configuration

[//]: # (TODO finish the deploy configuration)

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
3. In Intellij -> Viev -> Tool Windows -> Maven (or choose Maven from right side bar)
4. bookStore -> Plugins -> test
5. It creates `target/jacoco.exec` and go to: `target/site/jacoco/index.html` and open it in browser
