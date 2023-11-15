# UI
## About movielist\ui

The ui, or user interface, folder of the Movie App contains all code for how the app interacts with a user. It's a javafx application. 

For more information on how the Movie App works, see: [Release3 README.md](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/fc9c16f967deb79b1ca3a573d488ae8833a76ded/docs/release3/README.md) or [Overall README.md](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/35ca9ff71abb5b937204ef06ec9f3f67d3bceb0e/README.md)

## Architecture
Core has four primary uses. 
1. It contains the controllers of the javafx app, meaning the methods/commands that users use to interact with the app.
2. It contains a json folder where the movielists that are created are saved to file.
3. It contains a resources folder that stores the fxml files for user interface.
4. It contains test classes.

## How to use
You can run the application from the ui folder. To do this you either run the [App.java](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/1df7ea33d8dd74117da0140aa32229d4166acee1/movielist/ui/src/main/java/ui/App.java) class, or write mvn javafx:run in the terminal. 

## Detailed Description
### Controller Classes
**App:** This class starts and launched the JavaFX app.
_Filepath: movielist\ui\src\main\java\ui\App.java_

**AppController:** This controller is the main functionality of the user interface, and the other two controllers are built around this. In this controller one' on a page where someone can add objects of the Movie-class to a MovieList linked to your username.
_Filepath: movielist\ui\src\main\java\ui\AppController.java_


**LoginController:** This controller is the first page when launching the app. It gives the user the option to either login or create a new user. When clicking login/create new the controller opens the AppController page and either shows the user's MovieList or an empty one.
_Filepath: movielist\ui\src\main\java\ui\LoginController.java_

**TopRatedController:** This controller can be accessed by clicking "Open top lists" in the AppController. Here you can sort a MovieList object containing all the movies ever added to the app by best and worst rating, as well as amount of times a movie has been watched.
_Filepath: movielist\ui\src\main\java\ui\TopRatedController.java_

### json
**MovieList.json:** Contains all MovieLists that are created in the App. If app is run correctly it will be able to open lists that are made in previous runs. The [MovieListHandler](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/5c02099bc95888c26d57d3989272abd208c33148/movielist/core/src/main/java/filehandler/MovieListHandler.java) has access to deleting movielists from this json file.

### Resources\ui folder
**LoginPage.fxml:** is the fxml file for the login page, connected to the LoginController.
_Filepath: movielist\ui\src\main\resources\ui\LoginPage.fxml_

**MovieListApp.fxml:** is the fxml file for the MovieList page, connected to the AppController.
_Filepath: movielist\ui\src\main\resources\ui\MovieListApp.fxml_

**TopRated.fxml:** is the fxml file for the top rated movies page, connected to the TopRatedController.
_Filepath: movielist\ui\src\main\resources\ui\TopRated.fxml_


### Test Classes
**AppTest:** Is a test-class for the fxml app. Tests basic functionality of the app and controllers.
_Filepath: movielist\ui\src\test\java\ui\AppTest.java_



### Other 
The ui folder also contain a few other files. The *pom.xml* has specific ui configuration, as well as specifying project dependencies, plugins and other related settings. In folders containing controller classes (movielist\ui\src\main\java\ui) you'll find a *package-info.java* that specifies the package level documentations shortly.

## Codequality
All fields and methods have a javadoc comment explaining what the field is, or what the method does, what the parameters are and whether it returns anything.

The code adheres to the standards of sun_checks.xml, which is the built-in Sun's Style for checkstyle. This is specified in the pom in the root directory, and to test this you therefore need to be in the root directory (i.e movielist). From here you can write: mvn checkstyle:check. If it's not working, you might need to write "mvn clean" and "mvn compile" or "mvn install -DskipTests" beforehand.

The code also can be checked with spotbugs, which is sspecified in the pom.xml in ui. To check this you therefore need to be in movielist\ui. From here you can write "mvn spotbugs: check". The test should result in three bugs, which are related to setting the stage in the controllers and are expected outputs.