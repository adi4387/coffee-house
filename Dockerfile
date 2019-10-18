FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/coffee-house-0.0.1.jar
ADD ${JAR_FILE} coffee-house.jar
ENTRYPOINT ["java","-jar","/coffee-house.jar"]
