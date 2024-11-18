FROM eclipse-temurin:23-jdk-noble

WORKDIR /src

COPY . ./

ENTRYPOINT ["./gradlew","build"]
