package movielist.springboot;

import com.google.gson.Gson;
import core.Movie;
import core.MovieList;
import java.io.IOException;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Rest controller class for MovieList.
 */
@Configuration
@RestController
@RequestMapping("/movielist")
public class MovieListRestController {
  private MovieListService movielistservice; 

  // @Bean
  // public MovieListService movieListBean(){
  //   return new MovieListService(); 
  // }


  public MovieListRestController() {
    movielistservice = new MovieListService();
  }



  //localhost:8080/movielist/getall
  @GetMapping("/getall")
  public List<MovieList> getAllMovieLists() {
    return movielistservice.getMovieLists(); 
  }

  //localhost:8080/movielist/{username}
  @GetMapping("/{username}")
  public MovieList getMovieListbyname(@PathVariable("username") String username) {
    return movielistservice.getMovieListByUsername(username); 
  }

  /**
   * A method adding movielist to a given body.
   *
   * @param body body
   */
  //localhost:8080/movielist/add
  @PostMapping("/add")
  public void addMovieList(@RequestBody String body) {
    Gson gson = new Gson();
    MovieList movielist = gson.fromJson(body, MovieList.class);
    movielistservice.addMovieList(movielist);
  }

  //localhost:8080/movielist/{username}/deleteUser
  @DeleteMapping("/{username}/deleteUser")
  public void deleteMovieList(@PathVariable("username") String username) throws IOException {
    movielistservice.deleteMovieList(username);
  }

  /**
   * A method that adds a movie to movielist.
   *
   * @param body Takes inn a body element.
   * @param username Takes in a username String element.
   * @return A movie element.
   * @throws IOException Throws exception if wrongful input
   */
  //localhost:8080/movielist/{username}/addMovie
  @PostMapping("/{username}/addMovie")
  public Movie addMovie(@RequestBody String body, 
      @PathVariable("username") String username) throws IOException {
    Gson gson = new Gson();
    Movie movie = gson.fromJson(body, Movie.class);
    movielistservice.addMovieToList(username, movie);
    return movie;
  }

  //localhost:8080/movielist/{username}/{movieTitle}
  @GetMapping("/{username}/{movieTitle}")
  public Movie getMovie(@PathVariable("movieTitle") String movieTitle,
        @PathVariable("username") String username) throws IOException {
    return movielistservice.getMovie(username, movieTitle);
  }

  //localhost:8080/movielist/{username}/numberOfMovies
  @GetMapping("/{username}/numberOfMovies")
  public int getNumberOfMovies(@PathVariable("username") String username) {
    return movielistservice.getNumberOfMovies(username);
  }

  //localhost:8080/movielist/{username}/password
  @GetMapping("/{username}/password")
  public String getPassword(@PathVariable("username") String username) {
    return movielistservice.getPassword(username);
  }

  //localhost:8080/movielist/{username}/password/newUser
  @PostMapping("/{username}/{password}/newUser")
  public MovieList newMovieList(@PathVariable("username") String username,
        @PathVariable("password") String password) {
    return movielistservice.newMovieList(username, password);
  }
}
