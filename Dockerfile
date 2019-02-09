
FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8082

ARG JAR_FILE=target/hotel-0.0.1-SNAPSHOT.jar


ADD ${JAR_FILE} hotel-service.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/hotel-service.jar"]
