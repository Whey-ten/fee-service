FROM maven:3.8.7-eclipse-temurin-19-focal AS build

COPY src /home/app/src

COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package

FROM eclipse-temurin:17-jdk

COPY --from=build /home/app/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]

