FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]