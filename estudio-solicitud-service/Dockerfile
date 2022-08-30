FROM openjdk:11
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY target/estudio-solicitud-service 0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
CMD ["java", "-jar", "app.jar"]