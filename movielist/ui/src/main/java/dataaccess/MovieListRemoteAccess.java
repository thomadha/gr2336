package dataaccess;

import java.io.FileNotFoundException;

import core.Movie;
import core.MovieList;

public class MovieListRemoteAccess implements MovieListAccess{

  @Override
  public Movie getMovie() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getMovie'");
  }

  @Override
  public MovieList getMovieList(String username) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getMovieList'");
  }

  @Override
  public void postMovieList(MovieList list) throws FileNotFoundException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'postMovieList'");
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
