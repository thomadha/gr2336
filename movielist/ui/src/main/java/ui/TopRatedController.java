package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import core.Movie;
import core.MovieList;
import dataaccess.MovieListAccess;
//import dataaccess.MovieListRemoteAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Controller for the Top Rated page.
 */
public class TopRatedController {

  /**
   * Button for filering the ratings.
   */
  @FXML private MenuButton filterbtn;
  /**
   * Item in filerbutton.
   */
  @FXML private MenuItem views;
  /**
   * Item in filerbutton.
   */
  @FXML private MenuItem bestRating;
  /**
   * Item in filerbutton.
   */
  @FXML private MenuItem worstRating;
  /**
   * Header label.
   */
  @FXML private Label header1;
  /**
   * Text area for topfield.
   */
  @FXML private TextArea topfield;

  /**
   * List of all the different movielists.
   */
  private List<MovieList> allMovieLists;
  /**
   * A movielist containing all movies ever added.
   */
  private MovieList allMovies;
  private MovieList movieList;
  private Stage topStage; 

  //private MovieListRemoteAccess  movielistRemoteAccess;
  private MovieListAccess movielistAccess;

  /**
   * Method for getting the containerAccess thats automoticly chosen by setUpAccess().
   *
   * @return this.containerAccess;
   */
  public MovieListAccess getAccess() {
    return this.movielistAccess;
  }

  /**
   * Constructor for the toprated controller.
   * Initiates a movielist and a filehandler.
   */
  public TopRatedController() {
    this.movieList = new MovieList();
    //this.movielistAccess = movieListRemoteAccess;
}

  // public TopRatedController(){
  //   this.movieList = new MovieList();
  //   this.movielistAccess = new MovieListRemoteAccess(movieList);
  // }

  /**
   * Setter for the movielist.
   * @param movieList to show.
   */
  public void setMovielist(final MovieList movieList) {
    this.movieList = movieList;
  }

  /**
   * Setter for the TopRatedController stage.
   * @param stage
   */
  public void setStage(final Stage stage) {
    this.topStage = stage;
  }

  public void initialize(MovieList movieList, MovieListAccess access) throws FileNotFoundException {
    this.movieList = movieList;
    this.movielistAccess = access;
    getAllMoviesFromFile();
  }

  /**
   * Method to retrieve all the movies from the user.
   */
  private void getAllMoviesFromFile() {
    allMovies = new MovieList();
    allMovieLists = movielistAccess.getAllMovieListsFromFile();
    for (MovieList movielist : allMovieLists) {
      for (Movie movie : movielist.getMovies()) {
        allMovies.addMovie(movie);
      }
    }
  }

  /**
   * FXML method to filter movies by users choice.
   * @param event
   */
  @FXML
  private void handleFilterBtn(final ActionEvent event) {
      MenuItem filterchoise = (MenuItem) event.getSource();
      String text = filterchoise.getText();
      header1.setText(text.toLowerCase());
      this.filterbtn.setText(text);
      topfield.setText("");
      switch (text) {
        case "Views":
          allMovies.sortByCount();
          for (Movie m : allMovies.getMovies()) {
            topfield.setText(topfield.getText() + m.viewsString());
          }
          break;

        case "Worst rating":
          allMovies.sortByWorstRating();
          for (Movie m : allMovies.getMovies()) {
            topfield.setText(topfield.getText() + m.scoreString());
          }
          break;

        default:
          allMovies.sortByBestRating();
          for (Movie m : allMovies.getMovies()) {
            topfield.setText(topfield.getText() + m.scoreString());
          }
          break;
      }
  }


  /**
   * Back button to return to the MovieDiary.
   * Does not return to login page.
   * @param event
   */
  @FXML
  private void handleBackBtn2(final ActionEvent event) {
  try {
      FXMLLoader loader = new FXMLLoader(
        getClass().getResource("MovieListApp.fxml"));
      Parent root = loader.load();
      AppController appController = loader.getController();
      Scene scene = new Scene(root);
      Stage stage = new Stage();
      appController.setMovielist(movieList);
      movielistAccess.updateMovieList(movieList);
      appController.initData(movieList, movielistAccess);
      appController.updateMovieListField();
      appController.setMovieDiaryStage(stage);
      stage.setScene(scene);
      stage.show();
      topStage.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
