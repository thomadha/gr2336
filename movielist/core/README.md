# Core
## About movielist\core

The core folder for the Movie App contains all code for the functionality of the Movie App (javafx application), such as creating Movie and MovieList objects and saving these.

For more information on how the Movie App works, see: [Release3 README.md](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/fc9c16f967deb79b1ca3a573d488ae8833a76ded/docs/release3/README.md) or [Overall README.md](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2336/gr2336/-/blob/35ca9ff71abb5b937204ef06ec9f3f67d3bceb0e/README.md)

## Architecture
Core has three primary uses:
1. It contains the files Movie.java and MovieList.java, that have the core functionality of the entire app. 
2. In the filehandler folder in core you can find the MovieListHandler, that writes, reads and deletes movielists to/from file. These three files are crucial to the functionality of the app.
3. In addition, the folder test\java\core includes tests for the respective classes mentioned. 

These three main parts of core is explained further in: Detailed Description.

## How to use
You do not run the application from core. Core provides functionality that is implemented by the user interface classes

## Detailed Description
### Functionality Classes
**Movie:** This class can initialize objects of the class Movie, which includes the name, score, genre and movie count (amount of times watched) of a movie.
_Filepath: movielist\core\src\main\java\core\Movie.java_

**MovieList:** This class initialize a list of Movie objects that can be modified. This also includes changing the Movie objects stored, for example updating the score or moviecount of a movie if it's been watched multiple times. A movielist also has a username and password associated to it to only allow the right user to access it. 
_Filepath: movielist\core\src\main\java\core\MovieList.java_


**MovieListHandler:** This filehandler for the MovieList class is used to write movielists to file, and read later. It works with the application by writing/saving all movielists to the same file, and the when reading it created a list of MovieLists. From there one can find their MovieList with username and password. It also has a method for deleting a MovieList.
_Filepath: movielist\core\src\main\java\filehandler\MovieListHandler.java_

### Test Classes
**MovieTest:** Is a test-class for the Movie.java class. It tests that all inputs are correct and exceptions are thrown when they should.
_Filepath: movielist\core\src\test\java\core\MovieTest.java_


**MovieListTest:** Is a test-class for the MovieList.java class. It tests that the list itself is working when adding movies, and that it handles duplicates of movies correctly.  
_Filepath: movielist\core\src\test\java\core\MovieListTest.java_

### Other 
The core folder also contain a few other files. The *pom.xml* has specific core configuration, as well as specifying project dependencies, plugins and other relatted settings. In folders containing functionality classes you'll find a *package-info.java* that specifies the package level documentations shortly.

## Codequality
All fields and methods have a javadoc comment explaining what the field is, or what the method does, what the parameters are and whether it returns anything.

The code adheres to the standards of sun_checks.xml, which is the built-in Sun's Style for checkstyle. This is specified in the pom in the root directory, and to test this you therefore need to be in the root directory (i.e movielist). From here you can write: mvn checkstyle:check. If it's not working, you might need to write "mvn clean" and "mvn compile" or "mvn install -DskipTests" beforehand.

The code also can be checked with spotbugs, which is sspecified in the pom.xml in core. To check this you therefore need to be in movielist\core. From here you can write "mvn spotbugs: check". The test should result in no bugs.
