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

## User stories with expanded functionality

Jack enjoys rewatching his favorite movies from time to time. Sometimes this means that he wants to give the movie a new score. Whether he picked up on something new, or simply changes his opinion, Jack wants the opportunity to implement this to his movie diary. In the previous release, adding the same movie multiple times would add duplicates to the movie list. As Jack usually watches his absolute favorites many times, the logic in the first release provides a poor solution. However, with the expanded functionality in release 2, Jack can now keep track of his rewatches in a much simpler way. When Jack adds a movie with a title that already exists in his movie list, the two entries now combine. In the movie list view, the new generated score is a average of all entries, while the genre is updated to the most recent. Additionally, each movie object now has a corresponding counter, keeping track of how many times Jack has watched that specific movie.

After discovering the movie-list application, Jack wants to show his friends in the movie group he is a part of. His friend Alex is super excited, and can't wait to add his favorites to the list. However, with the logic from release 1, Jack and Alex could not separate their lists. This means that Jack would completely lose track of which movies he has watched, and even worse, their separate scores of the same movies would combine! Gladly, Jack did not show the application to Alex and the rest of the group before release 2. This version of the application allows Jack to create his own movie list connected to a personal username. This way, when Alex opens the application to make his movie list, he is able to create a new personal list, instead of sharing with everyone using the application. 