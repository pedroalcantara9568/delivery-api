FROM openjdk:8-jdk-alpine

ADD target/desafio-delivery.jar desafio-delivery.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "desafio-delivery.jar"]