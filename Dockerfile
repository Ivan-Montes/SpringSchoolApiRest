#BUILD
# Set main image
ARG TAG-BUILD=3.9.6-amazoncorretto-17-debian
FROM maven:${TAG-BUILD} as builder

# Set the working directory using variables
WORKDIR /app

# Copy the application source code to the src directory
COPY pom.xml ./
COPY src ./src
RUN mvn clean install

# DEPLOY
# Set main image
ARG TAG-DEPLOY=17-jre-alpine
FROM eclipse-temurin:${TAG-DEPLOY}

# Define meta info
LABEL ime.school-api-rest.version="1.0"
LABEL ime.school-api-rest.maintainer="IvanM"
LABEL ime.school-api-rest.description="Just a simple dockerfile"

# Copy the application source code to the src directory
COPY --from=builder ./target/*.jar app.jar

# Port
EXPOSE 8080

# Set the command to run the application
ENTRYPOINT ["java","-jar","./app.jar"]
