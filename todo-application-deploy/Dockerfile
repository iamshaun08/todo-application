FROM openjdk:8-jdk-alpine
LABEL authors="shaun"

COPY /target/todo-application-deploy-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]