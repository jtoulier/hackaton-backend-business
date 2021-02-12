FROM openjdk:11-jre
COPY ./target/hackaton-backend-business-0.0.1-SNAPSHOT.jar /opt/hackaton-backend-business.jar
ENTRYPOINT ["java", "-Djava.file.encoding=UTF-8", "-jar", "/opt/hackaton-backend-business.jar"]