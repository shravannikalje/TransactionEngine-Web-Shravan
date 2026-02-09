# Multi-stage build for Transaction Engine Backend - Spring Boot with Java 21

# Stage 1: Build with Maven
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app
COPY backend/pom.xml .
COPY backend/src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/TransactionEngine-1.0-SNAPSHOT.jar ./app.jar
EXPOSE 8080
ENV PORT=8080
ENTRYPOINT ["java", "-jar", "app.jar"]
