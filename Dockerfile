FROM openjdk:17-jdk-alpine
MAINTAINER m.uvarov@tinkoff.ru
ADD ./build/libs/home-work-5-0.0.11-SNAPSHOT.jar ./app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar","/app.jar"]
