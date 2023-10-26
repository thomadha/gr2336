package json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import core.MovieList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MovieListHandler {

    private final String filepath;

    public MovieListHandler(String filepath){
      String userdir = System.getProperty("user.dir");
      if(userdir.endsWith("GR2336")){
        userdir = userdir + "/movielist/ui";
      }
      if(userdir.endsWith("core")){
        userdir = userdir.substring(0, userdir.length()-5);
        userdir = userdir + "/ui";
      }
      this.filepath = userdir + filepath; 
    } 

    /**
   * Read all movieLists form file
   *
   * @return a list of movieLists 
   */
  public List<MovieList> getAllMovieListsFromFile() {
    List<MovieList> movieListList = new ArrayList<>();
    try (FileReader reader = new FileReader(filepath, StandardCharsets.UTF_8)) { // Specify UTF-8
      Gson gson = new Gson();
      MovieList[] movieListArray = gson.fromJson(reader, MovieList[].class);
      if (movieListArray != null) {
        for (MovieList movieList : movieListArray) {
          movieListList.add(movieList);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return movieListList;
  }

      /**
   * Gets a movielist with the specified username and matching password
   *
   * @param username the username of the movielist to get
   * * @param password the password of the specific movielist
   * @return a movielist with the specifed username and correct password
   * @throws IllegalArgumentException if the movielist does not exist or password is incorrect
   */
  public MovieList getMovieList(String username, String password) {
    List<MovieList> movieLists = getAllMovieListsFromFile();
    if (movieLists == null) {
      throw new IllegalStateException("Error loading movie lists from file");
    }
    return movieLists.stream()
        .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Movielist doesn't exist or password is incorrect"));
  }

  /**
   * Validates that a movieList with the specified username does not already exist.
   *
   * @param username the username to validate
   * @throws IllegalArgumentException if a movieList with the specified username already exists
   */
  public void validateNoExistingMovieList(String username) {
    if (getAllMovieListsFromFile().stream().anyMatch(a -> a.getUsername().equals(username))) {
      throw new IllegalArgumentException("MovieList already exists");
    }
  }

   /**
   * Updates the user file with the specified user.
   *
   * @param user the user to update
   */
  public void saveToFile(MovieList movielist) {
    List<MovieList> movieLists = getAllMovieListsFromFile();
    if(movieLists.stream().anyMatch(a->a.getUsername().equals(movielist.getUsername()))){

      MovieList movieListToUpdate =
      movieLists.stream().filter(a -> a.getUsername().equals(movielist.getUsername())).findAny().get();
      movieListToUpdate.setMovies(movielist.getMovies()); 
    }
    else{
      movieLists.add(movielist);
    }
    try (FileWriter writer = new FileWriter(filepath, StandardCharsets.UTF_8)) { // Specify UTF-8
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      gson.toJson(movieLists, writer); // Serialize and write the updated user list
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
}
