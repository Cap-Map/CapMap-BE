# Use the official OpenJDK 17 image as the base image
FROM --platform=linux/amd64 openjdk:17-jdk-slim

# Set the working directory within the container
WORKDIR /app

# Copy the JAR file built by Gradle into the container
COPY build/libs/*.jar app.jar

# Expose the port on which your Spring Boot application runs
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]