package movielist.springboot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import core.MovieList;
import core.Movie;
import filehandler.MovieListHandler;
/**
 * Class for handeling server calls.
 */
@Service
public class MovieListService {


  // public MovieList movieList = new MovieList();
  // private String username;
  // public MovieListHandler movieListHandler = new MovieListHandler("/src/main/java/filehandler/MovieList.json");
  // Gson gson = new GsonBuilder().setPrettyPrinting().create();


  // /**
  //  * Constructor for service class
  //  */
  // public MovieListService() { //Should we implement exception handling?
  //     movieListHandler.getMovieList(username);
  // }

  // @Autowired
  //   public MovieListService(MovieListHandler movieListHandler) {
  //       this.movieListHandler = movieListHandler;
  //   }
  // /**
  //  * Getter for MovieList
  //  * 
  //  * @return movieList
  //  */
  // public MovieList getMovieList() {
  //   return movieList;
  // }

  // /**
  //  * Method for getting movielist based on username
  //  * 
  //  * @param username of user
  //  * @return movielist if user exists, else null
  //  */
  // public MovieList getMovieListByUsername(String username) {
  //   MovieList list = movieListHandler.getMovieList(username);
  //   if (list.getNumberOfMovies() != 0) {
  //     return list;
  //   } else {return null;}
  // }

  // /**
  //  * Method for adding a movie to the MovieList
  //  * 
  //  * @param Movie to be added
  //  * @param username list is added to
  //  * 
  //  */
  // public void addMovietoMovielist(Movie movie, String username) {
  //     MovieList movieList = movieListHandler.getMovieList(username);
  //     movieList.addMovie(movie);
  //     movieListHandler.saveToFile(movieList);
  // }

  // /**
  //  * Method for saving and closing a movielist
  //  * 
  //  * @param username to save movielist to
  //  */
  // public void saveAndCloseMovieList(String username) {
  //     movieListHandler.saveToFile(movieList);
  // }

  // public MovieList getMovieList(String username){
  //   return movieListHandler.getMovieList(username); 
  // }
  
}
