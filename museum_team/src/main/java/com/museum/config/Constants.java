package com.museum.config;

public class Constants {
    /*
     * Massimo peso che il palyer può portare
     */
    public static final int MAX_WEIGHT = 10;

    /*
     * Obbiettivo in denaro che il player deve raggiungere per vincere la partita
     */
    public static final int WIN_GOAL = 12500000;

    /*
     * Tempo che passa per generare una nuova posizione della guardia
     */
    public static final int GUARD_TIME = 8;

    /*
     * Tempo massimo che il giocatore ha per vincere
     */
    public static final int PLAYER_TIME = 240;

    /*
     * Tempo rimosso quando la guardia ti trova
     */
    public static final int WASTED_TIME = 5;

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
    public static final String CREDENTIALS_PATH = "";//museumteam-credential.json";

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
    public static final String BLOB_AVAILABLE_CONFIGURATION = "available-configuration2.txt";

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

    /*
     * path fxml selezione
     */
    public static final String FXML_SELEZIONE = "view/selection.fxml";

    /*
     * path fxml museo
     */
    public static final String FXML_MUSEUM = "view/museum.fxml";

    /*
     * dimensione lato di una stanza in pixel
     */
    public static final int ROOM_DIMENSION = 120;

    /*
     * dimensione lato di una immagine in pixel
     */
    public static final int IMAGE_DIMENSION = 35;

    /*
     * dimensione padding finestra
     */
    public static final int WINDOW_PADDING = 25;
}
