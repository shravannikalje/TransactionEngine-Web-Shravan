# Multi-stage build for Transaction Engine Backend

# Stage 1: Build with Maven
FROM maven:3.8.1-openjdk-8 AS builder
WORKDIR /app
COPY backend/pom.xml .
COPY backend/src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=builder /app/target/TransactionEngine.jar .
EXPOSE 8080
CMD ["java", "-jar", "TransactionEngine.jar"]
