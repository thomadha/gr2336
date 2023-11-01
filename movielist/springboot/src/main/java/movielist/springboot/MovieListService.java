package movielist.springboot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import core.MovieList;
import core.Movie;
import filehandler.MovieListHandler;
/**
 * Class for handeling server calls.
 */
@Service
public class MovieListService {

  public MovieList movieList = new MovieList();
  private String username;
  public MovieListHandler movieListHandler = new MovieListHandler("remote.json");
  Gson gson = new GsonBuilder().setPrettyPrinting().create();


  /**
   * Constructor for service class
   */
  public MovieListService() { //Should we implement exception handling?
      movieListHandler.getMovieList(username);
  }

  /**
   * Getter for MovieList
   * 
   * @return movieList
   */
  public MovieList getMovieList() {
    return movieList;
  }

  /**
   * Method for getting movielist based on username
   * 
   * @param username of user
   * @return movielist if user exists, else null
   */
  public MovieList getMovieListByUsername(String username) {
    MovieList list = movieListHandler.getMovieList(username);
    if (list.getNumberOfMovies() != 0) {
      return list;
    } else {return null;}
  }

  /**
   * Method for adding a movie to the MovieList
   * 
   * @param Movie to be added
   * @param username list is added to
   * 
   */
  public void addMovietoMovielist(Movie movie, String username) {
    // TODO
  }

  /**
   * Method for saving and closing a movielist
   * 
   * @param username to save movielist to
   */
  public void saveAndCloseMovieList(String username) {
    // TODO
  }
  



  
  
}
