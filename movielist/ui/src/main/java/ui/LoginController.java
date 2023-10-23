package ui;

import java.io.IOException;
import java.util.List;

import core.MovieList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    public void openExistingMovieList (ActionEvent event){
  
      String usernameString = this.usernameInput.getText();
      String passwordString = this.passwordInput.getText();

      if(!validateInput(usernameString, passwordString)){
        System.out.println("Feil brukernavn eller passord :(");
        return;
      }

      movieList.setUsername(usernameString);
      movieList.setPassword(passwordString);

      openMovieList();

    }

  private boolean validateInput(String username, String password){
    return true;

  }

  @FXML
    public void makeNewMovieList (ActionEvent event){
    }
  

  @FXML
    public void openMovieList(){
            var selectionModel = output.getSelectionModel();
            List<Object> selectedItem = selectionModel.getSelectedItem();
            BookingInfoController.selectedItem = selectedItem;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieListApp.fxml"));
            Parent root = loader.load();
            RoomBookingController roomBookingController = loader.getController();
            roomBookingController.setBookingData(bookingData);
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openMovieList(ActionEvent event){    // når bruker trykker på "tilbake til forsiden" knappen
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieListApp.fxml"));
            Parent root = loader.load();
            AppController appController = loader.getController();
            appController.getMovieList();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            cleansePage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    @FXML
    private void cleansePage() {    // hjelpemetode for å rense alle elementer i javaFX filen for tekst så det er klart til neste booking
        // setter alt tilbake til start:
        usernameInput.setDisable(false);
        passwordInput.setDisable(false);
        loginBtn.setDisable(false);
        usernameInput.setText("");
        passwordInput.setText("");
        newUserBtn.setDisable(false);
    }


}
