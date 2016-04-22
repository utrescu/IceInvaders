# README #

I'm developing a fake shop to test diferent Spring components.

### What is this repository for? ###

* Test Spring Boot, Spring MVC, Spring Security and Spring Data MongoDB
* Version 0.0.1

### How do I get set up? ###

* Summary of set up

Creating a Mongo collection with articles, build and run the app and go to localhost:8080

* Configuration

It's a Spring Boot Maven project so you can run it with: 

    $ mvn spring-boot:run
    
To create a package execute: 

    $ mvn install
    $ cd target
    $ ls iceInvaders*
    iceInvaders-0.0.1-SNAPSHOT.jar

* Database configuration

There are a JSON with articles to test. Import the provided file into a MongoDB Database: 

    $ mongoimport --db=iceinvaders --collection=articles --file ice.json

* How to run tests

None yet

* Deployment instructions

Compile and run it as a Java program. Copy the generated JAR and execute it
   
    $ java -jar iceInvaders-0.0.1-SNAPSHOT.jar
