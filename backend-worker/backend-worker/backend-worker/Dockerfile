# Use a imagem base do OpenJDK para Java 17
FROM openjdk:17-alpine

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo JAR construído para o contêiner
COPY target/*.jar /app/backend-worker.jar

# Comando para executar o aplicativo quando o contêiner for iniciado
CMD ["java", "-jar", "backend-worker.jar"]