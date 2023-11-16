package ui;


import core.MovieList;
import dataaccess.MovieListAccess;
import dataaccess.MovieListLocalAccess;
import dataaccess.MovieListRemoteAccess;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.HttpURLConnection;
import java.net.URL;

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
  private Stage loginControllerStage; 
  //private boolean remote; 

  private MovieListAccess  movielistAccess;


  public LoginController() {
    this.movieList = new MovieList();
  }

  /**
   * Method for updating MovieListContainerAccess.
   *
   * @param access  remote or direct access
   */
  public void initData(MovieListAccess access) {
    this.movielistAccess = access;
  }

  /**
   * Method for seting up the server. Tries to connect to the server first.
   * If the server is not running, it connects directly to a local file
   *
   * @throws URISyntaxException if string could not be passed as URI
   * @throws InterruptedException if thread is waiting or interrupted
   * @throws IOException signals that I/= has occurred
   */
  public void setUpAccess() throws IOException, InterruptedException, URISyntaxException {
    // this.remote = false;

    // try{
    // URL url = new URL("http://localhost:8080/movielist");
    // HttpURLConnection connect = (HttpURLConnection) url.openConnection();
    // connect.setRequestMethod("GET");
    // connect.connect();

    // int responseCode = connect.getResponseCode();
    
    // if (responseCode == HttpURLConnection.HTTP_OK) {
    //   this.remote = true;
    // }
    // } catch (IOException e) {
    //   System.out.println("error fetching remote movielist");
    // }

    // if(remote) {
    //   this.movielistAccess = new MovieListRemoteAccess(new URI("http://localhost:8080/movielist"), false);
    // } else {
    //   this.movielistAccess = new MovieListLocalAccess(this.movieList);
    // }
    try {
      this.movielistAccess = new MovieListRemoteAccess(new URI("http://localhost:8080/movielist"), false);
      System.out.println("Remote access");
    } catch (IOException | InterruptedException | URISyntaxException e) {
      this.movielistAccess = new MovieListLocalAccess(this.movieList);
      System.out.println("Local access");
    }
  }

  // /**
  //  * Method for getting the containerAccess that´s automatically chosen by setUpAccess().
  //  *
  //  * @return this.movielistAccess;
  //  */
  // public MovieListAccess getAccess() {
  //   return this.movielistAccess;
  // }


  // /**
  //  * Getter for the movielist.
  //  * Creates a copy to avoid exposing internal representation.
  //  *
  //  * @return a movielist.
  //  */
  // public MovieList getMovieList() {
  //   return movieList;
  // }

  /**
   * Method for setting the stage of the logincontroller.
   *
   * @param stage for logincontroller
   */
  public void setLoginControllerStage(final Stage stage) {
    this.loginControllerStage = stage;
  }


  /**
   * Method for opening a list by the username input.
   * If Register is clicked a new movielist if generated with that username.
   *
   * @param event actionevent
   */
  @FXML
  public void openList(final ActionEvent event) {
    if (openListBtn.getText().equals("Log in")) {
      openExistingMovieList(event);
    } else if (openListBtn.getText().equals("Register")) {
      makeNewMovieList(event);
    }
  }

  /**
   * FXML Method to check if user wants to log in or register to Movie Diary.
   *
   * @param event actionevent
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
  public void openExistingMovieList(ActionEvent event) {

    String usernameString = this.usernameInput.getText();
    String passwordString = this.passwordInput.getText();

    if (validInput(usernameString, passwordString)) {
      movieList = movielistAccess.getMovieListByUsername(usernameString);
      loadMovieList(event);
    }
  }

  /**
   * FXML method to make a new movie list.
   */
  @FXML
  public void makeNewMovieList(ActionEvent event) {
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
      movielistAccess.addMovieList(movieList);
      loadMovieList(event);
    }
  }

  /**
   * Validation method for username and string.
   *
   * @param username of movie
   * @param password of movie
   * @return boolean
   */
  private boolean validInput(String username, String password) {
    MovieList movieList;
    if (username.isEmpty() || password.isEmpty()) {
      feedback.setText("You have to fill inn both a username and password");
      return false;
    } 
    try {
      movieList = this.movielistAccess.getMovieListByUsername(username);
      if (movieList == null) {
        feedback.setText("Movielist doesn't exist");
        return false;
      }
    } catch (IllegalArgumentException e) {
      feedback.setText("Movielist doesn't exist");
      return false;
    }
    if (!movieList.getPassword().equals(password)) {
      feedback.setText("Wrong password");
      return false;
    }
    feedback.setText("");
    return true;
  }

  /**
   * FXML method to load a movie list, i.e movie diary.
   */
  @FXML
  public void loadMovieList(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieListApp.fxml"));
      Parent root = loader.load();
      AppController appController = loader.getController();
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      appController.initData(movieList, movielistAccess);
      //Stage stage = new Stage();
      appController.setMovielist(movieList);
      appController.updateMovieListField();
      appController.setMovieDiaryStage(stage);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
      cleansePage();
      //loginControllerStage.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * FXML method to cleanse the page.
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
   * @param username the username to validate
   * @throws IllegalArgumentException if a movieList with the specified username already exists
   */
  private boolean takenUsername(String username) {
    return movielistAccess.getAllMovieListsFromFile().stream()
                                                     .map(a -> a.getUsername())
                                                     .anyMatch(a -> a.equals(username));
  }

}
