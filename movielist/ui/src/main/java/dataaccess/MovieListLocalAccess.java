package dataaccess;

import core.Movie;
import core.MovieList;
import filehandler.MovieListHandler;
import java.util.List;

/**
 * Class for local movielist access.
 */
public class MovieListLocalAccess implements MovieListAccess {

  private MovieList movieList;
  private MovieListHandler fileHandler;

  public MovieListLocalAccess(MovieList movielist) {
    movieList = new MovieList();
    fileHandler = new MovieListHandler("/src/main/java/json/MovieList.json");
  }

  @Override
  public MovieList getMovieList() {
    return movieList;
  }

  @Override
  public MovieList getMovieListByUsername(String username) {
    return fileHandler.getMovieList(username);
  }

  public void updateMovieList(MovieList newMovieList) {
    this.movieList = newMovieList;
  }

  @Override
  public List<MovieList> getAllMovieListsFromFile() {
    return fileHandler.getAllMovieListsFromFile();
  }

  @Override
  public void addMovieList(MovieList movieList) {
    fileHandler.saveToFile(movieList);
  }

  @Override
  public void removeMovieList(String username) {
    fileHandler.removeMovieList(fileHandler.getMovieList(username));
  }

  @Override
  public void addMovieToList(Movie movie) {
    movieList.addMovie(movie);
    fileHandler.saveToFile(movieList);
  }
}