FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM docker.io/openjdk:11-jdk-slim
COPY --from=build /target/moviereview_api-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","/app.jar"]