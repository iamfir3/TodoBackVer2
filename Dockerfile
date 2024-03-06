FROM maven:3.9.1-amazoncorretto-19-debian-bullseye AS build

WORKDIR /opt/app

COPY ./ /opt/app
RUN mvn clean install

FROM openjdk:19-jdk
COPY --from=build /opt/app/target/*.jar app.jar

ENV PORT 5000
EXPOSE $PORT

ENTRYPOINT ["java","-jar","-Xmx1024H","-Dserver.port=${PORT}","app.jar"]
