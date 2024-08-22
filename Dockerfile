FROM maven:3.9.6-eclipse-temurin-22-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn clean package 

FROM openjdk:22-jdk-slim
WORKDIR /app
COPY --from=build /app/target/users-srv-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]