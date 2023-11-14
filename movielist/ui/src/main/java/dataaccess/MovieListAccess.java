package dataaccess;

import java.util.List;

import core.Movie;
import core.MovieList;

public interface MovieListAccess {

  public MovieList getMovieList();

  public MovieList getMovieListByUsername(String username);

  public void addMovieList(MovieList movieList);

  public void removeMovieList(String username);

  public List<MovieList> getAllMovieListsFromFile();

  public void addMovieToList(Movie movie);

  public void updateMovieList(MovieList movieList);


}