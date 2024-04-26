FROM openjdk:17
EXPOSE 8081
COPY target/products-service.jar products-service.jar
ENTRYPOINT ["java", "-jar", "/products-service.jar"]