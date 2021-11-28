FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/TimingSenderService-V1.jar
COPY ${JAR_FILE} TimingSenderService-V1.jar
ENTRYPOINT ["java","-jar","/TimingSenderService-V1.jar"]