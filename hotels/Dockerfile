FROM maven:3.9.6-eclipse-temurin-21-alpine as maven

WORKDIR /app

COPY . /app/

RUN mvn clean install

FROM eclipse-temurin:21.0.1_12-jre as  build

COPY --from=maven /app/target/*.jar hotels-microservice.jar

ENTRYPOINT ["java", "-jar", "/hotels-microservice.jar"]


