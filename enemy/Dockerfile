FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY  /build/libs/enemy-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=container", "/app.jar"]
EXPOSE 8083/tcp

