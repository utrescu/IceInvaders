# README #

I'm developing a fake shop to test diferent Spring components.

### What is this repository for? ###

* Test Spring Boot, Spring MVC, Spring Security and Spring Data MongoDB
* Version 0.0.1

### How do I get set up? ###

* Summary of set up

1. Install MongoDB
2. import the json to MongoDB  
3. Change application.properties with the IP of the MongoDB server. 
3. build and run the app
4. go to http://localhost:8080

Alternatively, can be run in Docker (see below)

* Configuration
Change the MongoDB IP from src/main/resouces/application.properties to your IP

For example if MongoDB are running in localhost it must be:
    
    spring.data.mongodb.uri=mongodb://127.0.0.1/iceinvaders
    
To create a package execute: 

    $ mvn install
    $ cd target
    $ ls iceInvaders*
    iceInvaders-0.0.1-SNAPSHOT.jar

* Database configuration

There are a JSON with articles to test. Import the provided file into a MongoDB Database: 

    $ mongoimport --db=iceinvaders --collection=articles --file=ice.json

* How to run tests

None yet

* Deployment instructions

It's a Spring Boot Maven project so you can run it with:

    $ mvn spring-boot:run

Also can be executed as a simple Java program:
   
    $ mvn package
    $ cd target
    $ java -jar iceInvaders-0.0.1-SNAPSHOT.jar


### Running in Docker
It's also possible to create a Docker container (will be named utrescu/iceinvaders) with the app inside.

    $ mvn clean package docker:build

And run it with a Mongo container (I like to map mongo database to a local folder)

    # Start a MongoDB container
    $ docker run --name mymongo -v /home/user/data/:/data/db -d mongo    
    
    # Import test data to MongoDB
    $ docker inspect mymongo | grep IPAddress
            "SecondaryIPAddresses": null,
            "IPAddress": "172.17.0.2",
                    "IPAddress": "172.17.0.2",
    $ mongoimport --host 172.17.0.2 --db=iceinvaders --collection=articles --file=ice.json
    
    # Run the app linked to MongoDB Container named "mymongo"    
    $ docker run --name ice -p 8080:8080 --link mymongo -d -t utrescu/iceinvaders 

To stop the app: 

    $ docker stop ice
    
To restart it: 

    $ docker start ice

To remove the container image from your system: 

    $ docker rm ice
    $ docker rmi utrescu/iceinvaders

    

