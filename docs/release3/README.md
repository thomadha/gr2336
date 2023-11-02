## About the project

The Movie App is a JavaFX application that allows you to add and display a list of movies with their scores. This is the third and final draft of a database where you can save the movies you've watched. It's sorted by score, from highest to lowest.

## New features for this release

- If you add the same movie more than once, the data connected combines instead of making duplicates
    - In the json file, a count attribute is added, and shows how many times you have watched the movie
    - The genre attribute is updated to the latest contribution
    - The score is generated as an average from all the times you have watched it
- A login feature is also added
    - This seperates your movie list from other users
    - It works by writing in your username and clicking "Save & Close". If you want to open the list, you write in your username and click "Open"
    - Currently it is not possible to save your list for later use. Meaning if you close the app after you run it and saved a list, it will not be possible to open the next time you run it. This is planned to implement for the next release.

## How to use

If you haven't already, you need to clone the repository to your local machine. The app can be run from the App.java class by clicking run, or with mvn javafx:run if Maven is installed.

## Diagrams related to the release

For this release, the group has included updated versions of the three diagrams, to illustrate the architecture of the application. The class diagram, sequence diagram and package diagram are linked underneath:

## ADD LINKS HERE WHEN FINISHED!


## Architecture

The architecture of the project is described in the diagram above. The project can be found within the folder movielist, where the two main dividers are "core" and "ui". Classes like the Movie-object, MovieList-object, as well as the file handler can be found here. The "ui"-folder includes the Application itself, as well as its controllers. In addition to the "AppController" from previous releases, this version of the project has "LoginController" and "TopRatedController" as well. The json-file where the data is stored can also be located here. Tests for each of the packages can be found in their respective folder, for example ui/src/main/test/java/ui for "ui". Documentation is to be found in the docs-package, whereas the screenshots of the application is in the "assets"-folder. The fxml-files can be found within the "resources"-folder in ui.

For all complete filepaths, see the README.md-file at the outermost level.

## Work habits

