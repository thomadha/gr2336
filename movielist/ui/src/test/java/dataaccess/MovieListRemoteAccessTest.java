package dataaccess;
import org.junit.jupiter.api.TestInstance;
import com.github.tomakehurst.wiremock.WireMockServer;

import filehandler.MovieListHandler;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieListRemoteAccessTest {
  
  private static final int Port = 8080; 
  private static final String Host = "localhost";
  private static WireMockServer server; 
  private MovieListRemoteAccess remoteAccess; 

  private MovieListHandler movielisthandler = new MovieListHandler("/src/main/java/json/MovieList.json"); 
  private 
  


}