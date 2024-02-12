#BUILD
# Set main image
ARG TAG=17-jdk-jammy
FROM eclipse-temurin:${TAG} as builder

# Define meta info
LABEL ime.school-api-rest.version="1.0"
LABEL ime.school-api-rest.maintainer="IvanM"
LABEL ime.school-api-rest.description="Just a simple dockerfile"

# Set the working directory using variables
WORKDIR /app

# Copy the application source code to the src directory
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

# DEPLOY
# Set main image
ARG TAG-DEPLOY=17-jre-alpine
FROM eclipse-temurin:${TAG-DEPLOY}

# Copy the application source code to the src directory
COPY --from=builder ./target/*.jar app.jar

# Port
EXPOSE 8080

# Set the command to run the application
ENTRYPOINT ["java","-jar","./app.jar"]
