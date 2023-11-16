package dataaccess;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.gson.Gson;
import core.Movie;
import core.MovieList;
import filehandler.MovieListHandler;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Testclass for MovieListRemoteAccess.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieListRemoteAccessTest {
  
  private static final int Port = 8080; 
  private static final String Host = "localhost";
  private static WireMockServer server; 
  private MovieListRemoteAccess remoteAccess; 
  private MovieList testmovieList;

  private MovieListHandler movielisthandler = new MovieListHandler(
      "/src/test/java/resources/MovieListTest.json"); 
  private Gson gson = new Gson();
  
  /**
 * Sets up user credentials and creates a MovieList with predefined movies before all tests.
 *
 * @throws FileNotFoundException if there is an issue with loading or saving movie list data.
 */
  @BeforeAll 
  public void preSetUp() throws FileNotFoundException{
    testmovieList = new MovieList();
    testmovieList.setUsername("TestUser");
    testmovieList.setPassword("password");
    Movie m1 = new Movie("Star Wars", 8.0, "scifi");
    Movie m2 = new Movie("Harry Potter", 6.0, "fantasy");
    Movie m3 = new Movie("ThomasToget", 2.0, "other");
    testmovieList.addMovie(m1);
    testmovieList.addMovie(m2);
    testmovieList.addMovie(m3);
    movielisthandler.saveToFile(testmovieList);
  }

  /**
 * Sets up the test environment before each test by starting a WireMock server and configuring
 * a MovieListRemoteAccess instance for remote access.
 *
* @throws Exception if an error occurs during WireMock server 
          creation or MovieListRemoteAccess instantiation.
 */
  @BeforeEach
  public void setUp() {
    server = new WireMockServer(options().port(Port)); 
    server.start(); 
    WireMock.configureFor(Host, Port);
    try {
      remoteAccess = new MovieListRemoteAccess(new URI("http://localhost:" + server.port()), true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @Test
  public void testGetMovieLists() throws FileNotFoundException{
    List<MovieList> testMovieLists = movielisthandler.getAllMovieListsFromFile();
    WireMock.stubFor(get("/getall")
            .willReturn(new ResponseDefinitionBuilder()
            .withBody(gson.toJson(testMovieLists))));
  }

  @Test
  public void testGetMovieListByUsername() throws FileNotFoundException {
    MovieList testList = movielisthandler.getMovieList("TestUser");
    WireMock.stubFor(get("/movielist/TestUser")
        .willReturn(new ResponseDefinitionBuilder()
        .withBody(gson.toJson(testList))));
    MovieList movieList = remoteAccess.getMovieListByUsername("TestUser");
    assertEquals(gson.toJson(testList), gson.toJson(movieList));
  }

  @Test
    public void testGetMovieList() throws Exception {
    MovieList testList = remoteAccess.getMovieList();
    assertEquals(0, testList.getMovies().size()); 
    // hehe idk helt hvordan man skal skrive denne
    //husk at jeg adder 3 filmer på starten av hver runde
    //så mengden filmer skal være 3. 
    }



  @Test
  public void testAddMovie() throws FileNotFoundException {
    Movie m4 = new Movie("Teletubbies", 10.0, "other"); 
    testmovieList.addMovie(m4);
    remoteAccess.addMovieToList(m4);
    WireMock.stubFor(get("/movielist/TestUser/getMovie?movieName=Teletubbies")
                      .willReturn(new ResponseDefinitionBuilder()
                      .withBody(gson.toJson(m4))));
    MovieList movielist = remoteAccess.getMovieListByUsername("TestUser"); 

    assertThrows(RuntimeException.class, () -> {
      remoteAccess.addMovieToList(null);
    }); 
    //assertEquals(4, movielist.getNumberOfMovies()); 

    //aner ikke om jeg gjør det riktig, men dette gir litt mer mening i mitt hode hvertfall. 
    
    // MovieList testMovieList = movielisthandler.getMovieList("TestUser");
    // List<Movie> testMovies = testMovieList.getMovies();
    // Movie testMovie = testMovies.get(0);
    // WireMock.stubFor(get("/movielist/TestUser/getMovie?movieName=Jaws")
    //                 .willReturn(new ResponseDefinitionBuilder()
    //                 .withBody(gson.toJson(testMovie))));
    // MovieList movieList = remoteAccess.getMovieListByUsername("TestUser");
  }

  @Test
  public void testRemoveMovieList() {
    String username = testmovieList.getUsername();
    stubFor(delete("/movielist/" + username + "/deleteUser")
          .willReturn(aResponse().withStatus(200)));
    remoteAccess.removeMovieList(username);

    stubFor(delete("/movielist/")
          .willReturn(aResponse().withStatus(405)));
    assertThrows(RuntimeException.class, () -> {
      remoteAccess.removeMovieList(username);
    });
  }

  @AfterEach
  public void tearDown() {
    server.stop();
  }

  @AfterAll
  public void rigDown() throws FileNotFoundException {
    movielisthandler.removeMovieList(testmovieList);
  }
}