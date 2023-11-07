package dataaccess;

import java.io.FileNotFoundException;
import java.util.List;

import core.Movie;
import core.MovieList;
import filehandler.MovieListHandler;

public class MovieListDirectAccess implements MovieListAccess {

  static MovieList movieList = new MovieList();
  private MovieListHandler movieListHandler = new MovieListHandler("/src/main/java/json/MovieList.json");

  @Override
  public List<Movie> getMovies() {
    return  movieList.getMovies();
  }

  @Override
  public MovieList getMovieList(String username) {
    return  movieListHandler.getMovieList(username);
  }

  @Override
  public void postMovieList(MovieList list) throws FileNotFoundException {
    movieListHandler.saveToFile(list);
  }

  @Override
  public void removeMovieList(String username) throws FileNotFoundException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'removeMovieList'");
  }

  @Override
  public Movie getItem(String movieName, String movieListName) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getItem'");
  }

  @Override
  public void setPickedState(String movieName, String movieListName, Boolean isPicked) throws FileNotFoundException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setPickedState'");
  }

  @Override
  public void postMovie(Movie movie, String movieListName) throws FileNotFoundException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'postMovie'");
  }

  @Override
  public void deleteMovie(String movieName, String movieListName) throws FileNotFoundException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteMovie'");
  }
  
}
