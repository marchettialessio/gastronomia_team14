package com.museum.app;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.museum.data.Artwork;
import com.museum.data.Game;
import com.museum.data.Player;
import com.museum.enumerator.DirectionType;
import com.museum.enumerator.StealingToolType;
import com.museum.services.GoogleCloudBucketService;
import com.museum.services.JacksonService;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static com.museum.config.Constants.*;

/*
 * Questa classe gestisce l'interazione dell'utente con la grafica.
 * Si occupa anche di caricare la configurazione di gioco iniziale o far scegliere all'utente quale configurazione di gioco scaricare
 */
public class Controller {
    @FXML
    TextField commandField;

    @FXML
    AnchorPane root;

    @FXML
    ImageView imageView;

    String selectedConfiguration;

    Game game;

    /*
     * Metodo di inizializzazione chiamato automaticamente dopo il caricamento
     * dell'FXML
     */
    @FXML
    public void initialize() {
        if (imageView != null)
            root.getChildren().remove(imageView);
        imageView = null;

        setConfiguration(false);

        showPlayer();

        // TODO: (è roba facoltativa) ora bisogna visualizzare la guardia e settare il
        // timer

    }

    /*
     * metodo che gestisce i comandi dell'utente, chiamato quando preme invio sulla
     * textfield
     */
    @FXML
    private void handleCommand() {
        final List<String> availableCommands = Arrays.asList("north", "south", "east", "west", "take", "use", "back",
                "look", "save", "exit", "load", "take partial profit");
        /*
         * recupero il testo inserito dall'utente
         */
        String command = commandField.getText();

        if (availableCommands.contains(command.trim().toLowerCase())) {
            /*
             * il comando è corretto
             */
            Player player = game.get_player();
            int x_coord = player.get_x_coord();
            int y_coord = player.get_y_coord();

            switch (command) {
                case "north":

                    /*
                     * controllo se nella stanza in cui è il player si può andare in su
                     */
                    if (game.get_museum().get_rooms_matrix()[y_coord][x_coord].get_allowedDirections()
                            .contains(DirectionType.TOP)) {
                        /*
                         * si può fare il movimento
                         */
                        player.set_y_coord(y_coord - 1);
                        showPlayer();
                    } else {
                        /*
                         * non si può fare il movimento
                         */
                        showNotAllowedMovementAlert();
                    }

                    break;
                case "south":

                    /*
                     * controllo se nella stanza in cui è il player si può andare in giù
                     */
                    if (game.get_museum().get_rooms_matrix()[y_coord][x_coord].get_allowedDirections()
                            .contains(DirectionType.BOTTOM)) {
                        /*
                         * si può fare il movimento
                         */
                        player.set_y_coord(y_coord + 1);
                        showPlayer();
                    } else {
                        /*
                         * non si può fare il movimento
                         */
                        showNotAllowedMovementAlert();
                    }

                    break;
                case "east":
                    x_coord = player.get_x_coord();
                    y_coord = player.get_y_coord();
                    /*
                     * controllo se nella stanza in cui è il player si può andare a destra
                     */
                    if (game.get_museum().get_rooms_matrix()[y_coord][x_coord].get_allowedDirections()
                            .contains(DirectionType.RIGHT)) {
                        /*
                         * si può fare il movimento
                         */
                        player.set_x_coord(x_coord + 1);
                        showPlayer();
                    } else {
                        /*
                         * non si può fare il movimento
                         */
                        showNotAllowedMovementAlert();
                    }

                    break;
                case "west":
                    x_coord = player.get_x_coord();
                    y_coord = player.get_y_coord();
                    /*
                     * controllo se nella stanza in cui è il player si può andare a destra
                     */
                    if (game.get_museum().get_rooms_matrix()[y_coord][x_coord].get_allowedDirections()
                            .contains(DirectionType.LEFT)) {
                        /*
                         * si può fare il movimento
                         */
                        player.set_x_coord(x_coord - 1);
                        showPlayer();
                    } else {
                        /*
                         * non si può fare il movimento
                         */
                        showNotAllowedMovementAlert();
                    }

                    break;
                case "take":
                    /*
                     * controllo se la stanza ha opere o stealing tools
                     */
                    List<Artwork> artworksList = game.get_museum().get_rooms_matrix()[y_coord][x_coord]
                            .get_artworksList();
                    StealingToolType stealingTool = game.get_museum().get_rooms_matrix()[y_coord][x_coord]
                            .get_stealingTool();

                    if (stealingTool != null || !artworksList.isEmpty()) {
                        /*
                         * caso in cui sono presenti
                         */
                        lookTakeCommandWindow(artworksList, stealingTool, true);
                    } else {
                        /*
                         * caso in cui non sono presenti
                         */
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Attenzione");
                        alert.setHeaderText("Non sono presenti nè oggetti per rubare nè opere d'arte");
                        alert.show();
                    }

                    break;
                case "back":

                    break;
                case "look":
                    /*
                     * controllo se la stanza ha opere o stealing tools
                     */
                    artworksList = game.get_museum().get_rooms_matrix()[y_coord][x_coord]
                            .get_artworksList();
                    stealingTool = game.get_museum().get_rooms_matrix()[y_coord][x_coord]
                            .get_stealingTool();

                    if (stealingTool != null || !artworksList.isEmpty()) {
                        /*
                         * caso in cui sono presenti
                         */
                        lookTakeCommandWindow(artworksList, stealingTool, false);
                    } else {
                        /*
                         * caso in cui non sono presenti
                         */
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Attenzione");
                        alert.setHeaderText("Non sono presenti nè oggetti per rubare nè opere d'arte");
                        alert.show();
                    }

                    break;
                case "save":
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Salvataggio");
                    dialog.setHeaderText("Inserire il nome della partita");
                    dialog.setContentText("Inserisci nome:");

                    // Mostra la finestra di dialogo e attende l'input dell'utente
                    Optional<String> resultDialog = dialog.showAndWait();

                    // Controlla se l'utente ha inserito un valore
                    resultDialog.ifPresent(name -> {
                        if (name != "") {
                            GoogleCloudBucketService.saveGame(name, game);
                        }
                    });

                    break;
                case "exit":
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setTitle("Attenzione");
                    alert2.setHeaderText("Sei sicuro di voler chiudere l'applicazione?");
                    alert2.setContentText("Scegli un'opzione:");
                    alert2.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

                    Optional<ButtonType> result = alert2.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.YES) {
                        // L'utente ha scelto "Sì"
                        Platform.exit();
                    }

                    break;
                case "load":
                    setConfiguration(true);
                    break;
                case "take partial profit":

                    if (game.get_museum().get_rooms_matrix()[y_coord][x_coord].get_isEntry()) {
                        /*
                         * sono nella stanza entrata
                         */
                        game.set_currentProfit(player.takePartialProfit());
                        if (game.get_currentProfit() >= WIN_GOAL) {
                            /*
                             * Il giocatore ha vinto
                             */
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("CONGRATULAZIONI!");
                            alert.setHeaderText("HAI VINTO!");
                            alert.showAndWait();

                            alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                            alert2.setTitle("Attenzione");
                            alert2.setHeaderText("Vuoi chiudere l'applicazione (Sì) o continuare (No)?");
                            alert2.setContentText("Scegli un'opzione:");
                            alert2.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

                            result = alert2.showAndWait();

                            if (result.isPresent() && result.get() == ButtonType.YES) {
                                // L'utente ha scelto "Sì"
                                Platform.exit();
                            } else {
                                // L'utente ha scelto "No" o ha chiuso la finestra
                                initialize();
                            }
                        } else {
                            /*
                             * il giocatore non ha vinto
                             */
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Attenzione");
                            alert.setHeaderText("Il tuo profitto è di €" + String.valueOf(game.get_currentProfit())
                                    + "\nL'obbiettivo è di €" + String.valueOf(WIN_GOAL));
                            alert.show();
                        }
                    } else {
                        /*
                         * non sono nella stanza di entrata
                         */
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Attenzione");
                        alert.setHeaderText("Devi recarti all'entrata del museo per ottenere del profitto");
                        alert.show();
                    }

                    break;

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Il comando inserito non è corretto");
            alert.show();
        }

        /*
         * pulisco il campo dopo avere inserito il comando
         */
        commandField.clear();
    }

