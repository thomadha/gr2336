package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

import javafx.scene.input.MouseButton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.Node;
import org.testfx.util.WaitForAsyncUtils;
import javafx.scene.control.DialogPane;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import javafx.scene.control.ButtonType;

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
import ui.LoginController;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class AppTest extends ApplicationTest {

    private AppController controller;
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieListApp.fxml"));
        root = loader.load();
        this.controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        controller.setMovieDiaryStage(stage);
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
        
        // Writes title
        clickOn("#titleField").write("Batman");
        
        // initiates slider
        Slider slider = lookup("#scoreField").query();

        // Define the desired slider value (e.g., 5)
        double desiredValue = 9.0;

        //locates the slider "button"
        Node thumb = slider.lookup(".thumb");
        // Click on the slider to focus it
        clickOn(thumb, MouseButton.PRIMARY);

        // Calculate the relative x-coordinate based on the desired value
        double sliderMaxValue = slider.getMax();
        double sliderWidth = slider.getWidth();
        double relativeX = (desiredValue / sliderMaxValue) * sliderWidth;

        // Simulate dragging the button to the desired value
        drag(thumb).dropBy(relativeX, 0);
        sleep(1000);

        // select genre for the movie added
        clickOn("#genrebtn");
        clickOn("#action");

        //adds movie
        clickOn("#addBtn");
    }

    @Test
    public void testBackBtn(){
        clickOn("#backBtn");
    }
}
    
