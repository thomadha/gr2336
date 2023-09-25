package ui;

import core.Movie;
import core.MovieList;
import json.MovieListHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AppController {

    @FXML private TextField titleField;
    @FXML private Slider scoreField;
    @FXML private Button addBtn;
    @FXML private TextArea movieListField;
    @FXML private MenuButton genrebtn; 
    @FXML private MenuItem action; 
    @FXML private MenuItem horror; 
    @FXML private MenuItem romcom; 


    private MovieListHandler fileHandler;

    private MovieList movieList;

    public AppController() {
        movieList = new MovieList();
        fileHandler = new MovieListHandler();
    }

    public MovieList getMovieList() {
        return movieList;
    }


    @FXML
    void initialize() {
        initializeMovieField();
    }


    @FXML
    private void handleAddBtn(ActionEvent event){
        String title = this.titleField.getText();
        double score = this.scoreField.getValue();

        Movie new_movie = new Movie(title, score); 
        movieList.addMovie(new_movie);

        addMovieToList(movieList);
        clearTextFields();
        updateMovieListField();
    }

    @FXML
    private void clearTextFields(){
        this.titleField.setText("");
        this.scoreField.setValue(0);
    }

    @FXML
    private void addMovieToList(MovieList list){
        fileHandler.writeMovieListToFile(list);
    }


    @FXML
    private void initializeMovieField(){
        this.movieListField.setText("");
    }

    @FXML
    private void updateMovieListField(){
        this.movieListField.setText("");

        MovieList movies = fileHandler.readMovieListFromFile();
        
        for (Movie m : movies.getMovies()) {
            movieListField.appendText("Title: " + m.getName() + ", score: " + m.getScore() + "\n");
        }
    }

    @FXML
    private void handleChoise(ActionEvent event){
        MenuItem genrechoise = (MenuItem) event.getSource();
        this.genrebtn.setText(genrechoise.getText());
    }

}
