package com.museum.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.museum.data.Game;

import static com.museum.config.Constants.*;

public class GoogleCloudBucketService {

    public static Game LoadGame(String blobName) {
        /*
         * Autenticazione con Google Cloud
         */
        try {
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_PATH)))
                    .build()
                    .getService();

            Blob blob = storage.get(BUCKET_NAME, blobName);
            if (blob == null) {
                throw new IOException("Il file non esiste nel bucket!");
            }
            blob.downloadTo(Paths.get(DOWNLOAD_PATH));

            return JacksonService.deserializeGame(DOWNLOAD_PATH);

        } catch (IOException e) {
            return null;
        }

    }
}
