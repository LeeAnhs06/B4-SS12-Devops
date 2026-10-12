# Build stage: dung JDK 17 de dong goi jar (tuong thich ca 17/21).
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY gradlew gradlew.bat settings.gradle build.gradle ./
COPY gradle gradle
RUN chmod +x gradlew
COPY src src
RUN ./gradlew bootJar --no-daemon -x test

# Runtime stage: JRE nhe de chay app.
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
