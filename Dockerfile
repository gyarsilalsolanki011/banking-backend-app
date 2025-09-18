# =========================
# 1️⃣ Build Stage
# =========================
FROM maven:3.9.9-eclipse-temurin-17 AS build

#Set working Directory
WORKDIR /app

# Copy pom.xml first (for dependency caching)
COPY pom.xml .

# Download dependencies (cache layer)
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build project
RUN mvn clean package -DskipTests

# =========================
# 2️⃣ Runtime Stage
# =========================
FROM openjdk:17-jdk-slim AS runtime

# Set working directly
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/spring-boot-banking-app.jar app.jar
COPY docker/wait-for.sh wait-for.sh
RUN chmod +x wait-for.sh

# Expose port
EXPOSE 8080

# Run Jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
