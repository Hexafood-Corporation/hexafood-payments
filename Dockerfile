FROM amazoncorretto:17-alpine
WORKDIR /app
EXPOSE 8080
ADD ./build/libs/hexafood-payments-0.0.1-SNAPSHOT.jar server.jar
CMD ["java", "-jar", "server.jar"]