En README.md-fil (evt. en fil som README.md lenker til) inni kodingsprosjektet skal beskrive hva appen handler om og er ment å gjøre (når den er mer eller mindre ferdig). Ha med et illustrerende skjermbilde, så det er lettere å forstå. Det må også være minst én brukerhistorie for funksjonaliteten dere starter med.

## About the project

The Movie App is a JavaFX application that allows you to add and display a list of movies with their scores. It's a first draft of a database where you can save the movies you've watched. It's sorted by score, from highest to lowest.

## Features

- Add movies titles and give them a score
- See the list of the movies you've added, sorted
- Add as many movies as you want
- Data is saved to a gson file and stored

## How to use

If you haven't already, you need to clone the repository to your local machine. The app can be run from the App.java class by clicking run, or with mvn javafx:run if Maven is installed.

## User story

Jack loves movies, and needs a way to keep track of the movies that he watches. He is presented with our movie list application, with the logic that is implemented for release 1. Jack wants to add his fifteen favorite movies to the movie list. He opens the application, and enters the first movie's title, genre and his personal score into the respective input fields. When he clicks the "Add movie"-button, the movie details he put in should be validated. If the validation is successful, the movie should be added to the movie list, and displayed on the screen. He continues with this for all of his favorite movies, until a completed and sorted list is displayed on the left side of the screen. Jack is thrilled that he now has a simple way of accessing all of his favorite movies.