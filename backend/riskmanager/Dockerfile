FROM eclipse-temurin:17-jdk-focal
VOLUME /tmp
COPY target/*.jar riskmanager.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/riskmanager.jar"]
