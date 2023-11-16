package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import java.lang.reflect.InvocationTargetException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import ui.AppController;
import ui.LoginController;

public class LoginPageTest extends ApplicationTest {

    private LoginController controller;
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        root = loader.load();
        this.controller = loader.getController();
        controller.setUpAccess();
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
    public void testMissingPassword(){
        //tests to only input username
        clickOn("#usernameInput").write("Username123");
        clickOn("#loginBtn");

        // initializing text-field to check correct output
        String text = "You have to fill inn both a username and password";
        Label feedback = (Label) root.lookup("#feedback");
        assertEquals(feedback.getText(), text);

        //clears screen
        clickOn("#newUserBtn");
        clickOn("#newUserBtn");

        //try again to write only password without username
        clickOn("#passwordInput").write("NoUsername");
        clickOn("#loginBtn");

        // initializing text-field to check correct output
        feedback = (Label) root.lookup("#feedback");
        assertEquals(feedback.getText(), text);
    }
    @Test
    public void newOrLogin(){
        clickOn("#newUserBtn");
        Button btn = (Button) lookup("#loginBtn").query();
        assertEquals(btn.getText(), "Register");
        clickOn("#newUserBtn");
        btn = (Button) lookup("#loginBtn").query();
        assertEquals(btn.getText(), "Log in");
    }

    @Test
    public void loginExistingUser() {
            // logging in existing user
            clickOn("#usernameInput").write("test3");
            clickOn("#passwordInput").write("123");
            clickOn("#loginBtn");
            //initializing text-field to check correct output
            String text = "My Movie Diary";
            Label header = (Label) lookup("#header").query();
            assertEquals(header.getText(), text);
        
    }


    @Test
    public void iterateThrough() throws InvocationTargetException{
        
        // new user
        newUser();
        
        // initializing text-field to check correct output
        // String text = "My movie diary";
        // Label header = (Label) lookup("#header").query();
        // assertEquals(header.getText(), text);
        
        //navigate back
        clickOn("#backBtn");
        
        // try to create new user, with the already used username
        clickOn("#newUserBtn");
 
        clickOn("#usernameInput").write("TestUser");
 
        clickOn("#passwordInput").write("NEW");
        clickOn("#loginBtn");
 
        // initializing text-field to check correct output
        String text = "";
        Label feedback = (Label) root.lookup("#feedback");
        assertEquals(feedback.getText(), text);
 
        //back to loginmeny
        clickOn("#newUserBtn");

        logInWithNewUser();
        removeNewUser();

    }

    @Test
    public void testAddMovie(){

        newUser();

        // Writes title
        clickOn("#titleField").write("Batman");
        
        // initiates slider
        Slider slider = lookup("#scoreField").query();

        // Define the desired slider value (e.g., 5)
        double desiredValue = 5.0;

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

        removeNewUser();
        
    }

    @Test
    public void testTopList(){
        newUser();
        WaitForAsyncUtils.waitForFxEvents();


        clickOn("#topBtn");

        clickOn("#filterbtn");
        clickOn("#views");
        //check correct output
        String t1 = "Views";
        MenuButton btn1 = (MenuButton) lookup("#filterbtn").query();
        assertEquals(btn1.getText(), t1);
        

        clickOn("#filterbtn");
        clickOn("#bestRating");
        //check correct output
        String t2 = "Best rating";
        MenuButton btn2 = (MenuButton) lookup("#filterbtn").query();
        assertEquals(btn2.getText(), t2);

        
        clickOn("#filterbtn");
        clickOn("#worstRating");
         //check correct output
         String t3 = "Worst rating";
         MenuButton btn3 = (MenuButton) lookup("#filterbtn").query();
         assertEquals(btn3.getText(), t3);

        clickOn("#backBtn");
        removeNewUser();
        
    }
    

    public void newUser(){
        // selecting new user
        sleep(2000);
        clickOn("#newUserBtn");
        
        // creating new user
        clickOn("#usernameInput").write("TestUser");
        clickOn("#passwordInput").write("TestPassword");
        clickOn("#loginBtn");
    }

    public void logInWithNewUser(){
        //logging in with correct info
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#usernameInput").write("TestUser");
        clickOn("#passwordInput");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#passwordInput");
        clickOn("#passwordInput").write("TestPassword");
        clickOn("#loginBtn");
    }

    public void removeNewUser(){
        //deleting
        clickOn("#deleteMovieListBtn");

        // TestFX provides support for handling dialogs
        interact(() -> {
            // Find the alert dialog
            DialogPane dialogPane = lookup(".dialog-pane").queryAs(DialogPane.class);

            //check that its correct
            verifyThat(dialogPane.getContentText(), hasText("Press OK to confirm, or Cancel to go back."));

            // Click the OK button to confirm deletion
            Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
            //navigate back
            clickOn(okButton);
        });

    }

}