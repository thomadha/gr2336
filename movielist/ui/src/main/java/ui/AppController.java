package ui;


import java.io.IOException;

import core.Movie;
import core.MovieList;
import dataaccess.MovieListAccess;
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
    
    private MovieListAccess  movielistAccess;

    private Stage movieDiaryStage; 

    private MovieList movieList;

    public AppController() {
        movieList = new MovieList();
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
            movielistAccess.saveToFile(movieList);
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
            LoginController loginController = loaders.getController(); 
            movieListField.getItems().clear();
            resetChoises();
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

    @FXML
    private void handleDeleteMovieList(ActionEvent e){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete movie list");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Press OK to confirm, or Cancel to go back.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                movielistAccess.removeMovieList(movieList.getUsername());
                handleBackBtn(e);
            }
        });
}




}
