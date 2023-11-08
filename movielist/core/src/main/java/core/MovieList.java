package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MovieList {
  private List<Movie> movies;

  private String username; 
  private String password; 

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setMovies(List<Movie> movies){
    this.movies = movies; 
  }

  public MovieList(){
    this.movies = new ArrayList<Movie>();
  }

  public List<Movie> getMovies() {
    return new ArrayList<Movie>(movies);
  } 
  
  public void addMovie(Movie movie){
    if(!checkDuplicate(movie)){
      movies.add(movie); 
    }
    movies.sort(Comparator.comparingDouble(Movie::getScore).reversed());
  }

  public void sortByCount(){
    movies.sort(Comparator.comparingInt(Movie::getCount).reversed()); 
  }

  public void sortByBestRating(){
    movies.sort(Comparator.comparingDouble(Movie::getScore).reversed()); 
  }

  public void sortByWorstRating(){
    movies.sort(Comparator.comparingDouble(Movie::getScore)); 
  }

  public int getNumberOfMovies(){
    return movies.size(); 
  }

  public String movieListToString(){
    String s = ""; 
    for (Movie movie : movies) {
      s += movie.getName() + ", " + movie.getScore() + "\n"; 
    }
    return s; 
  }

  public boolean checkDuplicate(Movie newMovie){
    boolean hasDuplicate = false;
    String newTitle = newMovie.getName();
    for(Movie m : getMovies()){
      String oldTitle = m.getName();
      if (oldTitle.equals(newTitle)){
        Double newScore = ((m.getScore() + newMovie.getScore()) / 2);
        String newGenre = newMovie.getGenre();
        m.setScore(newScore);
        m.setGenre(newGenre);
        m.addDuplicate();
        hasDuplicate = true;
      }
    }
    return hasDuplicate;
  }

}
