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
    public void testUserNotFound(){
        // tests invalid username or password
        clickOn("#usernameInput").write("Username123");
        clickOn("#passwordInput").write("password123");
        clickOn("#loginBtn");

        // initializing text-field to check correct output
        String text = "Movielist doesn't exist";
        Label feedback = (Label) root.lookup("#feedback");
        assertEquals(feedback.getText(), text);

        try {
            Thread.sleep(3000);
        } catch (Exception e) {}


    }

    @Test
    public void testMissingPassword(){
        //tests to only inpu username
        clickOn("#usernameInput").write("Username123");
        clickOn("#loginBtn");

        // initializing text-field to check correct output
        String text = "You have to fill inn both a username and password";
        Label feedback = (Label) root.lookup("#feedback");
        assertEquals(feedback.getText(), text);
    }

    // @Test
    // public void testNewUser(){
    //     // selecting new user
    //     clickOn("#newUserBtn");
    //     try {
    //         Thread.sleep(3000);
    //     } catch (Exception e) {}
        
    //     // creating new user
    //     clickOn("#usernameInput").write("TestUser");
    //     clickOn("#passwordInput").write("TestPassword");
    //     clickOn("#loginBtn");

    //     try {
    //         Thread.sleep(3000);
    //     } catch (Exception e) {}
    // }


    

}