package ui;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App.
 */
public class App extends Application {

  /**
   * Method to start javafx app.
   */
  @Override
  public void start(Stage stage) throws IOException, InterruptedException, URISyntaxException {
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("LoginPage.fxml"));
    Parent parent = fxmlLoader.load();
    LoginController loginController = fxmlLoader.getController();
    loginController.setUpAccess();
    stage.setScene(new Scene(parent));
    stage.show();
    loginController.setLoginControllerStage(stage);
  }

  /**
   * Method to launch javafx app.
   *
   * @param args input
   */
  public static void main(final String[] args) {
    launch();
  }
}
