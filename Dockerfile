FROM openjdk:17-jdk-alpine
MAINTAINER m.uvarov@tinkoff.ru
ADD ./build/libs/home-work-1-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar","/app.jar"]