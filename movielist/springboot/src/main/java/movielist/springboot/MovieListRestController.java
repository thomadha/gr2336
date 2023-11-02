package movielist.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import core.Movie;
import core.MovieList;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/movielist")
public class MovieListRestController {
  
  // @RequestMapping("/movielist")
  // public String movielist() {
  //   return "Movielist is cool";
  // }

  @Bean
  public MovieListService movieListBean() {
    return new MovieListService();
  }

  public MovieListService movieListService = new MovieListService();

  /**
   * Method for opening a movielist
   * 
   * @param username
   * @return movieList
   */
  // localhost:8080/movielist/{username}
  @GetMapping("/{username}")
  public MovieList getMovieList(@PathVariable String username) {
    return null;
  }

  /**
   * Method for adding a movie
   * 
   * @param movie that'll be added to the movielist
   * @param username to add the movie to
   */
  // localhost:8080/movielist/add
  @PostMapping("/add")
  public void addMovie(@RequestBody Movie movie, @RequestBody String username) {
    // TODO
  }


  /**
   * Method for saving and closing a movielist
   * 
   * @param username that the list will be saved to
   * @param movieList that will be saved
   */
  // localhost:8080/movielist/{username}
  @PutMapping("{username}")
  public void saveAndCloseMovieList(@PathVariable String username, @RequestBody MovieList movieList) {
      //TODO
  }














}
