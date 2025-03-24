FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory (assuming Maven build output)
COPY target/*.jar app.jar

# Expose the application port (Change if needed)
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]