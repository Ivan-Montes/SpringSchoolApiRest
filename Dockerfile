ARG TAG=17-jre-alpine
FROM eclipse-temurin:${TAG}
LABEL ime.school-api-rest.version="1.0"
LABEL ime.school-api-rest.maintainer="IvanM"
LABEL ime.school-api-rest.description="Just a simple dockerfile"
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]