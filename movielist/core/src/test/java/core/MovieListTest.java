package core;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovieListTest {
    
    @Test
    @DisplayName("Checks if the list is correct")
    public void testMovieList() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "");
        Movie meangirls = new Movie("Mean Girls", 10, "");
        Movie harry = new Movie("Harry Potter 1", 9, "");
        
        MovieList movielist = new MovieList();
        
        movielist.addMovie(shark);
        movielist.addMovie(meangirls);
        movielist.addMovie(harry);

        Assertions.assertEquals(shark, movielist.getMovies().get(2));
        Assertions.assertEquals(meangirls, movielist.getMovies().get(0));
        Assertions.assertEquals(harry, movielist.getMovies().get(1));
        Assertions.assertEquals(3, movielist.getNumberOfMovies());
    }

    @Test
    @DisplayName("Check if duplicates work")
    public void testDuplicateMovieList() throws IllegalArgumentException{
        Movie shark1 = new Movie("Shark 2", 2, "");

        MovieList movielist = new MovieList();

        movielist.addMovie(shark1);

        Movie shark2 = new Movie("Shark 2", 7, "");

        movielist.addMovie(shark2);

        Assertions.assertEquals(2 , movielist.getMovies().get(0).getCount());
        Assertions.assertEquals(4.5 , movielist.getMovies().get(0).getScore());
    }

    @Test
    @DisplayName("Check if password and username works")
    public void checkPasswordAndUsername() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "");
        Movie meangirls = new Movie("Mean Girls", 10, "");
        Movie harry = new Movie("Harry Potter 1", 9, "");

        MovieList movielist = new MovieList();

        movielist.addMovie(shark);
        movielist.addMovie(meangirls);
        movielist.addMovie(harry);

        movielist.setUsername("testuser");
        movielist.setPassword("testpassword");

        Assertions.assertEquals("testuser", movielist.getUsername());
        Assertions.assertEquals("testpassword", movielist.getPassword());
    }

    @Test
    @DisplayName("Test toString method")
    public void checkMovieListToString() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "");
        Movie meangirls = new Movie("Mean Girls", 10, "");

        MovieList movielist = new MovieList();

        movielist.addMovie(shark);
        movielist.addMovie(meangirls);

        String answer = "Mean Girls, 10.0\nShark 2, 2.0\n";

        Assertions.assertEquals(answer, movielist.movieListToString());
    }

    /**
     * Test that sort by view rating work.
     * @throws IllegalArgumentException
     */
    @Test
    @DisplayName("Check sort by view ratings work")
    public void checkSortingView() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "");
        Movie meangirls = new Movie("Mean Girls", 10, "");
        Movie harry = new Movie("Harry Potter 1", 9, "", 5);

        MovieList movielist = new MovieList();

        movielist.addMovie(shark);
        movielist.addMovie(meangirls);
        movielist.addMovie(harry);

        movielist.sortByCount();
        Assertions.assertEquals(harry, movielist.getMovies().get(0));
        Assertions.assertEquals(meangirls, movielist.getMovies().get(1));
        Assertions.assertEquals(shark, movielist.getMovies().get(2));

    }

    /**
     * Test that sort by best rating works.
     * @throws IllegalArgumentException
     */
    @Test
    @DisplayName("Check sort by best ratings work")
    public void checkSortingBestRating() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "");
        Movie meangirls = new Movie("Mean Girls", 10, "");
        Movie harry = new Movie("Harry Potter 1", 9, "", 5);

        MovieList movielist = new MovieList();

        movielist.addMovie(shark);
        movielist.addMovie(meangirls);
        movielist.addMovie(harry);

        movielist.sortByBestRating();
        Assertions.assertEquals(meangirls, movielist.getMovies().get(0));
        Assertions.assertEquals(harry, movielist.getMovies().get(1));
        Assertions.assertEquals(shark, movielist.getMovies().get(2));

    }

    /**
     * Test that sort by worst rating works.
     * @throws IllegalArgumentException
     */
    @Test
    @DisplayName("Check sort by best ratings work")
    public void checkSortingWorstRating() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "");
        Movie meangirls = new Movie("Mean Girls", 10, "");
        Movie harry = new Movie("Harry Potter 1", 9, "", 5);

        MovieList movielist = new MovieList();

        movielist.addMovie(shark);
        movielist.addMovie(meangirls);
        movielist.addMovie(harry);

        movielist.sortByWorstRating();

        Assertions.assertEquals(shark, movielist.getMovies().get(0));
        Assertions.assertEquals(harry, movielist.getMovies().get(1));
        Assertions.assertEquals(meangirls, movielist.getMovies().get(2));

    }

    /**
     * Tests that you can set a new list in movies with setMovies.
     * @throws IllegalArgumentException
     */
    @Test
    @DisplayName("Check that you can set a new list")
    public void checkSetMovies() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "");
        Movie meangirls = new Movie("Mean Girls", 10, "");

        List<Movie> movies = new ArrayList<>();

        movies.add(shark);
        movies.add(meangirls);

        MovieList movielist = new MovieList();

        movielist.setMovies(movies);

        Assertions.assertEquals(shark, movielist.getMovies().get(0));
        Assertions.assertEquals(meangirls, movielist.getMovies().get(1));

    }
}
