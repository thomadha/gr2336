package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class for storing Movie objects in a list.
 */
public class MovieList {


  private List<Movie> movies;

  private String username;

  private String password;

  /**
   * Getter for username of movielist.
   *
   * @return a string of username.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Getter for password of the movielist.
   *
   * @return a string of password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Setter for username.
   *
   * @param username of object.
   */
  public void setUsername(final String username) {
    this.username = username;
  }

  /**
   * Setter for password.
   *
   * @param password of object.
   */
  public void setPassword(final String password) {
    this.password = password;
  }

  /**
   * Setter for movies.
   *
   * @param movies in a list.
   */
  public void setMovies(final List<Movie> movies) {
    this.movies = new ArrayList<>(movies);
  }

  /**
   * Constructor for this class.
   */
  public MovieList() {
    this.movies = new ArrayList<Movie>();
  }

  /**
   * Getter for movies.
   *
   * @return ArrayList of movies for object.
   */
  public List<Movie> getMovies() {
    return new ArrayList<Movie>(movies);
  }

  /**
   * Method to add a movie to the movielist object.
   *
   * @param movie to add.
   */
  public void addMovie(Movie movie) {
    if (!checkDuplicate(movie)) {
      movies.add(movie);
    }
    movies.sort(Comparator.comparingDouble(Movie::getScore).reversed());
  }

  /**
   * Method to sort list by amount of times the movie's been watched.
   */
  public void sortByCount() {
    movies.sort(Comparator.comparingInt(Movie::getCount).reversed());
  }

  /**
   * Method to sort movies in list by best rating.
   */
  public void sortByBestRating() {
    movies.sort(Comparator.comparingDouble(Movie::getScore).reversed());
  }

  /**
   * Method to sort movies in list by worst rating.
   */
  public void sortByWorstRating() {
    movies.sort(Comparator.comparingDouble(Movie::getScore));
  }

  /**
   * Getter for number of movies.
   *
   * @return number of movies in list.
   */
  public int getNumberOfMovies() {
    return movies.size();
  }

  /**
   * A toString similar method for the class Movielist.java.
   *
   * @return a string of movies in MovieList object.
   */
  public String movieListToString() {
    String s = "";
    for (Movie movie : movies) {
      s += movie.getName() + ", " + movie.getScore() + "\n";
    }
    return s;
  }

  /**
   * Method to check if a movie is already in the list.
   * If so it updates the movie with mean score and new genre.
   *
   * @param newMovie to be added to the list.
   * @return true if movie was a duplicate, else false.
   */
  public boolean checkDuplicate(Movie newMovie) {
    boolean hasDuplicate = false;
    String newTitle = newMovie.getName();
    for (Movie m : getMovies()) {
      String oldTitle = m.getName();
      if (oldTitle.equals(newTitle)) {
        Double newScore = ((m.getScore() + newMovie.getScore()) / 2);
        String newGenre = newMovie.getGenre();
        m.setScore(newScore);
        m.setGenre(newGenre);
        m.addDuplicate();
        hasDuplicate = true;
      }
    }
    return hasDuplicate;
  }
}
