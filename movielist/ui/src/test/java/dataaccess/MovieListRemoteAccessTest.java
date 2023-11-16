package dataaccess;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.gson.Gson;
import core.Movie;
import core.MovieList;
import filehandler.MovieListHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    "/src/main/java/json/MovieList.json"); 
  private Gson gson = new Gson();
  
  /**
 * Sets up user credentials and creates a MovieList with predefined movies before all tests.
 *
 * @throws FileNotFoundException if there is an issue with loading or saving movie list data.
 */
  @BeforeAll 
  public void preSetUp() throws FileNotFoundException {
    testmovieList = new MovieList();
    testmovieList.setUsername("TestUser");
    testmovieList.setPassword("password");
    Movie m1 = new Movie("Star Wars", 8.0, "Scifi", 1);
    Movie m2 = new Movie("Harry Potter", 6.0, "Fantasy", 1);
    Movie m3 = new Movie("ThomasToget", 2.0, "Other", 1);
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
  public void testGetAllMovieListsFromFile() throws FileNotFoundException{
    List<MovieList> testMovieLists = movielisthandler.getAllMovieListsFromFile();
    WireMock.stubFor(get("/movielist/getall")
            .willReturn(new ResponseDefinitionBuilder()
            .withBody(gson.toJson(testMovieLists))));
    List<MovieList> actualMovieLists = remoteAccess.getAllMovieListsFromFile();
    assertNotNull(actualMovieLists);
    assertEquals(testMovieLists.size(), actualMovieLists.size());
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
public void testAddMovieList() throws IOException, InterruptedException {
    MovieList movieListToAdd = new MovieList();
    movieListToAdd.setUsername("TestUser");
    movieListToAdd.setPassword("password");
    movieListToAdd.addMovie(new Movie("Inception", 9.0, "Scifi", 1));

    WireMock.stubFor(post("/movielist/TestUser/password/newUser")
            .willReturn(new ResponseDefinitionBuilder()
                    .withStatus(200)));

    remoteAccess.addMovieList(movieListToAdd);

    WireMock.verify(postRequestedFor(urlEqualTo("/movielist/TestUser/password/newUser"))
            .withHeader("Content-Type", containing("application/json"))
            .withRequestBody(equalToJson(gson.toJson(movieListToAdd))));
}

  @Test
  public void testAddMovieToList() throws FileNotFoundException {

    Movie m4 = new Movie("Teletubbies", 10.0, "Other", 1);

    // Act
    remoteAccess.updateMovieList(testmovieList);

    WireMock.stubFor(post("/movielist/TestUser/addMovie")
        .willReturn(new ResponseDefinitionBuilder()
              .withHeader("Content-Type", "application/json")
              .withBody(gson.toJson(m4))));
    remoteAccess.addMovieToList(m4);

    // Assert
    WireMock.stubFor(get("/movielist/TestUser")
        .willReturn(new ResponseDefinitionBuilder()
        .withBody(gson.toJson(testmovieList))));
    MovieList updatedList = remoteAccess.getMovieListByUsername("TestUser");

    // Ensure the movie was added to the list
    assertTrue(updatedList.getMovies().contains(m4));
    
  }

    @Test
    public void testRemoveMovieList() throws Exception{
      String username = "testUser";
      remoteAccess = new MovieListRemoteAccess(URI.create("http://localhost:8080"), true);
      
      stubFor(delete(urlPathEqualTo("/movielist/" + username + "/deleteUser"))
              .willReturn(aResponse().withStatus(200)));
        
      assertDoesNotThrow(() -> remoteAccess.removeMovieList(username));

      //tries to do the same again, but expect a exception to be thrown.
      stubFor(delete(urlPathEqualTo("/movielist/" + username + "/deleteUser"))
              .willReturn(aResponse().withStatus(500)));

      RuntimeException exception = assertThrows(RuntimeException.class,
              () -> remoteAccess.removeMovieList(username));

      assertTrue(exception.getCause() instanceof IOException);
      assertEquals("Not legal status code", exception.getCause().getMessage());
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