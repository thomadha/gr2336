package dataaccess;

import core.Movie;
import core.MovieList;
import filehandler.MovieListHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
  private MovieListLocalAccess localAccess;

  /**
   * Sets up user credentials and creates a MovieList with predefined movies before all tests.
   *
   * @throws FileNotFoundException if there is an issue with loading or saving movie list data.
   */
  @BeforeAll 
  public void preSetUp() throws FileNotFoundException{
    filehandler = new MovieListHandler("/src/main/java/json/MovieList.json");
    
    movielist = new MovieList();
    movielist.setUsername("TestUser");
    movielist.setPassword("password");
    Movie m1 = new Movie("Star Wars", 8.0, "scifi");
    Movie m2 = new Movie("Harry Potter", 6.0, "fantasy");
    Movie m3 = new Movie("ThomasToget", 2.0, "other");
    movielist.addMovie(m1);
    movielist.addMovie(m2);
    movielist.addMovie(m3);
    filehandler.saveToFile(movielist);
  }

  @BeforeEach
  public void setUp() {
    localAccess = new MovieListLocalAccess(movielist);
  }

  @AfterAll
  public void cleaunUp() throws FileNotFoundException {
    filehandler.removeMovieList(movielist);
  }

  @Test
  @DisplayName("Checks that get movielists by username work")
  public void checkGetMovieListByUsername() {

    MovieList movieList = localAccess.getMovieListByUsername("TestUser");
    MovieList newMovieList = filehandler.getMovieList("TestUser");

    Assertions.assertNotNull(movieList);
    Assertions.assertEquals(newMovieList.movieListToString(), movieList.movieListToString());
    Assertions.assertEquals("TestUser", movieList.getUsername());
    Assertions.assertEquals("password", movieList.getPassword());
  }


  @Test
  @DisplayName("Checks adding movies to a user works.")
  public void checkAddMovieToListWorks() {

    this.movielist = localAccess.getMovieListByUsername("TestUser");
    Movie cool = new Movie("a cool movie", 10.0, "horror");

    localAccess.updateMovieList(movielist);
    localAccess.addMovieToList(cool);

    MovieList newMovielist = filehandler.getMovieList("TestUser");

    Assertions.assertEquals("a cool movie \n - Genre: horror - Score: 10.0 - Views: 1", newMovielist
        .getMovies().get(0).toString());

  }

    
  // @Test
  // @DisplayName("Checks deleting a movielist")
  // public void checkRemoveMovieList() {
  //   filehandler.removeMovieList(movielist);
  //   List<MovieList> allMovieLists = new ArrayList<>(filehandler.getAllMovieListsFromFile());

  //   Assertions.assertEquals(2, allMovieLists.size());
  //   Assertions.assertEquals(allMovieLists.get(0).movieListToString(), filehandler
  //         .getMovieList("testuser").movieListToString());
  //   Assertions.assertEquals(allMovieLists.get(1).movieListToString(), filehandler
  //         .getMovieList("coolTestUser").movieListToString());
  // }

  
//   @Test
//   @DisplayName("Valid username returns movielist, getMovieList")
//   public void checkGetMovieList() {

//     MovieList movieList = filehandler.getMovieList("testuser");

//     Assertions.assertNotNull(movieList);
//     Assertions.assertEquals("testuser", movieList.getUsername());
//   }

//   @Test
//   @DisplayName("Check adding movielists work")
//   public void checkAddMovieList() {
//     MovieList newMovieList = new MovieList();
//     Movie shark3 = new Movie("Shark 3", 1.0, "action");
//     Movie meangirls2 = new Movie("Mean Girls 2", 8.0, "action");
//     newMovieList.addMovie(shark3);
//     newMovieList.addMovie(meangirls2);

//     newMovieList.setUsername("addUser");
//     newMovieList.setPassword("123");

//     filehandler.saveToFile(newMovieList);

//     MovieList testAddMovieList = filehandler.getMovieList("addUser");
//     filehandler.removeMovieList(testAddMovieList);
//     Assertions.assertEquals(newMovieList.movieListToString(), testAddMovieList.movieListToString());
//   }

//   @Test
//   @DisplayName("checks that validateNoExistingMovieList works")
//   public void checkValidateNoExistingMovieList() {
//     Assertions.assertThrows(IllegalArgumentException.class, () -> {
//       filehandler.validateNoExistingMovieList("testuser"); }, "list already exists");
//   }

//   @Test
//   @DisplayName("Checks that saving existing movielist to file works")
//   public void checkSaveToExistingFile() {

//     filehandler.saveToFile(movielist);

//     Movie dog = new Movie("Dog 2", 3.0, "romcom");
//     movielist.addMovie(dog);
//     filehandler.saveToFile(movielist);

//     String user = movielist.getUsername();

//     Assertions.assertEquals(movielist.getMovies().toString(), filehandler
//         .getMovieList(user).getMovies().toString());

//   }

//   @Test
//   @DisplayName("Checks that getAllMovieListsFromFile works")
//   public void checkGetAllMovieListsFromFile() {

//     List<MovieList> movieLists = filehandler.getAllMovieListsFromFile();

//     Assertions.assertNotNull(movieLists);
//     Assertions.assertEquals("testuser", movieLists.get(0).getUsername());
//     Assertions.assertEquals("coolTestUser", movieLists.get(1).getUsername());

//   }


}
