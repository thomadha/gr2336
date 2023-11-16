package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

import javafx.scene.input.MouseButton;
import org.testfx.util.WaitForAsyncUtils;
import javafx.scene.control.DialogPane;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import javafx.scene.control.ButtonType;


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
import ui.LoginController;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class LoginPageTest extends ApplicationTest {

    private LoginController controller;
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        root = loader.load();
        this.controller = loader.getController();
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