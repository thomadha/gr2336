package movielist.springboot;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import core.Movie;
import core.MovieList;
import filehandler.MovieListHandler;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Configuration
@RestController
@RequestMapping("/movielist")
public class MovieListRestController {

  // @Bean
  // public MovieListService movieListBean(){
  //   return new MovieListService(); 
  // }

  public MovieListService movielistservice = new MovieListService();

  //localhost:8080/movielist/getall
  @GetMapping("/getall")
  public List<MovieList> getAllMovieLists(){
    return movielistservice.getMovieLists(); 
  }

  //localhost:8080/movielist/movielistbyname
  @GetMapping("/{username}")
  public MovieList getMovieListbyname(@PathVariable("username") String username) {
      return movielistservice.getMovieListByUsername(username); 
  }

  // Aner ikke hva som blir best måte her, vi vet ikke om add er riktig. 
  //localhost:8080/movielist/add
  @PostMapping("/add")
  public void addMovieList(@RequestBody MovieList movielist) {
    movielistservice.addMovieList(movielist);
  }
  

  // vet ikke om denne er riktig heller
    //localhost:8080/movielist/getall/delete
  @DeleteMapping("/{username}")
  public void deleteMovieList(@RequestBody String username) throws IOException{
    movielistservice.deleteMovieList(username);
  }


  




//   // @Autowired
//   // public MovieListRestController(MovieListHandler movieListHandler) {
//   //     this.movieListHandler = movieListHandler;
//   // }


  
//   // @RequestMapping("/movielist")
//   // public String movielist() {
//   //   return "Movielist is cool";
//   // }

//   @Bean
//   public MovieListService movieListBean() {
//     return new MovieListService();
//   }

//   /**
//    * Method for opening a movielist
//    * 
//    * @param username
//    * @return movieList
//    */
//   // localhost:8080/movielist/{username}
//   @GetMapping("/{username}")
//   public MovieList getMovieList(@PathVariable String username) {
//     return movielistservice.getMovieList(username);
//   }

//   /**
//    * Method for adding a movie
//    * 
//    * @param movie that'll be added to the movielist
//    * @param username to add the movie to
//    */
//   // localhost:8080/movielist/add
//   @PostMapping("/add")
//   public void addMovie(@RequestBody Movie movie, @RequestBody String username) {
//     movielistservice.addMovietoMovielist(movie, username); 
//   }


//   /**
//    * Method for saving and closing a movielist
//    * 
//    * @param username that the list will be saved to
//    * @param movieList that will be saved
//    */
//   // localhost:8080/movielist/{username}
//   @PutMapping("/{username}")
//   public void saveAndCloseMovieList(@PathVariable String username) {
//     movielistservice.saveAndCloseMovieList(username);
//   }
}
