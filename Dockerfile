FROM openjdk:8
ADD build/libs/demo-0.0.1-SNAPSHOT.jar managing-system.jar
EXPOSE 5001
ENTRYPOINT ["java", "-jar", "managing-system.jar"]