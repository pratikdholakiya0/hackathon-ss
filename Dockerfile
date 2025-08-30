FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
COPY . .
RUN MVN CLEAN INSTALL PACKAGES -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/websocket-0.0.1-SNAPSHOT.jar prod.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "prod.jar"]