package com.museum.app;

import java.util.List;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/*
 * Controller per gestire la visualizzazione delle configurazioni disponibili
 */
public class SelectionController {
     @FXML
    private ListView<String> listView;

    private Consumer<String> onItemSelected;

    // Metodo per impostare gli elementi nella lista
    public void setItemList(List<String> items) {
        listView.getItems().setAll(items);
    }

    @FXML
    private void selectItem() {
        String selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null && onItemSelected != null) {
            onItemSelected.accept(selected);  // Ritorna il valore al controller principale
        }
    }

    // Metodo per impostare la callback di selezione
    public void setOnItemSelected(Consumer<String> callback) {
        this.onItemSelected = callback;
    }
}
