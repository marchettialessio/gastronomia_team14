package com.museum.app;

import java.util.List;

import com.museum.data.Artwork;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LookTakeCommandController {
    @FXML
    private ListView<String> listView;
    @FXML
    private Button btnSelect;

    private int selectedIndex;
    private boolean selectionMode;

    public void initData(List<Artwork> artworks, String tool, boolean selectionMode) {
        this.selectionMode = selectionMode;

        // Popola la ListView con opere d'arte e lo strumento
        listView.setItems(FXCollections.observableArrayList());
        for (Artwork artwork : artworks) {
            listView.getItems().add(artwork.toString());
        }

        if (tool != "") {
            listView.getItems().add(tool);
        }
        
        // Disabilita il pulsante di selezione in modalità sola visualizzazione
        btnSelect.setVisible(selectionMode);
    }

    @FXML
    private void handleSelection() {
        selectedIndex = listView.getSelectionModel().getSelectedIndex();
        Stage stage = (Stage) btnSelect.getScene().getWindow();
        stage.close();
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }
}
