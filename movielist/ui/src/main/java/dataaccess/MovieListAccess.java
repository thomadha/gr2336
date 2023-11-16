package dataaccess;

import core.Movie;
import core.MovieList;
import java.util.List;

/**
 * Interface for the remote and local access classes.
 */
public interface MovieListAccess {

  public MovieList getMovieListByUsername(String username);

  public void addMovieList(MovieList movieList);

  public void removeMovieList(String username);

  public List<MovieList> getAllMovieListsFromFile();

  public void addMovieToList(Movie movie);

  public void updateMovieList(MovieList movieList);


}