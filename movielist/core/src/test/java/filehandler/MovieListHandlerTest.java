package filehandler;

import core.Movie;
import core.MovieList;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



/**
 * Class for the moviehhandlertest.
 */
public class MovieListHandlerTest {

  private MovieListHandler filehandler;
  private MovieList movielist;

  /**
   * Method before each test.
   */
  @BeforeEach
  public void setUp() {
    filehandler = new MovieListHandler(
      "/src/test/java/ui/resources/MovieList.json");
  
    movielist = new MovieList();
    movielist.setUsername("newuser");
    movielist.setPassword("newpassword");

    Movie shark = new Movie("Shark 2", 2, "");
    Movie meangirls = new Movie("Mean Girls", 10, "");
    Movie harry = new Movie("Harry Potter 1", 9, "", 5);
    movielist.addMovie(shark);
    movielist.addMovie(meangirls);
    movielist.addMovie(harry);
  }

  @AfterEach
  public void cleanUp() {
    filehandler.removeMovieList(movielist);
  }

  @Test
  @DisplayName("Checks that getAllMovieListsFromFile works")
  public void checkGetAllMovieListsFromFile() {

    List<MovieList> movieLists = filehandler.getAllMovieListsFromFile();

    Assertions.assertNotNull(movieLists);
    Assertions.assertEquals("testuser", movieLists.get(0).getUsername());
    Assertions.assertEquals("coolTestUser", movieLists.get(1).getUsername());

  }

  @Test
  @DisplayName("Valid username returns movielist, getMovieList")
  public void checkGetMovieList() {

    MovieList movieList = filehandler.getMovieList("testuser");

    Assertions.assertNotNull(movieList);
    Assertions.assertEquals("testuser", movieList.getUsername());
  }

  // @Test
  // @DisplayName("checks that getmovielist throws illegal state")
  // public void checkGetMovieListWhenEmptyState() {
  //   testFileHandler = new MovieListHandler("/src/test/java/ui/resources/EmptyMovieList.json");

  //   Assertions.assertThrows(IllegalStateException.class, () -> {
  //     testFileHandler.getMovieList(""); }, "no lists in file");
  // }

  @Test
  @DisplayName("checks that getmovielist throws illegal argument")
  public void checkGetMovieListWhenEmptySrgument() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      filehandler.getMovieList("hmm"); }, "no user in movielist");
  }

  @Test
  @DisplayName("checks that validateNoExistingMovieList works")
  public void checkValidateNoExistingMovieList() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      filehandler.validateNoExistingMovieList("testuser"); }, "list already exists");
  }

  @Test
  @DisplayName("Checks that saving new movielist to file works")
  public void checkSaveToNewFile() {

    filehandler.saveToFile(movielist);
    String user = movielist.getUsername();

    Assertions.assertEquals(movielist.getMovies().toString(), filehandler
          .getMovieList(user).getMovies().toString());

  }

  @Test
  @DisplayName("Checks that saving existing movielist to file works")
  public void checkSaveToExistingFile() {

    filehandler.saveToFile(movielist);

    Movie dog = new Movie("Dog 2", 3.0, "romcom");
    movielist.addMovie(dog);
    filehandler.saveToFile(movielist);

    String user = movielist.getUsername();

    Assertions.assertEquals(movielist.getMovies().toString(), filehandler
        .getMovieList(user).getMovies().toString());

  }

  @Test
  @DisplayName("Checks deleting a movielist")
  public void checkRemoveMovieList() {
    filehandler.removeMovieList(movielist);
    List<MovieList> allMovieLists = new ArrayList<>(filehandler.getAllMovieListsFromFile());

    Assertions.assertEquals(2, allMovieLists.size());
    Assertions.assertEquals(allMovieLists.get(0).movieListToString(), filehandler
          .getMovieList("testuser").movieListToString());
    Assertions.assertEquals(allMovieLists.get(1).movieListToString(), filehandler
          .getMovieList("coolTestUser").movieListToString());
  }

}
