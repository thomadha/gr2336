package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ui.AppController;

public class AppTest extends ApplicationTest {

    private AppController controller;

    @BeforeEach
    public void setUp() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/App.fxml"));
        Scene scene = new Scene(loader.load());
        controller = loader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testAddMovie() {
        TextField titleField = lookup("#titleField").query();
        Slider scoreField = lookup("#scoreField").query();
        Button addBtn = lookup("#addBtn").query();
        TextArea movieListField = lookup("#movieListField").query();

        // Test adding a movie
        clickOn(titleField).write("Movie 1");
        clickOn(scoreField).type(KeyCode.RIGHT).type(KeyCode.RIGHT); // Increase score by 2
        clickOn(addBtn);
        
        // Verify if the movie has been added and the fields are cleared
        assertEquals("", titleField.getText());
        assertEquals(0.0, scoreField.getValue());
        assertTrue(movieListField.getText().contains("Title: Movie 1, score: 2.0"));
    }
}