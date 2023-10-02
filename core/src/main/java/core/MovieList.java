package core;

import java.util.ArrayList;
import java.util.Comparator;
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
    if(!checkDuplicate(movie)){
      movies.add(movie); 
    }
    movies.sort(Comparator.comparingDouble(Movie::getScore).reversed());
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

  public static void main(String args[]){
    MovieList ml = new MovieList(); 

    Movie m1 = new Movie("hai", 1.0);
    Movie m2 = new Movie("sommer", 5.0);
    Movie m3 = new Movie("mathilde", 7.0);
    Movie m4 = new Movie("isak", 8.0);

    ml.addMovie(m1);
    ml.addMovie(m2);
    ml.addMovie(m3);
    ml.addMovie(m4);

    System.out.println(ml.movieListToString());

  }
}
