# UI
## About movielist/ui

The ui, or user interface, folder of the Movie App contains all code for how the app interacts with a user. It's a javafx application. 

For more information on how the Movie App works, see: [Release3 README.md](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/fc9c16f967deb79b1ca3a573d488ae8833a76ded/docs/release3/README.md) or [Overall README.md](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/35ca9ff71abb5b937204ef06ec9f3f67d3bceb0e/README.md)

## Architecture
Ui has five primary uses, and consequently contains:
1. The controllers of the javafx app, meaning the methods/commands that users use to interact with the app. Our application has 3 controllers, each with its own page. These can be found within the inner folder "ui".
2. A "json" folder, where the movielists that are created are saved to file.
3. A "resources" folder that store the fxml files for user interface.
4. From release 3, a "dataaccess"-folder, supporting the REST-API. In this folder there is a localaccess and a remoteaccess, both implementing the same interface. 
5. Test classes within its own folder "test".

## How to use
You can run the application from the ui folder. To do this you either run the [App.java](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/1df7ea33d8dd74117da0140aa32229d4166acee1/movielist/ui/src/main/java/ui/App.java) class, or write mvn javafx:run in the terminal. 

## Detailed Description
### UI (Controller Classes)
**App:** 

This class starts and launched the JavaFX app.

_Filepath: movielist\ui\src\main\java\ui\App.java_

**AppController:** 

This controller includes the main functionality of the user interface, which the other two controllers are built around. In this controller one' on a page where someone can add objects of the Movie-class to a MovieList linked to your username.

_Filepath: movielist\ui\src\main\java\ui\AppController.java_


**LoginController:** 

The loginController  is the first page when launching the app. It gives the user the option to either login or create a new user. When clicking login/create new, the controller opens the AppController page and either shows the user's MovieList or an empty new one.

_Filepath: movielist\ui\src\main\java\ui\LoginController.java_

**TopRatedController:** 

The TopRatedController can be accessed by clicking "Open top lists" in the AppController. Here you can sort a MovieList object containing all the movies ever added to the app by best and worst rating, as well as amount of times a movie has been watched.

_Filepath: movielist\ui\src\main\java\ui\TopRatedController.java_

### json
**MovieList.json:** 

Contains all MovieLists that are created in the App. If the app is run correctly, it will be able to open lists that are made in previous runs. The [MovieListHandler](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/5c02099bc95888c26d57d3989272abd208c33148/movielist/core/src/main/java/filehandler/MovieListHandler.java) has access to deleting movielists from this json file.

### Resources
**LoginPage.fxml:** 

The fxml file for the login page, connected to the LoginController.

_Filepath: movielist\ui\src\main\resources\ui\LoginPage.fxml_

**MovieListApp.fxml:** 

The fxml file for the MovieList page, connected to the AppController.

_Filepath: movielist\ui\src\main\resources\ui\MovieListApp.fxml_

**TopRated.fxml:** 

The fxml file for the top rated movies page, connected to the TopRatedController.

_Filepath: movielist\ui\src\main\resources\ui\TopRated.fxml_


### Dataaccess Classes
**MovieListAccess:** 

Interface for the access-classes. Includes all the necessary methods for the application to run and function. Made to simplify the implementation of running on either a local or remote server from the controller classes.

_Filepath: movielist\ui\src\main\java\dataaccess\MovieListAccess.java_


**MovieListLocalAccess:** 

Implements interface MovieListAccess. Uses mostly the logic from core, especially calling on methods in MovieListHandler. Is used when a remote server is not running.

_Filepath: movielist\ui\src\main\java\dataaccess\MovieListLocalAccess.java_

**MovieListRemoteAccess:** 

Implements interface MovieListAccess. Sends HTTP-requests to the REST-API, performing the same logic as the localAccess class, but through the methods in  the springboot-folder. Is used when a remote server is running. This is determined when the application is launched in the App-class, where setUpAccess(); in the first controller is called.

_Filepath: movielist\ui\src\main\java\dataaccess\MovieListRemoteAccess.java_


### Test Classes
**AppTest:** 

Test-class for the fxml app. Tests basic functionality of the app and controllers.

_Filepath: movielist\ui\src\test\java\ui\AppTest.java_



### Other 
The ui folder also contain a few other files. The *pom.xml* has specific ui configuration, as well as specifying project dependencies, plugins and other related settings. In folders containing controller classes (movielist\ui\src\main\java\ui) you'll find a *package-info.java* that specifies the package level documentations shortly.

## Codequality
All fields and methods have a javadoc comment explaining what the field is, or what the method does, what the parameters are and whether it returns anything.

The code adheres to the standards of sun_checks.xml, which is the built-in Sun's Style for checkstyle. This is specified in the pom in the root directory, and to test this you therefore need to be in the root directory (i.e movielist). From here you can write: mvn checkstyle:check. If it's not working, you might need to write "mvn clean" and "mvn compile" or "mvn install -DskipTests" beforehand.

The code also can be checked with spotbugs, which is sspecified in the pom.xml in ui. To check this you therefore need to be in movielist\ui. From here you can write "mvn spotbugs: check". The test should result in three bugs, which are related to setting the stage in the controllers and are expected outputs.
