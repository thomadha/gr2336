package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovieListTest {

    
    @Test 
    @DisplayName("Checks if the list is correct")
    public void testMovieList() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2);
        Movie meangirls = new Movie("Mean Girls", 10);
        Movie harry = new Movie("Harry Potter 1", 9);
        
        MovieList movielist = new MovieList();
        
        movielist.addMovie(shark);
        movielist.addMovie(meangirls);
        //movielist.addMovie(new Movie("WRONG", 13));
        movielist.addMovie(harry);

        Assertions.assertEquals(shark, movielist.getMovies().get(2));
        Assertions.assertEquals(meangirls, movielist.getMovies().get(0));
        Assertions.assertEquals(harry, movielist.getMovies().get(1));
        Assertions.assertEquals(3, movielist.getNumberOfMovies());


    }

    @Test
    @DisplayName("Check if duplicates work")
    public void testDuplicateMovieList() throws IllegalArgumentException{
        Movie shark1 = new Movie("Shark 2", 2);

        MovieList movielist = new MovieList();

        movielist.addMovie(shark1);

        Movie shark2 = new Movie("Shark 2", 7);

        movielist.addMovie(shark2);

        Assertions.assertEquals(2 , movielist.getMovies().get(0).getCount());
        Assertions.assertEquals(4.5 , movielist.getMovies().get(0).getScore());


    }


}
