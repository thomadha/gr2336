package dataaccess;

import core.Movie;
import core.MovieList;
import filehandler.MovieListHandler;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Test class for the local movieaccess.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieListLocalAccessTest {
  
  private MovieListHandler filehandler;
  private MovieList movielist;

  /**
   * Method that runs before each test.
   */
  @BeforeEach
  public void setUp() {
    filehandler = new MovieListHandler(
      "/src/test/java/resources/MovieListTest.json");
  
    movielist = new MovieList();
    movielist.setUsername("newuser");
    movielist.setPassword("newpassword");

    Movie shark = new Movie("Shark 2", 2, "");
    Movie meangirls = new Movie("Mean Girls", 9.5, "");
    Movie harry = new Movie("Harry Potter 1", 9, "", 5);
    movielist.addMovie(shark);
    movielist.addMovie(meangirls);
    movielist.addMovie(harry);

    filehandler.saveToFile(movielist);

  }

  /**
   * Method that runs after each test.
   */
  @AfterEach
  public void cleanUp() {
    filehandler.removeMovieList(movielist);
  }

  @Test
  @DisplayName("Checks that get movielists by username work")
  public void checkGetMovieListByUsername() {

    MovieList newMovieList = filehandler.getMovieList("newuser");

    Assertions.assertNotNull(newMovieList);
    Assertions.assertEquals(this.movielist.movieListToString(), newMovieList.movieListToString());
    Assertions.assertEquals("newuser", newMovieList.getUsername());
    Assertions.assertEquals("newpassword", newMovieList.getPassword());
}


  @Test
  @DisplayName("Checks adding movies to a user works.")
  public void checkAddMovieToListWorks() {
    MovieList movieList = filehandler.getMovieList("newuser");
    Movie cool = new Movie("a cool movie", 10.0, "horror");

    movieList.addMovie(cool);
    filehandler.saveToFile(movieList);

    MovieList newMovielist = filehandler.getMovieList("newuser");

    Assertions.assertEquals("a cool movie \n - Genre: horror - Score: 10.0 - Views: 1", newMovielist
        .getMovies().get(0).toString());

  }
  
  @Test
  @DisplayName("Valid username returns movielist, getMovieList")
  public void checkGetMovieList() {

    MovieList movieList = filehandler.getMovieList("testuser");

    Assertions.assertNotNull(movieList);
    Assertions.assertEquals("testuser", movieList.getUsername());
  }

  @Test
  @DisplayName("Check adding movielists work")
  public void checkAddMovieList() {
    MovieList newMovieList = new MovieList();
    Movie shark3 = new Movie("Shark 3", 1.0, "action");
    Movie meangirls2 = new Movie("Mean Girls 2", 8.0, "action");
    newMovieList.addMovie(shark3);
    newMovieList.addMovie(meangirls2);

    newMovieList.setUsername("addUser");
    newMovieList.setPassword("123");

    filehandler.saveToFile(newMovieList);

    MovieList testAddMovieList = filehandler.getMovieList("addUser");
    filehandler.removeMovieList(testAddMovieList);
    Assertions.assertEquals(newMovieList.movieListToString(), testAddMovieList.movieListToString());
  }

  @Test
  @DisplayName("checks that validateNoExistingMovieList works")
  public void checkValidateNoExistingMovieList() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      filehandler.validateNoExistingMovieList("testuser"); }, "list already exists");
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
  @DisplayName("Checks that getAllMovieListsFromFile works")
  public void checkGetAllMovieListsFromFile() {

    List<MovieList> movieLists = filehandler.getAllMovieListsFromFile();

    Assertions.assertNotNull(movieLists);
    Assertions.assertEquals("testuser", movieLists.get(0).getUsername());
    Assertions.assertEquals("coolTestUser", movieLists.get(1).getUsername());

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
