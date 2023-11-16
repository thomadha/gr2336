package movielist.springboot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Movie;
import core.MovieList;
import filehandler.MovieListHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
/**
 * Class for handeling server calls.
 */

@Service
public class MovieListService {

  public MovieList movieList;
  public List<MovieList> movieLists;
  public MovieListHandler movieListHandler; 
  Gson gson = new GsonBuilder().setPrettyPrinting().create();

  /**
   * Method for initiating a movielist-service.
   */
  public MovieListService() {
    movieList = new MovieList();
    movieLists = new ArrayList<MovieList>();
    movieListHandler = new MovieListHandler("/src/main/java/json/MovieList.json"); 
    //movieListHandler.saveToFile(movieList);
  }

  public String getUsername() {
    return movieList.getUsername();
  }

  /**
   * Getter method for passwords to a given movielist/user.
   *
   * @param username corresponding to the given password. 
   * @return returns a String password. 
   */
  public String getPassword(String username) {
    MovieList movieList = getMovieListByUsername(username);
    String password = movieList.getPassword();
    return password;
  }

  public String getPassword() {
    return movieList.getPassword();
  }

  public List<Movie> getMovies() {
    return movieList.getMovies();
  }

  public int getNumberOfMovies() {
    return movieList.getNumberOfMovies();
  }
  
  /**
   * Getter-method for number of movies.
   *
   * @param username numbers of movies from the given movielist. 
   * @return returns the int size of the given movielist
   */
  public int getNumberOfMovies(String username) {
    MovieList movieList = getMovieListByUsername(username);
    List<Movie> movies = movieList.getMovies(); 
    return movies.size();
  }

  public List<MovieList> getMovieLists() {
    return movieListHandler.getAllMovieListsFromFile(); 
  }

  public MovieList getMovieList() {
    return movieList;
  }

  /**
   * Method returns movielist to a specific username.
   *
   * @param username given username.
   * @return movielist if valid, null if invalid
   */
  public MovieList getMovieListByUsername(String username) {
    movieLists = movieListHandler.getAllMovieListsFromFile();
    for (MovieList movieList : movieLists) {
      if (movieList.getUsername().equals(username)) {
        return movieList;
      }
    }
    return null;
  }

  public void addMovieList(MovieList movieList) {
    movieListHandler.saveToFile(movieList);
  }

  /**
   * Method deleting movielist identified by username.
   *
   * @param username whos movieslist should be deleted.
   * @throws IOException Throws input exception if wrong username
   */
  public void deleteMovieList(String username)throws IOException {
    if (username == null || username.equals("")) {
      throw new IOException("Name is null");
    }
    movieLists = movieListHandler.getAllMovieListsFromFile();
    int i = 0;
    for (MovieList movieList : movieLists) {
      if (movieList.getUsername().equals(username)) {
        movieLists.remove(i);
        movieListHandler.removeMovieList(movieList);
        break;
      }
      i++;
    }
  }

  /**
   * Method adding movie to the movielist, identified by username.
   *
   * @param username list to be added.
   * @param movie the movie that should be added. 
   */
  public void addMovieToList(String username, Movie movie) {
    MovieList movieList = getMovieListByUsername(username); 
    movieList.addMovie(movie);
    addMovieList(movieList);
  }

  /**
   * Getter-method for Movie objects. 
   *
   * @param username from which movielist the movie is gotten from. 
   * @param movieTitle String identifier for the given movie. 
   * @return returns the Movie-element if valid, null else. 
   */
  public Movie getMovie(String username, String movieTitle) {
    MovieList movieList = getMovieListByUsername(username); 
    List<Movie> movies = movieList.getMovies(); 
    for (Movie m : movies) {
      if (m.getName().equals(movieTitle)) {
        return m;
      }
    }
    return null;
  }

  /**
   * method for creating a new movielist with username and password.
   *
   * @param username of movielist
   * @param password of movielist
   * @return the new and empty movielist
   */
  public MovieList newMovieList(String username, String password) {
    MovieList newMovieList = new MovieList();
    newMovieList.setUsername(username);
    newMovieList.setPassword(password);
    addMovieList(newMovieList);
    return newMovieList;
  }
}
