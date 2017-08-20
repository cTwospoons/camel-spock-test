# Camel Spock Testing

A simple project showing how to use Spock to unit test camel routes.

### Introduction

Apache Camel is a popular integration framework with [documentation](http://camel.apache.org/) and [source code](https://github.com/apache/camel) available with instructions on it's use and various components.

This project uses [Spock](http://spockframework.org/) to explore a simple way to unit test routing logic avoiding the need for a full-fledged integration context.  

### Requirements

* Apache Camel
* Spring Boot
* Spock
* Groovy

### Running the App

#### Running locally
```bash
./gradlew bootRun
```

#### Running the tests
```bash
./gradlew test
```

#### Sending messages along the Camel routes  
The app starts a basic Spring context with Spring Boot putting a request listener on the default port. 

To send a message along the Camel routes when the app is running, issue a simple GET request to 

```bash
curl http://localhost:8080/simple
```



