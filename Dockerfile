FROM gradle:jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -Pprod -Papi-docs bootJar

FROM openjdk:11-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java",  "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]
#docker build -t maxiplux/io.api.base .
#docker tag  3501cab4af42 maxiplux/livemarket.business.b2bcart:1.0.5
#docker tag  39d440f82330 maxiplux/livemarket.business.b2bcart:kuerbernetes
#docker push maxiplux/livemarket.business.b2bcart:kuerbernetes
#docker push maxiplux/io.api.base:1.0.0
#docker push maxiplux/io.api.base:master .
#docker buildx build --platform linux/amd64,linux/arm64 maxiplux/io.api.base:1.0.0 --push -t maxiplux/io.api.base:1.0.0
#aws lightsail create-container-service --service-name api-server-demo --power micro --scale 1
