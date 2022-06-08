




FROM maven:3.8-jdk-11
WORKDIR /app
EXPOSE 8080
ADD target/vendingmachine-0.0.1-SNAPSHOT.jar vendingmachine-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "vendingmachine-0.0.1-SNAPSHOT.jar"]
