FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /opt/app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /opt/app

ENV PROFILE=prd

# Substitua "usuario-0.0.1-SNAPSHOT.jar" pelo nome exato do .jar gerado
COPY --from=build /opt/app/target/usuario-0.0.1-SNAPSHOT.jar /opt/app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "/opt/app/app.jar"]


#FROM  eclipse-temurin:21-alpine
#VOLUME /tmp
#EXPOSE 8080
#ARG JAR_FILE=target/usuario-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","app.jar"]