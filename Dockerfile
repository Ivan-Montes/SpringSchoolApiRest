ARG TAG=17-jre-alpine
FROM eclipse-temurin:${TAG}
LABEL ime.school-api-rest.version="1.0"
ENV DIRPATH=/var/app
WORKDIR $DIRPATH
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]