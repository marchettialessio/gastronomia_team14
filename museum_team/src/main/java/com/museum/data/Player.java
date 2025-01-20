package com.museum.data;

import java.util.List;

import com.museum.enumerator.StealingToolType;

public class Player {
    /*
     * opere momentaneamente possedute dal giocatore
     */
    List<Artwork> _artworkInventory;

    /*
     * oggetto per rubare momentaneamente posseduto dal giocatore
     */
    StealingToolType _stealingToolInventory;

    /*
     * coordinata x del giocatore
     */
    int _x_coord;

    /*
     * coordinata y del giocatore
     */
    int _y_coord;

    public Player(List<Artwork> _artworkInventory, StealingToolType _stealingToolInventory, int _x_coord,
            int _y_coord) {
        this._artworkInventory = _artworkInventory;
        this._stealingToolInventory = _stealingToolInventory;
        this._x_coord = _x_coord;
        this._y_coord = _y_coord;
    }

    public int get_x_coord() {
        return _x_coord;
    }

    public int get_y_coord() {
        return _y_coord;
    }

    public StealingToolType get_stealingToolInventory() {
        return _stealingToolInventory;
    }

    public List<Artwork> get_artworkInventory() {
        return _artworkInventory;
    }
}
