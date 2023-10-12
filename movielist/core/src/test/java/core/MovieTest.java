package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovieTest {

    
    @Test 
    @DisplayName("Checks if the name is correct")
    public void testValidName() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2);
        Assertions.assertEquals("Shark 2", shark.getName());
    }
    
    @Test 
    @DisplayName("Checks if the score is correct")
    public void testValidScore() throws IllegalArgumentException {
        Movie shark = new Movie("Shark 2", 2);
        Assertions.assertEquals(2, shark.getScore());
    }
    

    @Test 
    @DisplayName("Invalid score throws exception")
    public void testInvalidScore(){
        Assertions.assertThrows(IllegalArgumentException.class, 
        () -> {new Movie("Mean Girls", 13);}, 
        "wrong format on score"); 
        //sjekker at den kaster når den skal, altså dersom det blir oppgitt feil format på score
    }
    
    @Test 
    @DisplayName("Invalid score throws exception")
    public void testInvalidName(){
        Assertions.assertThrows(IllegalArgumentException.class, 
        () -> {new Movie("", 5);}, 
        "wrong format on name"); 
        //sjekker at den kaster når den skal, altså dersom det blir oppgitt feil format på name
    }

    @Test
    @DisplayName("Check if count is one for new movies")
    public void testInitialCount(){
        Movie shark = new Movie("Shark 2", 2);
        Assertions.assertEquals(1, shark.getCount());

    }

    @Test
    @DisplayName("Check if count is three after two duplicates")
    public void testCounter(){
        Movie shark = new Movie("Shark 2", 2);
        shark.addDuplicate();
        shark.addDuplicate();
        Assertions.assertEquals(3, shark.getCount());

    }

    @Test
    @DisplayName("Test setGenre")
    public void testSetGenre(){
        Movie shark = new Movie("Shark 2", 2);
        shark.setGenre("horror");
        Assertions.assertEquals("horror", shark.getGenre());

    }
}
