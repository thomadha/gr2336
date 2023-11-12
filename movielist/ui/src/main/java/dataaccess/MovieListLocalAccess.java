package dataaccess;

import java.util.List;


import core.MovieList;
import filehandler.MovieListHandler;

public class MovieListLocalAccess implements MovieListAccess{

  final MovieList movieList;
  private MovieListHandler fileHandler;

  public MovieListLocalAccess(MovieList movielist) {
    movieList = new MovieList();
    fileHandler = new MovieListHandler("/src/main/java/filehandler/MovieList.json");
}

  @Override
  public MovieList getMovieList() {
    return movieList;
  }

  @Override
  public MovieList getMovieListByUsername(String username) {
    return fileHandler.getMovieList(username);
  }

  // /**
  //  * Gets the names of the MovieLists.
  //  *
  //  * @return the names of the MovieLists.
  //  */
  // @Override
  // public Collection<String> getMovieListNames() {
  //   Collection<String> allNames = new ArrayList<>();
  //   fileHandler.getAllMovieListsFromFile().forEach(movieList -> allNames.add(movieList.getUsername()));
  //   return allNames;
  // }


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

  /**
   * Updates the user file with the specified user.
   *
   * @param user the user to update
   */

  public void saveToFile(MovieList movielist) {
    fileHandler.saveToFile(movielist);
  }

  // @Override
  // public void validateNoExistingMovieList(String username) {
  //   fileHandler.validateNoExistingMovieList(username);
  // }

  // @Override
  // public void addMovie(Movie movie) {
  //   movieList.addMovie(movie);
  // }

  // @Override
  // public List<Movie> getMovies() {
  //   return movieList.getMovies();
  // }


  // @Override
  // public boolean checkDuplicate(Movie newMovie) {
  //   return movieList.checkDuplicate(newMovie);
  // }

  // @Override
  // public void setUsername(String username) {
  //   movieList.setUsername(username);
  // }

  // @Override
  // public void setPassword(String password) {
  //   movieList.setPassword(password);
  // }

  // @Override
  // public void setMovies(List<Movie> movies) {
  //   movieList.setMovies(movies);
  // }

}