package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovieListOverviewTest {

  @Test
  @DisplayName("check if overview is correckt")
  public void testOverview(){
      Movie m1 = new Movie("m1", 5);
      Movie m2 = new Movie("m2", 5);
      Movie m3 = new Movie("m3", 5);
      Movie m4 = new Movie("m4", 5);

      MovieList list1 = new MovieList(); 
      MovieList list2 = new MovieList(); 
      list1.addMovie(m1);
      list1.addMovie(m2);
      list2.addMovie(m3);
      list2.addMovie(m4);

      MovieListOverview overview = new MovieListOverview(); 

      overview.addMovieList("nr1", list1);
      overview.addMovieList("nr2", list2);

      Assertions.assertEquals(list1, overview.getMovieList("nr1"));
      Assertions.assertEquals(list2, overview.getMovieList("nr2"));
    }


}
