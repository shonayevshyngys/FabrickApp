FROM openjdk:17-alpine
COPY target/FabrickApp-0.0.1-SNAPSHOT.jar FabrickApp-0.0.1-SNAPSHOT.jar
EXPOSE 8000
CMD ["java", "-jar","FabrickApp-0.0.1-SNAPSHOT.jar"]
