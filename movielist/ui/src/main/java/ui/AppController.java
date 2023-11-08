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

    private Stage movieDiaryStage; 

    private MovieListHandler fileHandler;

    private MovieList movieList;

    /**
     * Method to initiate a new movielist and movielisthandler with the javafx application.
     */
    public AppController() {
        movieList = new MovieList();
        fileHandler = new MovieListHandler("/src/main/java/json/MovieList.json");
    }

    /**
     * Getter for movielist.
     * 
     * @return a MovieList object.
     */
    public MovieList getMovieList() {
        return movieList;
    }

    /**
     * Setter for the MovieList object.
     * 
     * @param movieList with Movie objects.
     */
    public void setMovielist(MovieList movieList){
        this.movieList = movieList;
    }

    /**
     * Sets the stage for the movie diary.
     * @param stage.
     */
    public void setMovieDiaryStage(Stage stage){
        this.movieDiaryStage = stage;
    }

    /**
     * FXML method to handle the add button for a movie.
     * @param event.
     */
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

    /**
     * FXML method to reset choices after a movie has been added, meaning clearing the text fiels.
     */
    @FXML
    private void resetChoises(){
        this.titleField.setText("");
        this.scoreField.setValue(0);
        this.genrebtn.setText("Choose genre");
    }

    /**
     * FXML method to clear field with the movielist, and add the movies in the current MovieList object.
     */
    @FXML
    public void updateMovieListField(){
        movieListField.getItems().clear();
        movieListField.getItems().addAll(movieList.getMovies());
    }

    /**
     * FXML method to handle the user choosing a movie gender from the 'menu'.
     * @param e.
     */
    @FXML
    private void handleChoise(ActionEvent e){
        MenuItem genrechoise = (MenuItem) e.getSource();
        this.genrebtn.setText(genrechoise.getText());
    }

    /**
     * FXML method for going back to the login page when in the movielist page.
     * This is the page you get to after you log in or create user.
     * @param event.
     */
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

    /**
     * FXML method for going from the movielist page to the top rated page. 
     * @param event.
     */
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

    /**
     * FXML method to delete a movielist.
     * It can't be regretted and there are extra features to avoid deleting by accident.
     * @param e.
     */
    @FXML
    private void handleDeleteMovieList(ActionEvent e){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete movie list");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Press OK to confirm, or Cancel to go back.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                fileHandler.removeMovieList(movieList);
                handleBackBtn(e);
            }
        });
}




}
