package com.museum.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import static com.museum.config.Constants.*;

import com.museum.services.JacksonService;

/**
 * JavaFX App
 */
public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        JacksonService.deserializeGame(getClass().getResource(JSON_CONFIGURATION_URI).toURI());

        scene = new Scene(loadFXML("view"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}