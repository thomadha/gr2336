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

    /**
     * Textfield to write title of movie.
     */
    @FXML private TextField titleField;
    /**
     * Slider to choose score of movie.
     */
    @FXML private Slider scoreField;
    /**
     * Button to add a movie to list.
     */
    @FXML private Button addBtn;
    /**
     * View of all movies in the movielist.
     */
    @FXML private ListView<Movie> movieListField;
    /**
     * Button to choose genre of movie.
     */
    @FXML private MenuButton genrebtn;
    /**
     * Choice in genrebtn.
     */
    @FXML private MenuItem action;
    /**
     * Choice in genrebtn.
     */
    @FXML private MenuItem horror;
    /**
     * Choice in genrebtn.
     */
    @FXML private MenuItem romcom;
    /**
     * Header label for movielist.
     */
    @FXML private Label header;
    /**
     * Feedback label to show messages.
     */
    @FXML private Label feedback;
    /**
     * Back button to go back to login page.
     */
    @FXML private Button backBtn;
    /**
     * Delete button for deleting an entire movielist.
     */
    @FXML private Button deleteMovieListBtn;
    /**
    * Stage for the movie diary.
    */
    private Stage movieDiaryStage;
    /**
     * Filhandler for the movielist.
     */
    private MovieListHandler fileHandler;
    /**
     * The movielist to add movies to.
     */
    private MovieList movieList;

    /**
     * Method to initiate a new movielist and movielisthandler.
     * Uses javafx application.
     */
    public AppController() {
        movieList = new MovieList();
        fileHandler = new MovieListHandler(
            "/src/main/java/json/MovieList.json");
    }

    /**
     * Getter for movielist.
     * Creates a copy of the movielist to avoid direct access.
     *
     * @return a MovieList object.
     */
    public MovieList getMovieList() {
        return movieList;
    }

    /**
     * Setter for the MovieList object.
     *
     * @param movieListInput with Movie objects.
     */
    public void setMovielist(final MovieList movieListInput) {
        this.movieList = movieListInput;
    }

    /**
     * Sets the stage for the movie diary.
     * @param stage
     */
    public void setMovieDiaryStage(final Stage stage) {
        this.movieDiaryStage = stage;
    }

    /**
     * FXML method to handle the add button for a movie.
     * @param event
     */
    @FXML
    private void handleAddBtn(final ActionEvent event) {
        try {
            feedback.setText("");

            movieList.addMovie(new Movie(
            titleField.getText(), scoreField.getValue(), genrebtn.getText()
            ));

            updateMovieListField();
            fileHandler.saveToFile(movieList);
            resetChoises();
        } catch (Exception e) {
            feedback.setText(e.getMessage());
        }
    }

    /**
     * FXML method to reset choices after a movie has been added.
     * Means clearing the text fields.
     */
    @FXML
    private void resetChoises() {
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
     * @param e
     */
    @FXML
    private void handleChoise(final ActionEvent e) {
        MenuItem genrechoise = (MenuItem) e.getSource();
        this.genrebtn.setText(genrechoise.getText());
    }

    /**
     * FXML method for going back to the login page when in the movielist page.
     * This is the page you get to after you log in or create user.
     * @param event
     */
    @FXML
    private void handleBackBtn(final ActionEvent event) {
        try {
            FXMLLoader loaders = new FXMLLoader(
                getClass().getResource("LoginPage.fxml"));
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
     * FXML method for switching pages.
     * Goes from the movielist page to the top rated page.
     *
     * @param event
     */
    @FXML
    private void handleTopListBtn(final ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("TopRated.fxml"));
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * FXML method to delete a movielist.
     * It can't be regretted.
     * There are extra features to avoid deleting by accident.
     *
     * @param e
     */
    @FXML
    private void handleDeleteMovieList(final ActionEvent e) {
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
