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

public class TopRatedController {

  @FXML private MenuButton filterbtn;
  @FXML private MenuItem views;
  @FXML private MenuItem bestRating;
  @FXML private MenuItem worstRating;
  @FXML private Label header1; 
  @FXML private TextArea topfield; 

  private List<MovieList> allMovieLists; 
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

  public TopRatedController(){
    this.movieList = new MovieList();
    //this.movielistAccess = movieListRemoteAccess;
}

  // public TopRatedController(){
  //   this.movieList = new MovieList();
  //   this.movielistAccess = new MovieListRemoteAccess(movieList);
  // }

  public void setMovielist(MovieList movieList){
    this.movieList = movieList;
  }

  public void setStage(Stage stage){
    this.topStage = stage;
  }

  public void initialize(MovieList movieList, MovieListAccess access) throws FileNotFoundException {
    this.movieList = movieList;
    this.movielistAccess = access;
    getAllMoviesFromFile();
  }

  private void getAllMoviesFromFile(){
    allMovies = new MovieList();
    allMovieLists = movielistAccess.getAllMovieListsFromFile();
    for (MovieList MovieList : allMovieLists) {
      for (Movie Movie : MovieList.getMovies()) {
        allMovies.addMovie(Movie);
      }
    }
  }

  @FXML
  private void handleFilterBtn(ActionEvent event){
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


  @FXML
  private void handleBackBtn2(ActionEvent event){
  try{
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
      topStage.close();
    } catch (IOException e){
      e.printStackTrace();
    }
  }

}
