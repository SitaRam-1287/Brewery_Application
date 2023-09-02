FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar application-0.0.1-SNAPSHOT.jar.original
ENTRYPOINT ["java","-jar","/application-0.0.1-SNAPSHOT.jar.original"]
EXPOSE 8080
