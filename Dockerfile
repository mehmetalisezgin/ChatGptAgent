###
# Multistage Dockerfile for building and running the Kod Kahvesi resume web application.
#
# The first stage uses the official Maven image to compile the source code
# into a runnable JAR. The second stage runs the compiled JAR on a slim JRE
# image. This pattern keeps the runtime image small and free of build
# dependencies.
###

## Stage 1: Build the application
FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /workspace

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application, skipping tests for speed
RUN mvn -B clean package -DskipTests

## Stage 2: Prepare the runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the packaged jar from the builder stage
COPY --from=builder /workspace/target/resume-web-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Optionally, JVM options can be supplied via JAVA_OPTS environment variable
ENV JAVA_OPTS=""

# Start the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]