    /*
     * metodo per caricare una configurazione salvata o quella base
     * il parametro serve a capire se si chiama il metodo da inizialize o dal
     * comando load
     */
    public void setConfiguration(boolean isFromLoadCommand) {
        List<String> availableConfiguration = GoogleCloudBucketService.getAvailableConfigurationList();
        /*
         * controllo se ho o meno configurazioni salvate
         * availableConfiguration == null si verifica se non riesco ad accedere al GCB
         */
        if (availableConfiguration.isEmpty() || availableConfiguration == null) {
            /*
             * caso in cui non ho configurazioni salvate.
             * carico la configurazione di default
             */
            game = JacksonService.deserializeGame(JSON_CONFIGURATION_PATH);
        } else {
            /*
             * caso in cui ho configurazioni salvate
             */

            boolean loadNewConfiguration;

            if (isFromLoadCommand)
                loadNewConfiguration = true;
            else
                loadNewConfiguration = showConfirmationAlert();

            if (loadNewConfiguration) {
                /*
                 * L'utente ora deve scegliere quale configurazione caricare
                 */
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_SELEZIONE));
                    Stage stage = new Stage();

                    stage.setScene(new Scene(loader.load()));
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UNDECORATED);

                    stage.initModality(Modality.APPLICATION_MODAL); // Blocca la finestra principale finché non si
                                                                    // chiude

                    SelectionController selectionController = loader.getController();

                    // Passa la lista di elementi al secondo controller
                    selectionController.setItemList(availableConfiguration);
                    /*
                     * Callback per ricevere il valore selezionato
                     */
                    selectionController.setOnItemSelected(item -> {
                        selectedConfiguration = item;
                        stage.close();
                    });

