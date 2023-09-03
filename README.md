# Book Store Application - Spring Boot Backend

## Table of content

[//]: # (TODO create table of content)

## Github Repositories

Backend: https://github.com/pawelNu/backend-spring-boot-bookStore

Frontend: https://github.com/pawelNu/frontend-react-book-store

## Features

[//]: # (TODO add discription of application features)

## Travis CI - IS NOT FREE

1. Create [.travis.yaml](.travis.yaml) file in root directory.
2. Go to https://www.travis-ci.com/

## CircleCI

How to set up: https://medium.com/xebia-engineering/how-to-enable-circleci-builds-for-a-spring-boot-application-ff125e1bd062

PDF version: [Enable-CircleCI-builds-for-Spring-Boot.pdf](assets/Enable-CircleCI-builds-for-Spring-Boot.pdf)

[//]: # (TODO create an automation deploy pipeline)

1. Login to CircleCI with GitHub account and connect it to CircleCI account
2. On the left side -> menu -> Projects
3. From list choose backend-spring-boot-bookStore -> button Set Up Project
4. In Select your `config.yml` file -> Fast: Take me to a `config.yml` template that I can edit
5. Choose Java (Maven) -> button Select
6. It will create configuration for `config.yml` file.
7. In the root directory create a hidden folder called `.circleci`.
8. Inside `.circleci` create `config.yml` file.
9. Paste to `config.yml` file configuration.
10. Change openjdk to 17 in `cimg/openjdk:17.0.7`. More info: https://circleci.com/developer/images/image/cimg/openjdk

[//]: # (11. TODO finish deployment configuration)

## Deploy to Vercel

1. Go to https://vercel.com/

[//]: # (TODO finish the deploy configuration)

## Deploy to Humalect

1. Go to https://humalect.com/

[//]: # (TODO finish the deploy configuration)

## Deploy to Render

1. Go to https://render.com/
2. Login with GitHub account
3. In `.gitignore` file comment
    ```.gitignore
    ### Comment this directories to deploy to Render ###
    #target/
    #!.mvn/wrapper/maven-wrapper.jar
    #!**/src/main/**/target/
    #!**/src/test/**/target/
    ```
4. Directories: `.mvn` and `target` must be added to git repository
5. In Intellij -> View -> Tool Windows -> Maven
6. Select bookStore -> Lifecycle -> clean -> package
7. Test `.jar` file [bookStore-0.0.1-SNAPSHOT.jar](target/bookStore-0.0.1-SNAPSHOT.jar)
8. In Intellij run in Terminal:
    ```shell
    java -jar target/bookStore-0.0.1-SNAPSHOT.jar
    ```
9. Or create `.bat` file in main directory [runApplication.bat](runApplication.bat) and run it
    ```shell
    start /B java -jar target/bookStore-0.0.1-SNAPSHOT.jar
    ```
10. In browser go to http://localhost:8080/api/v1/books, you should see JSON response.
11. Create Dockerfile in main directory
    ```dockerfile
    FROM eclipse-temurin:17-jdk-alpine
    VOLUME /tmp
    COPY target/*.jar app.jar
    ENTRYPOINT ["java", "-jar", "/app.jar"]
    EXPOSE 8080
    ```
12. In Render -> Create a new Web Service
13. Configure GitHub account -> choose backend-spring-boot-bookStore repository
14. Or add in: Public Git repository -> https://github.com/pawelNu/backend-spring-boot-bookStore -> button Continue
15. Runtime: Docker
16. Button Create Web Service
17. After building -> https://bookstore-k5xx.onrender.com/api/v1/books
