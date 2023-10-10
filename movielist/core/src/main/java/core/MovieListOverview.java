package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;


public class MovieListOverview {
  
  HashMap<String, MovieList> movieOverview;


  public MovieListOverview() {
    this.movieOverview = new HashMap<>();
  }

  public void addMovieList(String username, MovieList movieList) {
    movieOverview.put(username, movieList);
    
    if (movieOverview.keySet().contains(username)) {

    }
  }

  public MovieList getMovieList(String username) {
    if (movieOverview.keySet().contains(username)) {
      return movieOverview.get(username);
    }
    else {
      MovieList emptyList = new MovieList();
      return emptyList;
    }
  }







}