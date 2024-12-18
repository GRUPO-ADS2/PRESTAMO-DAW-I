FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/ApiRestPrestamoEdu-3.3.6.jar
COPY ${JAR_FILE} Api_Prestamo.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "Api_Prestamo.jar"]
