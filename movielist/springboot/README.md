# Springboot
## About springboot

SpringBoot is a framework that implements REST API for building Java-based web applications. REST API is a web service that uses HTTP requests to perform CRUD (Create, Read, Update, Delete) operations. 
[Source](https://folk.ntnu.no/olso/wu/rest/rest.html)

For more information on how the Movie App works, see: [Release3 README.md](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/fc9c16f967deb79b1ca3a573d488ae8833a76ded/docs/release3/README.md) or [Overall README.md](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/35ca9ff71abb5b937204ef06ec9f3f67d3bceb0e/README.md)

## Architecture
The SpringBoot folder is responsible for handling the backend logic of the Movie App, and includes:
1. Controllers
2. Services
3. The application itself

A more indepth look to these four main points can be found in _Detailed Description_.

## How to use
To run the SpringBoot application there are a few tools that are needed to follow: (Note that you have to change your directory to the springboot folder)
1. Build project: mvn clean install
2. Start SprinBoot: mvn spring-boot:run
3. When you get confirmation that the app is running, you can access the Rest API with the following URL: **http://localhost:8080/movielist**

## Detailed Description
### Springboot classes
**MovieListRestController:**

This class serves as the controller for orchestrating all REST API calls, managing incoming HTTP requests through its defined methods, including @GetMapping, @PostMapping, @PutMapping, and @DeleteMapping. Acting as a vital bridge between the client and the core functionality of the MovieList, this controller mirrors the structure of application controllers in the UI. Much like its counterparts in the UI, the restController contains methods essential for the application's functionality, interacting with the service class. The service class, in turn, functions similarly to the core of the application. Through the restController, clients can perform actions such as creating or displaying movie lists, adding movies to lists, and more.

_Filepath: movielist\springboot\src\main\java\movielist\springboot\MovieListRestController.java_

**MovieListService:** 

This is the service component of the movie list program, designed to encapsulate the core logic. Consequently, the MovieListService has abstracted methods and operations capable of manipulating movie lists and interacting with the data source, which, in this case, is a file system. All essential methods for executing the application, along with its inherent logic, are implemented within the MovieListService. When the server is operational and the application employs remote access, these methods are invoked to execute the internal logic of the application. Thus, the service-class functions as an intermediary layer between the REST-controller and the underlying data storage.

_Filepath: movielist\springboot\src\main\java\movielist\springboot\MovieListService.java_

**ServerController:** 

A simple class to check the health of the server, meaning if it is running.
Will return the strink "OK" if server is running. 

_Filepath: movielist\springboot\src\main\java\movielist\springboot\ServerController.java_

**SpringbootApplication:** 

The class that launces the Springboot web app. 

_Filepath: movielist\springboot\src\main\java\movielist\springboot\SpringbootApplication.java_

### Dataaccess

_See ui documentation for more information on the dataaccess files that interact with the rest-API._


### Other
The springboot folder also contains a few other files.

Additionally, the *pom.xml* has specific springboot configuration, as well as specifying project dependencies, plugins and other related settings.

Similar to the organization of UI and core components, the tests for the Spring Boot logic are located in a dedicated folder:

**SpringbootApplicationTests** 
_Filepath: movielist/springboot/src/test/java/movielist/springboot_

## Codequality
All fields and methods have a javadoc comment explaining what the field is, or what the method does, what the parameters are and whether it returns anything.

The code adheres to the standards of sun_checks.xml, which is the built-in Sun's Style for checkstyle. This is specified in the pom in the root directory, and to test this you therefore need to be in the root directory (i.e movielist). From here you can write: mvn checkstyle:check. If it's not working, you might need to write "mvn clean" and "mvn compile" or "mvn install -DskipTests" beforehand.

The code also can be checked with spotbugs, which is sspecified in the pom.xml in ui. To check this you therefore need to be in movielist\springboot. From here you can write "mvn spotbugs: check". **SKRIV FORVENTET RESULTAT AV KJØRINGEN**