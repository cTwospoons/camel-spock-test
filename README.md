# Camel Spock Testing

A simple project showing how to use Spock to unit test camel routes.

### Introduction

Apache Camel is a popular integration framework with [documentation](http://camel.apache.org/) and [source code](https://github.com/apache/camel) available with instructions on it's use and various components.

Camel's variety of components and message routing logic capabilities makes testing a requirement for ensuring that your Camel routes are performing as expected. Since Camel's routes are built within a context it usually follows that to test those routes you must have an integration test (or testing suite) to verify the routing logic. 

But what if you don't want a full fledged integration test and context just to have a test case to cover some simple routing logic? Do you really need to wire up a test database and provide all the other information an integration test on your application requires just to test a simple conditional in your routing logic? Of course not, unit testing would be the obvious and logical choice. 

To that end, I've chosen my favorite testing framework, [Spock](http://spockframework.org/), to explore a simple way to truly unit test my camel routing logic.  

### Requirements

* Apache Camel
* Spring Boot
* Spock

### Running the App

#### Running locally
```bash
./gradelw bootRun
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



