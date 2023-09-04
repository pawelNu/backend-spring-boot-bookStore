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
