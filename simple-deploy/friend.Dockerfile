FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY  friend-0.1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=container", "/app.jar"]
EXPOSE 8081/tcp

