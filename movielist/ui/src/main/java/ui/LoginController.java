package ui;

import core.MovieList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
  
  @FXML private TextField usernameInput = new TextField();
  @FXML private TextField passwordInput = new TextField();
  @FXML private Button loginBtn = new Button();
  @FXML private Button newUserBtn = new Button();
  @FXML private TextField newUsernameInput = new TextField();
  @FXML private TextField newPasswordInput = new TextField();
  @FXML private Button createUserBtn = new Button();

  private MovieList movieList;
    
    public void setBookingData(MovieList movieList) {
        this.movieList = movieList;
    }

  @FXML
    public void openMovieList (ActionEvent event){
  
      String usernameString = this.usernameInput.getText();
      String passwordString = this.passwordInput.getText();

      if(!validateInput(usernameString, passwordString)){
        System.out.println("Feil brukernavn eller passord :(");
        return;
      }

      movieList.setUsername(usernameString);
      movieList.setPassword(passwordString);

    }

  private boolean validateInput(String username, String password){
    return true;

  }


}
