FROM openjdk:24
LABEL authors="devamkumar"
WORKDIR /app
COPY build/libs/*.jar app.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","app.jar"]
