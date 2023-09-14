package core;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
  private List<Movie> movies;

  public MovieList(){
    this.movies = new ArrayList<Movie>();
  }

  public List<Movie> getMovies() {
    return movies;
  } 
  
  public void addMovie(Movie movie){
    movies.add(movie); 
  }

  public int getNumberOfMovies(){
    return movies.size(); 
  }
}
