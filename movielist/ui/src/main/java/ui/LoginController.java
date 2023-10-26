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
  
    @FXML private TextField usernameInput = new TextField();
    @FXML private TextField passwordInput = new TextField();

    @FXML private Button openListBtn = new Button();
    @FXML private Button newOrLoginBtn = new Button();

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
    public void openListBtn (ActionEvent event){
      if(openListBtn.getText().equals("Log in")){
          openExistingMovieList();
      }
      else if(openListBtn.getText().equals("Register")){
          makeNewMovieList();
      }
    }
  
    @FXML
    public void newOrLogin (ActionEvent event){
      if(newOrLoginBtn.getText().equals("Make a new movie diary")){
        openOrMakeTexts.setText("Make your movie diary:");
        openListBtn.setText("Register");
        changeText.setText("Already have a movie list? Log in instead!");
        newOrLoginBtn.setText("Log into your account");
      }
      else if(newOrLoginBtn.getText().equals("Log into your account")){
        openOrMakeTexts.setText("Open your movie diary:");
        openListBtn.setText("Log in");
        changeText.setText("Don't have one yet? Make your own movie diary now!");
        newOrLoginBtn.setText("Make new movie diary");
      }
    }









  @FXML
    public void openExistingMovieList (){
  
      String usernameString = this.usernameInput.getText();
      String passwordString = this.passwordInput.getText();

      movieList = movieListHandler.getMovieList(usernameString, passwordString);

      if(!validateInput(usernameString, passwordString)){
        feedback.setText("Feil brukernavn eller passord :(");
        return;
      }

      // ADD VALIDATOR

      if(validateInput(usernameString, passwordString)){
        movieList = movieListHandler.getMovieList(usernameString);
        feedback.setText("");
        openMovieList();

      }
      

    }

    @FXML
    public void makeNewMovieList (){
      movieList =  new MovieList();

    }

    @FXML 
    private void handleOpenList(){
        if(validateUsernameField(usernameString)){
            try{
                feedback.setText("");
                movieList = movieListHandler.getMovieList(usernameString);
                updateMovieListField();
            }catch(Exception e){
                feedback.setText(e.getMessage());
                movieList = new MovieList();
                movieListField.getItems().clear();
            } 
        }
    }


    

  private boolean validateInput(String username, String password){
    if(username.equals("") || password.equals("")){
            feedback.setText("You have to fill inn both a username and password");
            return false;
        } 
    movieListHandler.getMovieList(username, password);
    return true;
      }
  

  @FXML
    public void openMovieList(){
      if(validateUsernameField(usernameString)){
            try{
                feedback.setText("");
                movieList=movieListHandler.getMovieList(userName.getText());
                updateMovieListField();
            }catch(Exception e){
                feedback.setText(e.getMessage());
                movieList = new MovieList();
                movieListField.getItems().clear();
            } 
        }

    }

    @FXML
    public void openMovieList(ActionEvent event){    
      if(validateInput(null, null))
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
    private void cleansePage() {    // hjelpemetode for å rense alle elementer i javaFX filen for tekst så det er klart til neste booking
        // setter alt tilbake til start:
        usernameInput.setText("");
        passwordInput.setText("");
    }

}
