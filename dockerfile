FROM eclipse-temurin:17-jdk-alpine

VOLUME /temp

COPY build/libs/*.jar app.jar

EXPOSE 5100

ENTRYPOINT [ "java", "-jar", "app.jar"]