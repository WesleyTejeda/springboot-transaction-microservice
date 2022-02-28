FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} transaction-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/transaction-0.0.1-SNAPSHOT.jar"]