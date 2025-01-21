package com.museum.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.museum.config.Constants.*;

import com.museum.data.Game;
import com.museum.services.GoogleCloudBucketService;
import com.museum.services.JacksonService;

/**
 * JavaFX App
 */
public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
      //  Game game = JacksonService.deserializeGame(JSON_CONFIGURATION_PATH);
      //  JacksonService.serializeGame(game, "museum_team/src/main/resources/com/museum/app/json/serialprova.json");
        // List<String> list = GoogleCloudBucketService.GetAvailableConfigurationList();
        // Game game2 = GoogleCloudBucketService.LoadGame("serialprova.json");
        // GoogleCloudBucketService.SaveGame("prova", new Game(new ArrayList<>()));
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