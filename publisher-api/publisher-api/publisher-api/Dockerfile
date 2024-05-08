# Use a imagem base do OpenJDK para Java 17
FROM openjdk:17

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo JAR construído para o contêiner
COPY target/*.jar /app/publisher-api.jar


# Comando para executar o aplicativo quando o contêiner for iniciado
CMD ["java", "-jar", "publisher-api.jar"]