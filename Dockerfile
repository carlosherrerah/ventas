# Usa la imagen base de Gradle para compilar el proyecto
FROM gradle:latest as builder
WORKDIR /source
COPY . .
# Ejecuta la tarea de construcción de Gradle
RUN gradle build -x test

# Cambia a la imagen de base que necesitas para ejecutar la aplicación
FROM eclipse-temurin:17
WORKDIR /bin
# Copia el archivo JAR construido desde la imagen de construcción a la imagen de ejecución
COPY --from=builder /source/build/libs/ventas-0.0.1-SNAPSHOT.jar .

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Define el comando para ejecutar la aplicación
CMD ["java", "-jar", "ventas-0.0.1-SNAPSHOT.jar"]

# docker build -t academia_bedu/ventas:latest .
# docker run -d -p 8080:8080 academia_bedu/ventas:latest
# http://localhost:8080/swagger-ui.html#

# Mofidicar la linea de application.properties: normal o con docker

# C:\Dev\Ventas20
# gradlew clean build 
# gradle bootRun
# java -jar build/lib/ventas-0.0.1-SNAPSHOT.jar
# http://localhost:8080/employees
# http://localhost:8080/swagger-ui/index.html#
