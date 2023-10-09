## About the project

The Movie App is a JavaFX application that allows you to add and display a list of movies with their scores. This is the second draft of a database where you can save the movies you've watched. It's sorted by score, from highest to lowest.

## New features for this release

- If you add the same movie more than once, the data connected combines instead of making duplicates
    - In the json file, a count attribute is added, and shows how many times you have watched the movie
    - The genre attribute is updated to the latest contribution
    - The score is generated as an average from all the times you have watched it
- A login feature is also added
    - This seperates your movie list from other users

## How to use

If you haven't already, you need to clone the repository to your local machine. The app can be run from the App.java class by clicking run, or with mvn javafx:run if Maven is installed.