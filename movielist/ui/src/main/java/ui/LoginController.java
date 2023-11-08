package ui;

import java.io.IOException;

import core.MovieList;
import filehandler.MovieListHandler;
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

/**
 * Class for the Login page/controller.
 */
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
  private Stage loginControllerStage;

  /**
  * Constructor for the login controller.
  */
  public LoginController() {
    movieList = new MovieList();
    movieListHandler = new MovieListHandler("/src/main/java/json/MovieList.json");
  }

  /**
   * Getter for the movielist.
   * 
   * @return a movielist.
   */
  public MovieList getMovieList() {
    return movieList;
  }

  /**
   * Method for setting the stage of the logincontroller.
   * 
   * @param stage.
   */
  public void setLoginControllerStage(Stage stage){
    this.loginControllerStage = stage;
  }


  /**
   * Method for opening a list by the username input.
   * If Register is clicked a new movielist if generated with that username.
   * @param event.
   */
  @FXML
  public void openList (ActionEvent event){
    if(openListBtn.getText().equals("Log in")){
      openExistingMovieList();
    }
    else if(openListBtn.getText().equals("Register")){
      makeNewMovieList();
    }
  }

  /**
   * FXML Method to check if user wants to log in or register to Movie Diary.
   * @param event.
   */
  @FXML
  public void newOrLogin (ActionEvent event){
    if(newOrLoginBtn.getText().equals("Make new movie diary")){
      openOrMakeTexts.setText("Make your movie diary:");
      openListBtn.setText("Register");
      changeText.setText("Already have a movie list? Log in instead!");
      newOrLoginBtn.setText("Log into your account");
      cleansePage();
    }
    else if(newOrLoginBtn.getText().equals("Log into your account")){
      openOrMakeTexts.setText("Open your movie diary:");
      openListBtn.setText("Log in");
      changeText.setText("Don't have one yet? Make your own movie diary now!");
      newOrLoginBtn.setText("Make new movie diary");
      cleansePage();
    }
  }


  /**
   * FXML method to open an exciting movie list when log in is clicked.
   */
  @FXML
  public void openExistingMovieList (){
  
    String usernameString = this.usernameInput.getText();
    String passwordString = this.passwordInput.getText();

    if(validInput(usernameString, passwordString)){
      movieList = movieListHandler.getMovieList(usernameString);
      movieListHandler.saveToFile(movieList);
      loadMovieList();
    }
  }

  /**
   * FXML method to make a new movie list when register user is clicked.
   */
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
    else {
      movieList =  new MovieList();
      movieList.setUsername(usernameString);
      movieList.setPassword(passwordString);
      movieListHandler.saveToFile(movieList);
      loadMovieList();
    }
  }

  /**
   * Method to check if the input for username and password is valid.
   * @param username.
   * @param password.
   * @return "MovieList doesn't exist" and false if username is wrong, or "Wrong password" and false if password doesn't exist. True else.
   */
  private boolean validInput(String username, String password){
    MovieList movieList;
    if(username.isEmpty() || password.isEmpty()){
      feedback.setText("You have to fill inn both a username and password");
      return false;
    }
    try{
      movieList = movieListHandler.getMovieList(username);
    }
    catch (IllegalArgumentException e){
      feedback.setText("Movielist doesn't exist");
      return false;
    }
    if(!movieList.getPassword().equals(password)){
      feedback.setText("Wrong password");
      return false;
    }
    feedback.setText("");
    return true;
  }

  /**
   * FXML method to load a movielist after a person has logged in or registered.
   * Will then open the Movie Diary.
   */
  @FXML
  public void loadMovieList(){
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieListApp.fxml"));
      Parent root = loader.load();
      AppController appController = loader.getController();
      Scene scene = new Scene(root);
      Stage stage = new Stage();
      appController.setMovielist(movieList);
      appController.updateMovieListField();
      appController.setMovieDiaryStage(stage);
      stage.setScene(scene);
      stage.show();
      cleansePage();
      loginControllerStage.close();
    } catch (IOException e) {
        e.printStackTrace();
      }
  }

  /**
   * FXML method to cleanse/remove the login page.
   */
  @FXML
  private void cleansePage() {
    usernameInput.setText("");
    passwordInput.setText("");
    feedback.setText("");
  }

    /**
   * Validates that a movieList with the specified username does not already exist.
   *
   * @param username the username to validate.
   * @throws IllegalArgumentException if a movieList with the specified username already exists.
   */
  private boolean takenUsername(String username){
        return movieListHandler.getAllMovieListsFromFile().stream()
                                                          .map(a -> a.getUsername())
                                                          .anyMatch(a -> a.equals(username));
  }

}
