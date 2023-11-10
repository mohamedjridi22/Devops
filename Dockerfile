FROM openjdk:11
EXPOSE 8089
ADD target/gestion-station-ski-0.0.1.jar gestion-station-ski-0.0.1.jar
ENTRYPOINT ["java","-jar","/gestion-station-ski.jar"]
