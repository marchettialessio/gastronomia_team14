package com.museum.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.museum.config.Constants.FXML_MUSEUM;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * JavaFX App
 */
public class main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        scene = new Scene(loadFXML(FXML_MUSEUM), 410, 580);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}