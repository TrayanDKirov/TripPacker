# Build stage: compiles the jar from source, no host-side mvn package needed
FROM maven:3.9-eclipse-temurin-24 AS build
WORKDIR /build

# Cache dependencies separately from source so code changes don't re-download them
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

# Runtime stage: lightweight JRE-only image
FROM eclipse-temurin:24-jre-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=build /build/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

