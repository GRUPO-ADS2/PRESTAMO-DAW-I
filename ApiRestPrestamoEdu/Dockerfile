# Etapa de construcción
FROM openjdk:17.0.2 as builder

WORKDIR /app/ApiRestPrestamoEdu

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Asegurar ejecución del wrapper
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

# Crear archivo de configuración del wrapper si no existe
RUN echo "wrapperVersion=3.3.1" > .mvn/wrapper/maven-wrapper.properties && \
    echo "distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip" >> .mvn/wrapper/maven-wrapper.properties

# Primer build sin código fuente para descargar dependencias
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/ || true

# Copiar código fuente
COPY src src

# Compilar aplicación
RUN ./mvnw clean package -DskipTests

# Etapa final
FROM openjdk:17.0.2

WORKDIR /app
RUN mkdir logs

# Cambiar nombre del .jar según el generado por Maven
COPY --from=builder /app/ApiRestPrestamoEdu/target/ApiRestPrestamoEdu-3.3.6.jar Api_Prestamo.jar

# (Opcional) Si usas autenticación externa como Dialogflow, copia claves
# COPY dialogflow-key.json /dialogflow-key.json

EXPOSE 8081

CMD ["java", "-jar", "Api_Prestamo.jar"]
