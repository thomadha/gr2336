package filehandler;

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

  /**
   * field for filepath.
   */
  private final String filepath;
  /**
   * field for length to remove if the userdir ends with core.
   */

    /**
     * Constructor for MovieListHandler.
     * Initiates a movielisthandler with correct filepath in ui.
     *
     * @param filepath of movielisthandler.
     */
    public MovieListHandler(final String filepath) {
      String userdir = System.getProperty("user.dir");
      if (userdir.endsWith("GR2336")) {
        userdir = userdir + "/movielist/ui";
      }
      if (userdir.endsWith("core")) {
        userdir = userdir.substring(0, userdir.length() - 5);
        userdir = userdir + "/ui";
      }
      if (userdir.endsWith("springboot")) {
        userdir = userdir.substring(0, userdir.length()-11);
        userdir = userdir + "/ui";
      }
      this.filepath = userdir + filepath;
    }

    /**
   * Read all movieLists form file.
   *
   * @return a list of movieLists.
   */
  public List<MovieList> getAllMovieListsFromFile() {
    List<MovieList> movieListList = new ArrayList<>();
    try (FileReader reader = new FileReader(filepath, StandardCharsets.UTF_8)) {
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
   * Gets a movielist with the specified username and matching password.
   *
   * @param username the username of the movielist to get.
   * @return a movielist with the specifed username and correct password.
   * @throws IllegalArgumentException if the list does not exist/wrong password.
   */
  public MovieList getMovieList(final String username) {
    List<MovieList> movieLists = getAllMovieListsFromFile();
    if (movieLists == null) {
      throw new IllegalStateException("Error loading movie lists from file");
    }
    return movieLists.stream()
      .filter(a -> a.getUsername().equals(username))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("List doesn't exist"));
  }

  /**
   * Validates that a movielist with the specified username doesn't exist.
   *
   * @param username the username to validate.
   * @throws IllegalArgumentException if movieList with username already exists.
   */
  public void validateNoExistingMovieList(final String username) {
    if (getAllMovieListsFromFile().stream()
      .anyMatch(a -> a.getUsername().equals(username))) {
      throw new IllegalArgumentException("MovieList already exists");
    }
  }

   /**
   * Updates the user file with the specified user.
   *
   * @param movielist to update.
   */
  public void saveToFile(MovieList movielist) {
    List<MovieList> movieLists = getAllMovieListsFromFile();
    boolean found = false;

    for (MovieList existingMovieList : movieLists) {
        if (existingMovieList.getUsername().equals(movielist.getUsername())) {
            // Update the existing MovieList with the new movies
            existingMovieList.setMovies(movielist.getMovies());
            found = true;
            break;
        }
    }

    // If the MovieList was not found, add it to the list.
    if (!found) {
        movieLists.add(movielist);
    }
    System.out.println("ALL MOVIES: " + movieLists);

    // Save the entire list to the file.
    try (FileWriter writer = new FileWriter(filepath, StandardCharsets.UTF_8)) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(movieLists, writer);
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  /**
   * Removes a movielist from saved files.
   *
   * @param movielist to be removed.
   */
  public void removeMovieList(MovieList movielist) {
    List<MovieList> movieLists = getAllMovieListsFromFile();
    boolean found = false;
    MovieList movieListToRemove = null;

    for (MovieList exsistingMovieList : movieLists) {
        if (exsistingMovieList.getUsername().equals(movielist.getUsername())) {
          found = true;
          break;
        }
    }

    if (found) {
      for (MovieList exsistingMovieList : movieLists) {
        if (exsistingMovieList.getUsername().equals(movielist.getUsername())) {
          movieListToRemove = exsistingMovieList;
        }
    }
    movieLists.remove(movieListToRemove);
  }

    try (FileWriter writer = new FileWriter(filepath, StandardCharsets.UTF_8)) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(movieLists, writer);
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
}
