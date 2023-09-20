FROM openjdk:17-alpine
RUN apk add --no-cache libstdc++
COPY FabrickPaymentQuery/target/FabrickPaymentQuery-0.0.1-SNAPSHOT.jar FabrickPaymentQuery-0.0.1-SNAPSHOT.jar
EXPOSE 8000
CMD ["java", "-jar","FabrickPaymentQuery-0.0.1-SNAPSHOT.jar"]
