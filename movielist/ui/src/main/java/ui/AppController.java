package ui;


import java.io.IOException;

import core.Movie;
import core.MovieList;
import filehandler.MovieListHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    private Stage movieDiaryStage; 

    private MovieListHandler fileHandler;

    private MovieList movieList;

    public AppController() {
        movieList = new MovieList();
        fileHandler = new MovieListHandler("/src/main/java/json/MovieList.json");
    }

    public MovieList getMovieList() {
        return movieList;
    }

    public void setMovielist(MovieList movieList){
        this.movieList = movieList; 
    }

    public void setMovieDiaryStage(Stage stage){
        this.movieDiaryStage = stage; 
    }

    @FXML
    private void handleAddBtn(ActionEvent event){
        try{
            feedback.setText("");
            movieList.addMovie(new Movie(titleField.getText(), scoreField.getValue(),genrebtn.getText()));
            updateMovieListField();
            fileHandler.saveToFile(movieList);
            resetChoises();
        }catch(Exception e){
            feedback.setText(e.getMessage());
        }
    }

    @FXML
    private void resetChoises(){
        this.titleField.setText("");
        this.scoreField.setValue(0);
        this.genrebtn.setText("Choose genre");
    }

    @FXML
    public void updateMovieListField(){
        movieListField.getItems().clear();
        movieListField.getItems().addAll(movieList.getMovies());
    }

    @FXML
    private void handleChoise(ActionEvent e){
        MenuItem genrechoise = (MenuItem) e.getSource();
        this.genrebtn.setText(genrechoise.getText());
    }

    @FXML
    private void handleBackBtn(ActionEvent event){
        try {
            FXMLLoader loaders = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent roots = loaders.load();
            movieListField.getItems().clear();
            resetChoises();
            Scene scenes = new Scene(roots);
            Stage stages = new Stage();
            stages.setScene(scenes);
            stages.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void handleTopListBtn(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TopRated.fxml"));
            Parent root = loader.load();
            TopRatedController topRatedController = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            topRatedController.setStage(stage);
            topRatedController.setMovielist(movieList);
            topRatedController.initialize();
            stage.setScene(scene);
            stage.show();
            movieDiaryStage.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }



}
