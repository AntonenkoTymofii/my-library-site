# Library Site

## Web-site:

Deploy is here: https://my-library-site.onrender.com

## About project

This is a pet project that implements a simple library of books and authors. 
It provides book search with information about the genre and year of creation 
and the author. There is also an implementation of authorization and registration 
and the implementation of access roles for different pages.

## What I use

The project is based on Java Spring Boot with the Gradle builder. 
The Thymeleaf templating engine was used to display template pages. 
The PostgreSQL relational database was used to store data, and Hibernate 
and Flyway were used to connect the database with the models. Spring 
Security was used to implement security. To simplify the launch on the 
local machine, a Dockerfile and docker-compose were created. 
Testing was implemented using Mockito and JUnit.

## Instructions
In order to run the project on a local computer,
you need to unload the repository with the command:
```
git clone https://github.com/AntonenkoTymofii/my-library-site
```

Next, you will need to run the docker-compose file.
You will need to have the docker desktop downloaded.
You can download the docker on the official website: https://www.docker.com

To run the application, go to the command line and navigate to
the root folder of the uploaded repository.
First, you need to build the project:

```
docker-compose build
```

To see the full process of launching the application, use the command:

```
docker-compose up
``` 

If not, then:

```
docker-compose up -d
```

To stop the application, write the command:

```
docker-compose down
```

To see how the application works on your computer,
follow the link: http://localhost:8080/