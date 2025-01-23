package com.museum.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.museum.data.Game;

import static com.museum.config.Constants.*;


/*
 * Classe con metodi statici per interfacciarsi a google cloud buckets
 */
public class GoogleCloudBucketService {

    /*
     * metodo per avere i nomi dei file delle configurazioni disponibili
     */
    public static List<String> getAvailableConfigurationList() {
        try {
            /*
             * Autenticazione con Google Cloud
             */
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_PATH)))
                    .build()
                    .getService();

            /*
             * Recupera il file (Blob) dal bucket
             */
            Blob blob = storage.get(BUCKET_NAME, BLOB_AVAILABLE_CONFIGURATION);

            if (blob == null) {
                throw new FileNotFoundException("Il file non esiste nel bucket.");
            }

            /*
             * Leggi il contenuto del blob come stringa
             */
            String content = new String(blob.getContent(), StandardCharsets.UTF_8);

            /*
             * Dividi il contenuto in righe e restituiscilo come lista di stringhe
             */
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new StringReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.replaceFirst("\\.json$", ""));
            }
            return lines;

        } catch (IOException e) {
            return null;
        }
    }

    /*
     * metodo per aggiornare la lista delle configurazioni disponibili
     */
    public static void updateAvailableConfigurationList(String newBlobName) {
        List<String> availableConfiguration = GoogleCloudBucketService.getAvailableConfigurationList();
        /*
         * devo fare update solo se la configurazione non è già presente
         */
        if (!availableConfiguration.contains(newBlobName)) {
            availableConfiguration.add(newBlobName);

            /*
             * Creo il file con la lista delle configurazioni disponibili
             */
            try {
                Files.write(Paths.get(AVAILABLE_CONFIGURATION_LIST_PATH), availableConfiguration);
            } catch (IOException e) {
                System.err.println("Errore nella scrittura del file: " + e.getMessage());
            }

            try {
                Storage storage = StorageOptions.newBuilder()
                        .setCredentials(GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_PATH)))
                        .build()
                        .getService();

                /*
                 * Leggi il nuovo contenuto del file locale
                 */
                byte[] newFileContent = Files.readAllBytes(Paths.get(AVAILABLE_CONFIGURATION_LIST_PATH));

                /*
                 * Sovrascrivi il blob esistente nel bucket
                 */
                BlobId blobId = BlobId.of(BUCKET_NAME, BLOB_AVAILABLE_CONFIGURATION);
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

                storage.create(blobInfo, newFileContent);

                /*
                 * elimino il file temporaneo
                 */
                Files.delete(Path.of(AVAILABLE_CONFIGURATION_LIST_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * metodo per caricare la configurazione di un gioco salvata in json
     */
    public static Game loadGame(String blobName) {

        try {
            /*
             * Autenticazione con Google Cloud
             */
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_PATH)))
                    .build()
                    .getService();

            /*
             * ottengo il file
             */
            Blob blob = storage.get(BUCKET_NAME, blobName + ".json");
            if (blob == null) {
                throw new IOException("Il file non esiste nel bucket!");
            }

            /*
             * scarico il file
             */
            blob.downloadTo(Paths.get(DOWNLOAD_PATH));

            /*
             * deserializzo il file scaricato
             */
            Game game = JacksonService.deserializeGame(DOWNLOAD_PATH);

            /*
             * elimino il file temporaneo
             */
            Files.delete(Path.of(DOWNLOAD_PATH));

            return game;

        } catch (IOException e) {
            return null;
        }
    }

    /*
     * metodo per salvare un game in un blob
     */
    public static void saveGame(String configurationName, Game game) {
        try {
            /*
             * Autenticazione con Google Cloud
             */
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_PATH)))
                    .build()
                    .getService();

            /*
             * serializzo il game
             */
            JacksonService.serializeGame(game, SERIALIZE_PATH);

            /*
             * Leggi il contenuto del file locale
             */
            byte[] fileContent = Files.readAllBytes(Paths.get(SERIALIZE_PATH));

            /*
             * Carica il file come blob nel bucket
             */
            BlobId blobId = BlobId.of(BUCKET_NAME, configurationName + ".json");
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

            storage.create(blobInfo, fileContent);

            /*
             * aggiorno la lista con le configurazioni disponibili
             */
            GoogleCloudBucketService.updateAvailableConfigurationList(configurationName);

            /*
             * elimino il file temporaneo
             */
            Files.delete(Path.of(SERIALIZE_PATH));

        } catch (IOException e) {
        }
    }
}
