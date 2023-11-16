package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovieTest {


    // TESTS FOR MOVIE CONSTRUCTOR WITH 3 INPUTS.
    /**
     * Tests if name assigning is correct when using contructor.
     * @throws IllegalArgumentException
     */
    @Test
    @DisplayName("Checks if the name is correct")
    public void testValidName() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "");
        Assertions.assertEquals("Shark 2", shark.getName());
    }

    /**
     * Tests if score assigning is correct when using contructor.
     * @throws IllegalArgumentException
     */
    @Test
    @DisplayName("Checks if the score is correct")
    public void testValidScore() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "");
        Assertions.assertEquals(2, shark.getScore());
    }

    /**
     * Tests if score assigning is correct when using contructor.
     * @throws IllegalArgumentException
     */
    @Test
    @DisplayName("Checks if the genre is correct")
    public void testValidGenre() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "horror");
        Assertions.assertEquals("horror", shark.getGenre());
    }

    /**
     * Tests if constructor throws exception if score is wrong.
     */
    @Test
    @DisplayName("Invalid score throws exception")
    public void testInvalidScore() {
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> {
                new Movie("Mean Girls", 13, ""); }, "wrong format score");
    }

    /**
     * Tests is constructor throws exception if name is wrong.
     */
    @Test
    @DisplayName("Invalid name throws exception")
    public void testInvalidName() {
        Assertions.assertThrows(IllegalArgumentException.class,
        () -> {
            new Movie("", 5, ""); }, "wrong format on name");
    }

    /**
     * Tests is constructor throws exception if name is wrong.
     */
    @Test
    @DisplayName("Invalid genre throws exception")
    public void testInvalidGenre() {
        Assertions.assertThrows(IllegalArgumentException.class,
        () -> {
            new Movie("Mean", 5, "Choose genre"); }, "wrong format on genre");
    }

// TESTS FOR MOVIE CONSTRUCTOR WITH 4 INPUTS.

        /**
     * Tests if name assigning is correct when using contructor.
     * @throws IllegalArgumentException
     */
    @Test
    @DisplayName("Checks if the name is correct")
    public void testValidNameSecConstructor() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "", 3);
        Assertions.assertEquals("Shark 2", shark.getName());
    }

    /**
     * Tests if score assigning is correct when using contructor.
     * @throws IllegalArgumentException
     */
    @Test
    @DisplayName("Checks if the score is correct")
    public void testValidScoreSecConstructor() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "", 3);
        Assertions.assertEquals(2, shark.getScore());
    }

    /**
     * Tests if score assigning is correct when using contructor.
     * @throws IllegalArgumentException
     */
    @Test
    @DisplayName("Checks if the genre is correct")
    public void testValidGenreSecConstructor() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2, "horror",2);
        Assertions.assertEquals("horror", shark.getGenre());

        Movie lion = new Movie("Lion", 2, "Choose genre", 2);
        Assertions.assertEquals("Must specify genre", lion.getGenre());

    }

    /**
     * Tests if constructor throws exception if score is wrong.
     */
    @Test
    @DisplayName("Invalid score throws exception")
    public void testInvalidScoreSecondConstructor() {
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> {
                new Movie("Mean Girls", 13, "", 2); }, "wrong format score");
    }

    /**
     * Tests is constructor throws exception if name is wrong.
     */
    @Test
    @DisplayName("Invalid name throws exception")
    public void testInvalidNameSecondConstructor() {
        Assertions.assertThrows(IllegalArgumentException.class,
        () -> {
            new Movie("", 5, "", 2); }, "wrong format on name");
    }

    /**
     * Tests is constructor throws exception if name is wrong.
     */
    @Test
    @DisplayName("Invalid genre throws exception")
    public void testInvalidGenreSecConstructor() {
        Assertions.assertThrows(IllegalArgumentException.class,
        () -> {
            new Movie("", 5, "hei", 2); }, "wrong format on genre");
    }




    // OTHER TESTS.

    /**
     * Test that movies start with moviecount 1 unless specifies.
     */
    @Test
    @DisplayName("Check if count is one for new movies")
    public void testInitialCount() {
        Movie shark = new Movie("Shark 2", 2, "");
        Assertions.assertEquals(1, shark.getCount());
    }

    /**
     * Tests that moviecount updates when movie is duplicated.
     */
    @Test
    @DisplayName("Check if count is three after two duplicates")
    public void testCounter() {
        Movie shark = new Movie("Shark 2", 2, "");
        shark.addDuplicate();
        shark.addDuplicate();
        Assertions.assertEquals(3, shark.getCount());
    }

    /**
     * Tests that it works to set genre.
     */
    @Test
    @DisplayName("Test setGenre")
    public void testSetGenre() {
        Movie shark = new Movie("Shark 2", 2, "");
        shark.setGenre("horror");
        Assertions.assertEquals("horror", shark.getGenre());
    }

    /**
     * Tests that it works to set name.
     */
    @Test
    @DisplayName("Test setGenre")
    public void testSetName() {
        Movie shark = new Movie("Shark 2", 2, "");
        shark.setName("Shark 3");
        Assertions.assertEquals("Shark 3", shark.getName());
    }

    // TEST TOSTRING METHODS.
    /**
     * Test to chack that toString method works.
     */
    @Test
    @DisplayName("Test toString")
    public void testToString() {
        Movie shark = new Movie("Shark 2", 2, "", 2);
        shark.setGenre("horror");
        Assertions.assertEquals(("Shark 2 \n - Genre: horror - Score: 2.0 - Views: 2"), shark.toString());
    }

    /**
     * Test to chack that toString method works.
     */
    @Test
    @DisplayName("Test scoreString")
    public void testScoreString() {
        Movie shark = new Movie("Shark 2", 2, "", 2);
        shark.setGenre("horror");
        Assertions.assertEquals(("Shark 2\n - Score: 2.0\n\n"), shark.scoreString());
    }

    /**
     * Test to chack that toString method works.
     */
    @Test
    @DisplayName("Test viewString")
    public void testViewString() {
        Movie shark = new Movie("Shark 2", 2, "", 2);
        shark.setGenre("horror");
        Assertions.assertEquals(("Shark 2\n - Views: 2\n\n"), shark.viewsString());
    }


}
