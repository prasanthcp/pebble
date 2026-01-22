FROM eclipse-temurin:17-jdk
COPY target/pebble.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]