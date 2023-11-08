package restapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.LoginController;

import java.io.IOException;

/**
 * JavaFX App
 */
public class MovieListRemoteApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("RemoteApp.fxml"));
        Parent parent = fxmlLoader.load();
        LoginController loginController = fxmlLoader.getController();
        stage.setScene(new Scene(parent));
        stage.show();
        loginController.setLoginControllerStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}