package movielist.springboot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    movieListHandler.saveToFile(movieList);
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
        break;
      }
      i++;
    }
  }

  public void addMovieToList(String username, Movie movie){
    MovieList movieList = getMovieListByUsername(username); 
    movieList.addMovie(movie);
    System.out.println(movie);
    //movieListHandler.saveToFile(movieList);
    addMovieList(movieList);
  }

  public Movie getMovie(String username, String movieTitle){
    MovieList movieList = getMovieListByUsername(username); 
    List<Movie> movies = movieList.getMovies(); 
    for (Movie m : movies) {
      if(m.getName().equals(movieTitle)){
        return m;
      }
    }
    return null;
  }

  public int getNumberOfMovies(String username){
    MovieList movieList = getMovieListByUsername(username);
    List<Movie> movies = movieList.getMovies(); 
    return movies.size();
  }

  public String getPassword(String username){
    MovieList movieList = getMovieListByUsername(username);
    String password = movieList.getPassword();
    return password;
  }

  public MovieList newMovieList(String username, String password){
    MovieList newMovieList = new MovieList();
    newMovieList.setUsername(username);
    newMovieList.setPassword(password);
    addMovieList(newMovieList);
    return newMovieList;
  }
}
