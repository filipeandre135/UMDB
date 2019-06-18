package UMDB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("UMDB");

        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/VistaLogin.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.centerOnScreen();

        stage.show();
    }
}
