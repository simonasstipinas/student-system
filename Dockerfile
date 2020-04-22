# Building phase...
FROM openjdk:8 as builder

WORKDIR '/app'

COPY ./build.gradle ./build.gradle
COPY ./settings.gradle ./settings.gradle
COPY ./gradle ./gradle
COPY ./gradlew ./gradlew

RUN ./gradlew

COPY ./src ./src

RUN ./gradlew build

# Application phase...
FROM openjdk:8

COPY --from=builder /app/build/libs/demo-0.0.1-SNAPSHOT.jar managing-system.jar

EXPOSE 5000

ENTRYPOINT ["java", "-jar", "managing-system.jar"]