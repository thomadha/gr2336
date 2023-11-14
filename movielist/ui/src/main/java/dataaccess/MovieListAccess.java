package dataaccess;

import java.util.List;

import core.Movie;
import core.MovieList;

public interface MovieListAccess {

  public MovieList getMovieList();

  public MovieList getMovieListByUsername(String username);

  public void addMovieList(MovieList movieList);

  public void removeMovieList(String username);

  //public Collection<String> getMovieListNames();

  public List<MovieList> getAllMovieListsFromFile();

  public void saveToFile(MovieList movieList);

  public void addMovieToList(Movie movie);

  public void updateMovieList(MovieList movieList);

  //public void validateNoExistingMovieList(String username);


  // public void addMovie(Movie movie);

  // public List<Movie> getMovies();

  // public boolean checkDuplicate(Movie newMovie);

  // public void setUsername(String username);

  // public void setPassword(String password);

  // public void setMovies(List<Movie> movies);


}