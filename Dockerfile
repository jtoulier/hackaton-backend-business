FROM openjdk:11-jre
COPY ./target/backend-0.0.1-SNAPSHOT.jar /opt/backend.jar
ENTRYPOINT ["java", "-Djava.file.encoding=UTF-8", "-jar", "/opt/backend.jar"]