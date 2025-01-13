FROM openjdk:17-jdk-slim AS build
WORKDIR /workspace
COPY . .
RUN ./mvnw clean package -DskipTests
FROM openjdk:17-jre-slim
WORKDIR /app
COPY --from=build /workspace/target/quarkus-app/quarkus-run.jar /app/quarkus-run.jar
EXPOSE 8081
CMD ["java", "-jar", "quarkus-run.jar"]