                    stage.setTitle("Seleziona un elemento");
                    stage.showAndWait(); // Aspetta la chiusura della finestra di selezione

                    game = GoogleCloudBucketService.loadGame(selectedConfiguration);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                if (isFromLoadCommand) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Attenzione");
                    alert.setHeaderText("non hai configurazioni salvate");
                    alert.show();
                } else {
                    /*
                     * L'utente vuole la configurazione base
                     */
                    game = JacksonService.deserializeGame(JSON_CONFIGURATION_PATH);

                }
            }

        }
    }

    /*
     * metodo per visualizzare un player sulla mappa
     */
    public void showPlayer() {
        /*
         * va creata solo la prima volta
         */
        if (imageView == null) {
            /*
             * Carica l'immagine dalla cartella delle risorse
             */
            Image image = new Image(getClass().getResourceAsStream("img/player.png"));
            imageView = new ImageView(image);

            /*
             * Imposta le dimensioni desiderate dell'immagine
             */
            imageView.setFitWidth(IMAGE_DIMENSION);
            imageView.setFitHeight(IMAGE_DIMENSION);

            root.getChildren().add(imageView);

        }

        /*
         * Calcola le coordinate per centrare l'immagine
         */
        double centerX = WINDOW_PADDING + ((ROOM_DIMENSION - IMAGE_DIMENSION) / 2)
                + game.get_player().get_x_coord() * ROOM_DIMENSION;
        double centerY = WINDOW_PADDING + ((ROOM_DIMENSION - IMAGE_DIMENSION) / 2)
                + game.get_player().get_y_coord() * ROOM_DIMENSION;

        imageView.setLayoutX(centerX);
        imageView.setLayoutY(centerY);

    }

    public boolean showConfirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Caricare partita");
        alert.setHeaderText("Vuoi caricare una vecchia partita?");
        alert.setContentText("Scegli un'opzione:");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        /*
         * Mostra la finestra di dialogo e attende la risposta dell'utente
         */
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            // L'utente ha scelto "Sì"
            return true;
        } else {
            // L'utente ha scelto "No" o ha chiuso la finestra
            return false;
        }
    }

    /*
     * metodo chiamato con i comandi look o take
     */
    public void lookTakeCommandWindow(List<Artwork> artworksList, StealingToolType stealingTool,
            boolean selectionMode) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/lookTakeCommandWindow.fxml"));
            Parent root = loader.load();

            LookTakeCommandController loadCommandController = loader.getController();
            loadCommandController.initData(artworksList, stealingTool == null ? "" : stealingTool.name(),
                    selectionMode);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(selectionMode ? "Seleziona un oggetto" : "Visualizza oggetti");
            stage.setScene(new Scene(root));
            stage.setResizable(false);

            if (selectionMode) {
                stage.initStyle(StageStyle.UNDECORATED);
            }

            stage.showAndWait();

            if (selectionMode) {
                /*
                 * indice dell'elemento selezionato
                 */
                int selected = loadCommandController.getSelectedIndex();
                Player player = game.get_player();

                if (selected == artworksList.size()) {
                    /*
                     * ho selezionato stealingtool
                     */

                    StealingToolType stealingToolInventory = player.get_stealingToolInventory();
                    if (stealingToolInventory != null) {
                        /*
                         * ne ho già uno, quindi lascio il mio nella stanza e prendo quello nuovo
                         */
                        player.set_stealingToolInventory(stealingTool);

                        game.get_museum().get_rooms_matrix()[player.get_y_coord()][player.get_x_coord()]
                                .set_stealingTool(stealingToolInventory);
                    } else {
                        /*
                         * non ne ho uno, prendo quello della stanza
                         */
                        player.set_stealingToolInventory(stealingTool);
                        game.get_museum().get_rooms_matrix()[player.get_y_coord()][player.get_x_coord()]
                                .set_stealingTool(null);
                    }
                } else {
                    /*
                     * ho selezionato un artwork
                     */
                    Artwork selectedArtwork = artworksList.get(selected);

                    if (selectedArtwork.canBeTaken(player.get_stealingToolInventory())) {
                        /*
                         * ho lo stealing tool corretto per questa artwork
                         */
                        if (selectedArtwork.get_weight() + player.calculateInventoryWeight() > MAX_WEIGHT) {
                            /*
                             * Non posso aggiungere l'artwork all'inventario, supero il peso limite
                             */
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Errore");
                            alert.setHeaderText(
                                    "Non puoi superare il peso massimo di 10 kg, devi depositare l'inventario all'ingresso!");
                            alert.show();
                        } else {
                            /*
                             * posso aggiungere all'inventario
                             */
                            artworksList.remove(selected);
                            player.get_artworkInventory().add(selectedArtwork);
                        }
                    } else {
                        /*
                         * non ho lo stealing tool corretto
                         */
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Errore");
                        alert.setHeaderText(
                                "Non hai l'oggetto corretto per rubare questa opera!");
                        alert.show();
                    }

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNotAllowedMovementAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Movimento non consentito");
        alert.show();
    }
}
