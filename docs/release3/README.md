## About the project

The Movie App is a JavaFX application that allows you to add and display a list of movies with their scores. This is the third and final draft of a database where you can save the movies you've watched. It's sorted by score, from highest to lowest.

## Assets

Screenshots of the final application:

_The login-page that the application lanches to:_

![release3](assets/release3/(1)LoginPage.png "Picture"){width=500}

_Changed interface after pressing the button to register new user:_

![release3](assets/release3/(2)MakeNewMovieList.png "Picture"){width=500}

_Login-Page with the necessary info written:_

![release3](assets/release3/(3)LoginWithInfo.png "Picture"){width=500}

_The main application page, where you add movies by entering title, score and genre:_

![release3](assets/release3/(4)AddMovieToNewList.png "Picture"){width=500}

_How the movielist looks after adding multiple movies:_

![release3](assets/release3/(5)MoviesInList.png "Picture"){width=500}

_Pop-up when pressing button to delete movielist:_

![release3](assets/release3/(6)DeleteMovieList.png "Picture"){width=500}

_TopRatedPage, accessed from the movielist-page, the back-button brings the user back to its movielist:_

![release3](assets/release3/(7)TopRatedPage.png "Picture"){width=500}

## New features for this release

1. The login feature is improved immensly in this release
    - Each movie list now includes both a username and password, significantly enhancing the security of user data.
    - A new controller and fxml-file has been made to support this new page.
    - This also means that you can close the app and retrieve the list the next time you run it, as all the data related to the movie diary is saved to a file. The logic regarding the file handling is significally improved.
2. A dedicated page is implemented for top rated movies
    - This shows the user an overview for highest rated movies, most viewed and most popular genre.
    - The data displayed in this page is retrieved from all movie lists within the application, combining the average scores and summarizing the total view count. 
    - Similarily to the login page, this page has its own controller and fxml-file
3. Making it possible to delete your movie list profile
    - Implementing a new button within the application interface, enabling users to delete their profiles.
    - The user is given an alert when pressing this button, ensuring that they actually want their movie diary deleted.
    - If the user selects "Cancel," they are redirected back to their list. However, choosing "OK" deletes their movie diary from the file, causing the application to return to the home page, since the previously accessed movie list no longer exists.

## How to use

If you haven't already, you need to clone the repository to your local machine. The app can be run from the App.java class by clicking run, or with mvn javafx:run if Maven is installed.

## Diagrams related to the release

For this release, the group has included updated versions of the three diagrams, to illustrate the architecture of the application. The class diagram, sequence diagram and package diagram are linked underneath:


**Basic Functionality Sequence Diagram:**
![Sequence Diagram](http://your-plantuml-server-url.com/plantuml/png/ZP9DJiCm48NtFiKiGQ8Se0lKg6mgqRA8EC0m7g81npRs65HEZzDKaAX4OkE_zxutrjYRh9Yqz0vQVY_iYU5Ui4lOc_mcFc9TGQj7nv0r2GtMgNA6zfmpTjmuwNpFNe7gmCaO90q9dlAmheWAISIgDp5kWzSKdAlILDc73-47oNfvQimG1AkxERw6nbeuyEVeWfsFHStCCZ0dSGtRnAWyiJy-lpAf6JUho_2fwqpRnVIT5nLqyiLpxifZppUGAQGwiTBjzJyh_YOkLHsQDVPW1cg0Knj_F8l07ywbIrM8BRI5gBxuIt7kQCo6lQtVuXi0 "Sequence Diagram")

**Controllers Sequence Diagram:**
![Sequence Diagram](http://your-plantuml-server-url.com/plantuml/png/dLNRJkGm37tFL_XnaM1vdmV4C6WH4XU9BXyG4ay9aoObIK6pNu_JbkarNU2bhMmVd-FOJizzu2vKXOOCNoiq0k5IyTpnWlcTCYMdByZ2NYFuu9I8eNrF2xf5xtcEQwroKw098EY1ZZ4kWdNmwECtGO8IgkJalx6vCXjhWhDQaxFl-slB1nvG9jphianuRksRmXlbmzJA68iYuEHiJBs2jPIDyVG1Sq9JsBKfgy16aH5ye5Z17sKaK7728QdjF73_z88YGB1WIpIiSvnGWe7wLGyLOr4UL8xYBb4ZaTJ53LnrqWYYElmEjYI8d5lb2XwKDM3p7aUTXQmfbXOvPXYKtIhfplgBzSaQ9rcA-yh21HUxHUwDjlu7r2B2vCovJKRXdfhW6pFqXYmfEexE7RutNEtKp1T0ufivwSAREMcAI1RmEKqDMYQpNniL5DVg7mvnlmmly1Yyi8vU6CJleoyoTXdbObFY6Go0DFZZxYG0Cv4fKGH22Lab15qrsqhhVR8fsRDzZvS8rBT8W_UB9SIszrTn2rMyiOQofjVIpFfEWqPKcNMXBXgUzlKiR9wj4fWyh4FyjzH4L7_Nvg8_5-w_afUtRbb8ktfp7DF6rMqWst3rs3aQIR-T3m00 "Sequence Diagram")



## Architecture

The architecture of the project is described in the diagram above. The project can be found within the folder movielist, where the two main dividers are "core" and "ui". Classes like the Movie-object, MovieList-object, as well as the file handler can be found here. The "ui"-folder includes the Application itself, as well as its controllers. In addition to the "AppController" from previous releases, this version of the project has "LoginController" and "TopRatedController" as well. The json-file where the data is stored can also be located here. Tests for each of the packages can be found in their respective folder, for example ui/src/main/test/java/ui for "ui". Documentation is to be found in the docs-package, whereas the screenshots of the application is in the "assets"-folder. The fxml-files can be found within the "resources"-folder in ui.

For all complete filepaths, see the README.md-file at the outermost level.

## Work habits

Throughout this release, the team has placed immense emphasis on maintaining the work habits we cultivated from the beginning. Initially, none of us were particularly familiar with working on GitHub, making it a challenge at the start. However, this challenge transformed into a much-improved workflow by this final release. Despite our initial lack of knowledge, each member of the team has undergone significant learning. We have actively utilized development tasks on GitHub, ensuring that these tasks serve as the cornerstone for all our work. Every piece of code has been associated with these tasks, employing informative commit messages and logical branch names.

Our commitment to clarity is evident in our commit messages, which are made as descriptive as possible. Additionally, all merges have been conducted through merge requests linked to relevant issues. Each merge request has undergone thorough review by another team member to guarantee the overall code quality within the group. This approach has been fundamental in our collaborative efforts, leading to a successful and polished final release.

Comments on improvement in documentaion and code quality for this release:
    - Checkstyle rules have been implemented and are followed in the wholde directory, we use sun rules (sun_checks.xml)
    - We've used spotbugs to check for bugs. Core should run without bugs and ui should run with three expected bugs related to setting the stage.
    - All main folders in the root directory (core, ui, springboot) should now have a README.md file to explain what the folder contains
    - Javadoc comments should now be before all methods and fields in all classes.