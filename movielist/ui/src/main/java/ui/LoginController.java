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

  /**
   * Textfield for username of movielist.
   */
  @FXML private TextField usernameInput;
  /**
   * Textfield for username of movielist.
   */
  @FXML private TextField passwordInput;
  /**
   * Button for opening the movielist.
   */
  @FXML private Button openListBtn;
  /**
   * Button for switching between creating a new list or logging in.
   */
  @FXML private Button newOrLoginBtn;
  /**
   * Label to give feedack if something's wrong.
   */
  @FXML private Label feedback;
  /**
   * Text that shows open or register moviediary.
   */
  @FXML private Text openOrMakeTexts;
  /**
   * If you're logging in this shows "want to register instead".
   * And vice versa.
   */
  @FXML private Text changeText;
  /**
   * Movielist that will be opened.
   */
  private MovieList movieList;
  /**
   * Filehandler for the movielist.
   */
  private MovieListHandler movieListHandler;
  /**
   * Stage for the login controller.
   */
  private Stage loginControllerStage;

  /**
  * Constructor for the login controller.
  */
  public LoginController() {
    movieList = new MovieList();
    movieListHandler = new MovieListHandler(
      "/src/main/java/json/MovieList.json");
  }

  /**
   * Getter for the movielist.
   * Creates a copy to avoid exposing internal representation.
   *
   * @return a movielist.
   */
  public MovieList getMovieList() {
    MovieList movieListCopy = new MovieList();
    movieListCopy.setMovies(this.movieList.getMovies());
    return movieListCopy;
  }

  /**
   * Method for setting the stage of the logincontroller.
   *
   * @param stage
   */
  public void setLoginControllerStage(final Stage stage) {
    this.loginControllerStage = stage;
  }


  /**
   * Method for opening a list by the username input.
   * If Register is clicked a new movielist if generated with that username.
   *
   * @param event
   */
  @FXML
  public void openList(final ActionEvent event) {
    if (openListBtn.getText().equals("Log in")) {
      openExistingMovieList();
    } else if (openListBtn.getText().equals("Register")) {
      makeNewMovieList();
    }
  }

  /**
   * FXML Method to check if user wants to log in or register to Movie Diary.
   *
   * @param event
   */
  @FXML
  public void newOrLogin(final ActionEvent event) {
    if (newOrLoginBtn.getText().equals("Make new movie diary")) {
        openOrMakeTexts.setText("Make your movie diary:");
        openListBtn.setText("Register");
        changeText.setText("Already have a movie list? Log in instead!");
        newOrLoginBtn.setText("Log into your account");
        cleansePage();
    } else if (newOrLoginBtn.getText().equals("Log into your account")) {
        openOrMakeTexts.setText("Open your movie diary:");
        openListBtn.setText("Log in");
        changeText.setText("Don't have one yet? Make your movie diary now!");
        newOrLoginBtn.setText("Make new movie diary");
        cleansePage();
    }
  }


  /**
   * FXML method to open an exciting movie list when log in is clicked.
   */
  @FXML
  public void openExistingMovieList() {

    String usernameString = this.usernameInput.getText();
    String passwordString = this.passwordInput.getText();

    if (validInput(usernameString, passwordString)) {
      movieList = movieListHandler.getMovieList(usernameString);
      movieListHandler.saveToFile(movieList);
      loadMovieList();
    }
  }

  /**
   * FXML method to make a new movie list when register user is clicked.
   */
  @FXML
  public void makeNewMovieList() {
    String usernameString = this.usernameInput.getText();
    String passwordString = this.passwordInput.getText();
    if (usernameString.equals("") || passwordString.equals("")) {
        feedback.setText("You have to fill inn both a username and password");
    } else if (takenUsername(usernameString)) {
        feedback.setText("The username you typed is taken");
    } else {
        movieList =  new MovieList();
        movieList.setUsername(usernameString);
        movieList.setPassword(passwordString);
        movieListHandler.saveToFile(movieList);
        loadMovieList();
    }
  }

  /**
   * Method to check if the input for username and password is valid.
   *
   * @param username
   * @param password
   *
   * @return "MovieList doesn't exist" and false if username is wrong.
   * Or "Wrong password" and false if password doesn't exist. True else.
   */
  private boolean validInput(final String username, final String password) {
    MovieList movieListCheck;
    if (username.isEmpty() || password.isEmpty()) {
      feedback.setText("You have to fill inn both a username and password");
      return false;
    }
    try {
      movieListCheck = movieListHandler.getMovieList(username);
    } catch (IllegalArgumentException e) {
      feedback.setText("Movielist doesn't exist");
      return false;
    }
    if (!movieListCheck.getPassword().equals(password)) {
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
  public void loadMovieList() {
    try {
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("MovieListApp.fxml"));
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
   * Validates that a movieList with the specified username doesn't exist.
   *
   * @param username the username to validate.
   * @throws IllegalArgumentException
   * if a movieList with the specified username already exists.
   *
   * @return a boolean if username is taken or not.
   */
  private boolean takenUsername(final String username) {
        return movieListHandler.getAllMovieListsFromFile().stream()
                                .map(a -> a.getUsername())
                                .anyMatch(a -> a.equals(username));
  }

}
