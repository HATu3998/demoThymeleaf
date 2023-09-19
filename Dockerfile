FROM maven:3.8.5-openjdk-17 AS build
COPY . . 
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk0-slim
COPY --from=build /target/demoThymeleaf-0.0.1-SNAPSHOT.jar demoThymeleaf.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demoThymeleaf.jar"]
