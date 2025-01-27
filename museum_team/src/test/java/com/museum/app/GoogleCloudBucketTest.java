package com.museum.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.museum.data.Game;
import com.museum.services.GoogleCloudBucketService;
import com.museum.services.JacksonService;

import static com.museum.config.Constants.*;

public class GoogleCloudBucketTest {
    @Test
    public void testAvailableConfiguration() {
        List<String> prova = GoogleCloudBucketService.getAvailableConfigurationList();
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        assertNotNull(prova);
    }

    @Test
    public void testUpdateAvailableConfiguration() {
        GoogleCloudBucketService.updateAvailableConfigurationList("test");
        assertTrue(GoogleCloudBucketService.getAvailableConfigurationList().contains("test"));
    }

    @Test
    public void testLoadGame() {
        if (GoogleCloudBucketService.getAvailableConfigurationList().contains("test")) {
            Game game = GoogleCloudBucketService.loadGame("test");
            assertNotNull(game);
        }
    }

    @Test
    public void testSaveGame() {
        Game game = JacksonService.deserializeGame(JSON_CONFIGURATION_PATH);
        try {
            GoogleCloudBucketService.saveGame("test", game);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(GoogleCloudBucketService.getAvailableConfigurationList().contains("test"));
    }
}
