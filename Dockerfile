FROM openjdk:21-slim

LABEL dev="https://github.com/michael-pena"

WORKDIR /app

# copy whole project for building in the container
#COPY . .
COPY ./target/jobtrackerv2-0.0.1-SNAPSHOT.jar jobtrackerv2-0.0.1-SNAPSHOT.jar

EXPOSE 8080

# build the jars in the container
#RUN ./mvnw clean install 
ENTRYPOINT [ "java", "-jar", "jobtrackerv2-0.0.1-SNAPSHOT.jar" ]