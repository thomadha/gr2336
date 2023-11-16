package ui;


import core.Movie;
import core.MovieList;
import dataaccess.MovieListAccess;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Class for the appcontroller, which is the movie diary.
 */
public class AppController {


  @FXML private TextField titleField;

  @FXML private Slider scoreField;

  @FXML private Button addBtn;

  @FXML private ListView<Movie> movieListField;

  @FXML private MenuButton genrebtn;

  @FXML private MenuItem action;

  @FXML private MenuItem horror;

  @FXML private MenuItem romcom;

  @FXML private Label header;

  @FXML private Label feedback;

  @FXML private Button backBtn;

  @FXML private Button deleteMovieListBtn;

  @FXML private Button topListBtn;
    
  private MovieListAccess  movielistAccess;
    
  private Stage movieDiaryStage;
    
  private MovieList movieList;

  public AppController() {
    this.movieList = new MovieList();
  }


  /**
  * Method for populating the scene with the items.
  *
  * @throws FileNotFoundException  if file is not found
  */
  public void initData(MovieList movieList, MovieListAccess access) throws FileNotFoundException {
    this.movieList = movieList;
    this.movielistAccess = access;
  }

  // /**
  //  * Getter for movielist.
  //  * Creates a copy of the movielist to avoid direct access.
  //  *
  //  * @return a MovieList object.
  //  */
  // public MovieList getMovieList() {
  //   return movieList;
  // }

  /**
   * Setter for the MovieList object.
   *
   * @param movieList with Movie objects.
   */
  public void setMovielist(final MovieList movieList) {
    this.movieList = movieList;
  }

  /**
   * Sets the stage for the movie diary.
   *
   * @param stage to set
   */
  public void setMovieDiaryStage(final Stage stage) {
    this.movieDiaryStage = stage;
  }

  /**
   * FXML method to handle the add button for a movie.
   *
   * @param event to handle
   */
  @FXML
  private void handleAddBtn(final ActionEvent event) {
    try {
      feedback.setText("");
      movielistAccess.updateMovieList(movieList);
      Movie newMovie = new Movie(titleField.getText(), scoreField.getValue(), genrebtn.getText());
      movielistAccess.addMovieToList(newMovie);
      updateMovieListField();
      resetChoices();
    } catch (Exception e) {
      feedback.setText(e.getMessage());
    }
  }

  /**
   * FXML method to reset choices after a movie has been added.
   * Means clearing the text fields.
  */
  @FXML
  private void resetChoices() {
    this.titleField.setText("");
    this.scoreField.setValue(0);
    this.genrebtn.setText("Choose genre");
  }

  /**
   * FXML method to clear field with the movielist.
   * Will also add the movies in the current MovieList object.
   */
  @FXML
  public void updateMovieListField() {
    movieListField.getItems().clear();
    movieListField.getItems().addAll(movieList.getMovies());
  }

  /**
   * FXML method to handle the user choosing a movie gender from the 'menu'.
   *
   * @param e actionevent
   */
  @FXML
  private void handleChoice(ActionEvent e) {
    MenuItem genrechoice = (MenuItem) e.getSource();
    this.genrebtn.setText(genrechoice.getText());
  }

  /**
   * FXML method for going back to the login page when in the movielist page.
   * This is the page you get to after you log in or create user.
   *
   * @param event actionevent
   */
  @FXML
  private void handleBackBtn(final ActionEvent event) {
    try {
      FXMLLoader loaders = new FXMLLoader(
          getClass().getResource("LoginPage.fxml"));
      Parent roots = loaders.load();
      LoginController loginController = loaders.getController();
      movieListField.getItems().clear();
      resetChoices();
      loginController.initData(movielistAccess);
      Scene scenes = new Scene(roots);
      Stage stages = new Stage();
      stages.setScene(scenes);
      stages.show();
      loginController.setLoginControllerStage(stages);
      movieDiaryStage.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * FXML method for switching pages.
   * Goes from the movielist page to the top rated page.
   *
   * @param event actionevent
   */
  @FXML
  private void handleTopListBtn(final ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("TopRated.fxml"));
      Parent root = loader.load();
      TopRatedController topRatedController = loader.getController();
      topRatedController.setMovielist(movieList);
      topRatedController.initialize(movieList, movielistAccess);
      Scene scene = new Scene(root);
      Stage stage = new Stage();
      topRatedController.setStage(stage);
      stage.setScene(scene);
      stage.show();
      movieDiaryStage.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * FXML method to delete a movielist.
   * It can't be regretted.
   * There are extra features to avoid deleting by accident.
   *
   * @param e actionevent
   */
  @FXML
  private void handleDeleteMovieList(final ActionEvent e) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Delete movie list");
    alert.setHeaderText("Are you sure?");
    alert.setContentText("Press OK to confirm, or Cancel to go back.");

    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        String username = this.movieList.getUsername();
        movielistAccess.removeMovieList(username);
        handleBackBtn(e);
      }
    });
  }
}
