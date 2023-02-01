FROM adoptopenjdk/openjdk11:alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} votacao.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/votacao.jar"]
