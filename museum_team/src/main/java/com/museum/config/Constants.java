package com.museum.config;

public class Constants {
    /*
     * Numero di righe della matrice contenente le stanze
     */
    public static final int ROWS_NUM = 4;

    /*
     * Numero di colonne della matrice contenente le stanze
     */
    public static final int COLUMNS_NUM = 3;

    /*
     * Coordinata x della stanza di entrata
     */
    public static final int ENTRY_X_COORD = 1;

    /*
     * Coordinata y della stanza di entrata
     */
    public static final int ENTRY_Y_COORD = 0;

    /*
     * Uri del json di configurazione iniziale
     */
    public static final String JSON_CONFIGURATION_PATH = "museum_team/src/main/resources/com/museum/app/json/configuration.json";

    /*
     * Uri del json con le credenziali di cloud buckets
     */
    public static final String CREDENTIALS_PATH = "museumteam-credential.json";

    /*
     * project-id su GCB
     */
    public static final String PROJECT_ID = "museumteam";

    /*
     * nome del bucket su GCB
     */
    public static final String BUCKET_NAME = "available-configuration";

    /*
     * nome del blob che lista le configurazioni salvate
     */
    public static final String BLOB_AVAILABLE_CONFIGURATION = "available-configuration.txt";

    /*
     * path in cui scaricare i file di configurazioni
     */
    public static final String DOWNLOAD_PATH = "museum_team/src/main/resources/com/museum/app/json/downloaded_file.json";

    /*
     * path in cui salvare un file serializzato
     */
    public static final String SERIALIZE_PATH = "museum_team/src/main/resources/com/museum/app/json/serialize_file.json";

    /*
     * path in cui salvare la lista di configurazioni salvate
     */
    public static final String AVAILABLE_CONFIGURATION_LIST_PATH = "museum_team/src/main/resources/com/museum/app/json/available_configuration_file.txt";
}
