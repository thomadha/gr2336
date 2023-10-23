package ui;


import core.Movie;
import core.MovieList;
import json.MovieListHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class AppController {

    @FXML private TextField titleField;
    @FXML private TextField userName;
    @FXML private Slider scoreField;
    @FXML private Button addBtn;
    @FXML private ListView<Movie> movieListField;
    @FXML private MenuButton genrebtn; 
    @FXML private MenuItem action; 
    @FXML private MenuItem horror; 
    @FXML private MenuItem romcom; 
    @FXML private Button openListBtn;
    @FXML private Button saveListBtn;
    @FXML private Label header; 
    @FXML private Label feedback; 



    private MovieListHandler fileHandler;

    private MovieList movieList;

    public AppController() {
        movieList = new MovieList();
        fileHandler = new MovieListHandler("/src/main/java/json/MovieList.json");
    }

    public MovieList getMovieList() {
        return movieList;
    }

     public MovieList getMovieList(String username) {
        
        return movieList;
    }

    @FXML
    private void handleAddBtn(ActionEvent event){
        if(validateUsernameField()){
            try{
                feedback.setText("");
                movieList.addMovie(new Movie(titleField.getText(), scoreField.getValue(),genrebtn.getText()));
                movieList.setUsername(userName.getText());
                updateMovieListField();
                fileHandler.saveToFile(movieList);
                resetChoises();
            }catch(Exception e){
                feedback.setText(e.getMessage());
            }
        }
    }

    @FXML
    private void resetChoises(){
        this.titleField.setText("");
        this.scoreField.setValue(0);
        this.genrebtn.setText("Choose genre");
    }

    @FXML
    private void updateMovieListField(){
        movieListField.getItems().clear();
        movieListField.getItems().addAll(movieList.getMovies());
    }

    @FXML
    private void handleChoise(ActionEvent e){
        MenuItem genrechoise = (MenuItem) e.getSource();
        this.genrebtn.setText(genrechoise.getText());
    }

    @FXML 
    private void handleOpenList(){
        if(validateUsernameField()){
            try{
                feedback.setText("");
                movieList=fileHandler.getMovieList(userName.getText());
                updateMovieListField();
            }catch(Exception e){
                feedback.setText(e.getMessage());
                movieList = new MovieList();
                movieListField.getItems().clear();
            } 
        }
    }


    @FXML
    private boolean validateUsernameField(){
        if(userName.getText().equals("")){
            feedback.setText("You have to fill inn a username");
            return false;
        } else {
            feedback.setText("");
            return true;
        }
    }

}
