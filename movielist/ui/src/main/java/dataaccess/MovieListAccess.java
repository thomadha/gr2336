package dataaccess;

import java.io.FileNotFoundException;
import java.util.List;

import core.Movie;
import core.MovieList;

public interface MovieListAccess {

  public List<Movie> getMovies();

  public MovieList getMovieList(String username);

  public void postMovieList(MovieList list) throws FileNotFoundException;

  public void removeMovieList(String username) throws FileNotFoundException;

  public Movie getItem(String movieName, String movieListName);

  public void setPickedState(String movieName, String 
      movieListName, Boolean isPicked) throws FileNotFoundException;

  public void postMovie(Movie movie, String movieListName) throws FileNotFoundException;

  public void deleteMovie(String movieName, String movieListName) throws FileNotFoundException;

  
  
}
