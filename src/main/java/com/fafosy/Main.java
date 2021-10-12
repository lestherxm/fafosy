package com.fafosy;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX Main
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        String pathFile = "/view/fxml/login.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(pathFile));
                Scene scene = new Scene(root);
                    stage.setScene(scene);
                        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}