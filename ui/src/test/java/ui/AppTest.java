package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

import javafx.scene.input.MouseButton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.Node;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import ui.AppController;

public class AppTest extends ApplicationTest {

    private AppController controller;
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
        root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Parent getRootNode() {
        return root;
    }


    @Test
    public void testAppInitialization() {
        // Test the initialization of the App class
        assertNotNull(controller);
    }

    @Test
    public void testAddingMovie() {
        
        clickOn("#titleField").write("Movie Title");
        //clickOn("#scoreField").write("5.0");
        
        Slider slider = lookup("#scoreField").query();

        // Define the desired slider value (e.g., 5)
        double desiredValue = 5.0;

        Node thumb = slider.lookup(".thumb");
        // Click on the slider to focus it
        clickOn(thumb, MouseButton.PRIMARY);

        // Calculate the relative x-coordinate based on the desired value
        double sliderMaxValue = slider.getMax();
        double sliderWidth = slider.getWidth();
        double relativeX = (desiredValue / sliderMaxValue) * sliderWidth;

        // Simulate dragging the thumb to the desired value
        drag(thumb).dropBy(relativeX, 0);
        sleep(1000);

        clickOn("#addBtn");
        
        //verifyThat(slider, hasText("5.0"));

    // You can also check the value programmatically
        //assertEquals(5.0, slider.getValue(), 0.1);


        sleep(2000);
        //Assertions.assertEquals();

        // Add your assertions to validate the expected behavior
        // For example, you can check if the movie list is updated correctly
        // using controller.getMovieList() and assert the movie's presence.
    }

    

}