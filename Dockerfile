FROM openjdk:8
EXPOSE 8080
ADD target/JMJT.jar JMJT.jar
ENTRYPOINT ["java","-jar","JMJT.jar"]