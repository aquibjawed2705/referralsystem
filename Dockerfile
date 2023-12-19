FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar ReferalSystem.jar
ENTRYPOINT ["java","-jar","/ReferalSystem.jar"]
EXPOSE 8080