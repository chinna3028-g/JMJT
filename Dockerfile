FROM openjdk:8-jre
ADD /target/usermanagement-1.0.jar //
ADD /src/main/resources/db-config.properties /appdata/
ENTRYPOINT ["java", "-jar", "/usermanagement-1.0.jar"]
