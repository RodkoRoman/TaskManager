FROM openjdk:17-alpine
COPY ./target/taskmanager-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080:8080

ENTRYPOINT ["exec", "java", "-jar", "/app.jar"]
