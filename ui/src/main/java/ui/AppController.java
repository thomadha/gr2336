package ui;

import core.Movie;
import core.MovieList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AppController {

    @FXML private TextField titleField = new TextField();
    @FXML private Slider scoreField = new Slider();
    @FXML private Button addBtn = new Button();
    @FXML private TextArea movieListField = new TextArea();

    private MovieList movieList;

    public AppController() {
        movieList = new MovieList();
    }

    public MovieList getMovieList() {
        return movieList;
    }


    @FXML
    void initialize() {
        updateMovieListField();
    }


    @FXML
    private void handleAddBtn(ActionEvent event){
        String title = this.titleField.getText();
        double score = this.scoreField.getValue();

        Movie new_movie = new Movie(title, score); 
        movieList.addMovie(new_movie);

        clearTextFields();
        updateMovieListField();
    }

    @FXML
    private void clearTextFields(){
        this.titleField.setText("");
        this.scoreField.setValue(0);
    }

    @FXML
    private void updateMovieListField(){
        movieListField.setText("");
        for (Movie m : movieList.getMovies()) {
            movieListField.appendText("Title: " + m.getName() + ", score: " + m.getScore() + "\n");
        }
    }
}
