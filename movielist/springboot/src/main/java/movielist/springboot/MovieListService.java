package movielist.springboot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import core.MovieList;
import core.Movie;
import filehandler.MovieListHandler;
/**
 * Class for handeling server calls.
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MovieListService {

  public MovieList movieList;
  public List<MovieList> movieLists;
  public MovieListHandler movieListHandler; 
  Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public MovieListService(){
    movieList = new MovieList();
    movieLists = new ArrayList<MovieList>();
    movieListHandler = new MovieListHandler("/src/main/java/json/MovieList.json"); 
    //movieListHandler.saveToFile(movieList);
  }

  public String getUsername(){
    return movieList.getUsername();
  }

  public String getPassword(){
    return movieList.getPassword();
  }

  public List<Movie> getMovies(){
    return movieList.getMovies();
  }

  public int getNumberOfMovies(){
    return movieList.getNumberOfMovies();
  }

  public List<MovieList> getMovieLists(){
    return movieListHandler.getAllMovieListsFromFile(); 
  }

  public MovieList getMovieList(){
    return movieList;
  }

  public MovieList getMovieListByUsername(String username){
    movieLists = movieListHandler.getAllMovieListsFromFile();
    for(MovieList movieList : movieLists){
      if(movieList.getUsername().equals(username)){
        return movieList;
      }
    }
    return null;
  }

  public void addMovieList(MovieList movieList){
    saveChanges();
  }

  public void deleteMovieList(String username)throws IOException{
    if(username == null || username.equals("")){
      throw new IOException("Name is null");
    }
    movieLists = movieListHandler.getAllMovieListsFromFile();
    int i = 0;
    for(MovieList movieList : movieLists){
      if(movieList.getUsername().equals(username)){
        movieLists.remove(i);
        movieListHandler.removeMovieList(movieList);
        saveChanges();
        break;
      }
      i++;
    }
  }



  private void saveChanges(){
    movieListHandler.saveToFile(movieList);
  }


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
