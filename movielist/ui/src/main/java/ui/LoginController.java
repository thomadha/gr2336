package ui;

import java.io.IOException;

import core.MovieList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import json.MovieListHandler;

public class LoginController {
  
    @FXML private TextField usernameInput;
    @FXML private TextField passwordInput;

    @FXML private Button openListBtn;
    @FXML private Button newOrLoginBtn;

    @FXML private Label feedback; 
    @FXML private Text openOrMakeTexts;
    @FXML private Text changeText;

    private MovieList movieList;
    private MovieListHandler movieListHandler;

    public LoginController() {
      movieList = new MovieList();
      movieListHandler = new MovieListHandler("/src/main/java/json/MovieList.json");
  }

    public MovieList getMovieList() {
      return movieList;
    }


    @FXML
    public void openList (ActionEvent event){
      if(openListBtn.getText().equals("Log in")){
          openExistingMovieList();
      }
      else if(openListBtn.getText().equals("Register")){
          makeNewMovieList();
      }
    }
  
    @FXML
    public void newOrLogin (ActionEvent event){
      if(newOrLoginBtn.getText().equals("Make new movie diary")){
        openOrMakeTexts.setText("Make your movie diary:");
        openListBtn.setText("Register");
        changeText.setText("Already have a movie list? Log in instead!");
        newOrLoginBtn.setText("Log into your account");
        feedback.setText("");
      }
      else if(newOrLoginBtn.getText().equals("Log into your account")){
        openOrMakeTexts.setText("Open your movie diary:");
        openListBtn.setText("Log in");
        changeText.setText("Don't have one yet? Make your own movie diary now!");
        newOrLoginBtn.setText("Make new movie diary");
        feedback.setText("");
      }
    }


  @FXML
    public void openExistingMovieList (){
  
      String usernameString = this.usernameInput.getText();
      String passwordString = this.passwordInput.getText();

      if(validInput(usernameString, passwordString)){
          movieList = movieListHandler.getMovieList(usernameString, passwordString);
          correctPassword(usernameString,passwordString);
          movieListHandler.saveToFile(movieList);
          loadMovieList();
      }
    }

    @FXML
    public void makeNewMovieList (){
      String usernameString = this.usernameInput.getText();
      String passwordString = this.passwordInput.getText();
      if(usernameString.equals("") || passwordString.equals("")){
          feedback.setText("You have to fill inn both a username and password");
      }
      else if(takenUsername(usernameString)){
          feedback.setText("The username you typed is taken");
      }
      else{
          movieList =  new MovieList();
          movieList.setUsername(usernameString);
          movieList.setPassword(passwordString);
          movieListHandler.saveToFile(movieList);
          loadMovieList();
      }
          

    }

    private boolean validInput(String username, String password){
        if(username.equals("") || password.equals("")){
            feedback.setText("You have to fill inn both a username and password");
            return false;
        } 
        try{
            movieListHandler.getMovieList(username, password);
        }
        catch (IllegalArgumentException e){
            feedback.setText("Movielist doesn't exist or password is incorrect");
            return false;
        }
        feedback.setText("");
        return true;
      }
  
    @FXML
    public void loadMovieList(){    
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieListApp.fxml"));
            Parent root = loader.load();
            AppController appController = loader.getController();
            appController.getMovieList();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            cleansePage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void cleansePage() {    
        usernameInput.setText("");
        passwordInput.setText("");
        feedback.setText("");
    }

    /**
   * Validates that a movieList with the specified username does not already exist.
   *
   * @param username the username to validate
   * @throws IllegalArgumentException if a movieList with the specified username already exists
   */
    private boolean takenUsername(String username){
        return movieListHandler.getAllMovieListsFromFile().stream()
                                                          .map(a -> a.getUsername())
                                                          .anyMatch(a -> a.equals(username));
    }

    private boolean correctPassword(String username, String password){
        return movieListHandler.getAllMovieListsFromFile().stream()
                                                          .filter(a -> a.getUsername().equals(username))
                                                          .allMatch(a -> a.getPassword().equals(password));
    }


}
