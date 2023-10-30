package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.Movie;
import core.MovieList;
import filehandler.MovieListHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class TopRatedController {

  @FXML private MenuButton filterbtn;
  @FXML private MenuItem views;
  @FXML private MenuItem bestRating;
  @FXML private MenuItem worstRating;
  @FXML private ListView<Movie> movieListField; 

  private List<MovieList> allMovieLists; 
  private MovieList allMovies;
  private MovieListHandler fileHandler;
  private MovieList movieList;
  private Stage topStage; 

  public TopRatedController(){
    this.movieList = new MovieList();
    this.fileHandler = new MovieListHandler("/src/main/java/json/MovieList.json");
  }

  public void setMovielist(MovieList movieList){
    this.movieList = movieList;
  }

  public void setStage(Stage stage){
    this.topStage = stage;
  }

  public void initialize(){
    getAllMoviesFromFile();
    updateMovieListField();
  }

  private void updateMovieListField(){
    movieListField.getItems().clear();
    movieListField.getItems().addAll(allMovies.getMovies());
  }

  private void getAllMoviesFromFile(){
    allMovies = new MovieList();
    allMovieLists = fileHandler.getAllMovieListsFromFile();
    for (MovieList MovieList : allMovieLists) {
      for (Movie Movie : MovieList.getMovies()) {
        allMovies.addMovie(Movie);
      }
    }
  }

  @FXML
  private void handleFilterBtn(ActionEvent event){
      MenuItem filterchoise = (MenuItem) event.getSource();
      this.filterbtn.setText(filterchoise.getText());
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
