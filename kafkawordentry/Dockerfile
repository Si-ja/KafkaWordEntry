FROM openjdk:22-jdk-slim
ADD target/kafkawordentry-1.0-SNAPSHOT-jar-with-dependencies.jar kafkawordentry-1.0-SNAPSHOT-jar-with-dependencies.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "kafkawordentry-1.0-SNAPSHOT-jar-with-dependencies.jar" ]