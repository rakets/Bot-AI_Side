FROM openjdk:24-ea-17-slim-bullseye
WORKDIR /app
COPY /target/route-optimizer-1.0-SNAPSHOT.jar /app/route-optimizer.jar
COPY API_token_cloud_google.txt /app/API_token_cloud_google.txt

ENTRYPOINT ["java", "-jar", "route-optimizer.jar"]
