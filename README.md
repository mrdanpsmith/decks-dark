# Decks Dark
## Prerequisites
In order to build this project you should have Java 8 installed as your primary JDK and be able to run "java" and "javac" commands on the command line.

## Building
This project uses maven and includes the maven wrapper. It can be built using the following command (from the root of the project):

    ./mvnw clean install

## Running
After the project is built, you can run the service using a java jar command:

    java -jar ./target/decks-dark-0.0.1-SNAPSHOT.jar

## Documentation
For detailed information on how to use the service, you can refer to the docs by visiting the following link after the service is running:

    http://localhost:8080/docs/index.html
