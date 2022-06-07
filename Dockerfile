FROM maven:3.8-jdk-11 as builds
WORKDIR /app
COPY . /app
RUN mvn install -DskipTests



FROM maven:3.8-jdk-11
WORKDIR /app
COPY --from=builds /app/target/vendingmachine-0.0.1-SNAPSHOT.jar /app/vendingmachine-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "vendingmachine-0.0.1-SNAPSHOT.jar"]
