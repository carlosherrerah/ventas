# Usa la imagen base de Gradle para compilar el proyecto
FROM gradle:latest as builder
WORKDIR /source
COPY . .
# Ejecuta la tarea de construcción de Gradle
RUN gradle build

# Cambia a la imagen de base que necesitas para ejecutar la aplicación
FROM adoptopenjdk:17-jre-hotspot
WORKDIR /bin
# Copia el archivo JAR construido desde la imagen de construcción a la imagen de ejecución
COPY --from=builder /source/build/libs/ventas-0.0.1-SNAPSHOT.jar .

# Define el comando para ejecutar la aplicación
CMD ["java", "-jar", "ventas-0.0.1-SNAPSHOT.jar"]


# C:\Dev\Ventas20
# gradlew clean build 
# gradle bootRun
# java -jar build/lib/ventas-0.0.1-SNAPSHOT.jar
# http://localhost:8080/employees
# http://localhost:8080/swagger-ui/index.html#

# docker build -t academia_bedu/ventas:latest .
# docker run -p 8080:8081 academia_bedu/ventas:latest
# http://localhost:8080/swagger-ui.html#

