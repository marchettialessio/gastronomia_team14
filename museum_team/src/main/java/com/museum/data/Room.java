package com.museum.data;

import java.util.List;

import com.museum.enumerator.*;;

/*
 * classe che rappresenta una stanza del museo
 */
public class Room {
    /*
     * nome della stanza
     */
    String _name;

    /*
     * coordinata x della stanza
     */
    int _x_val;
    /*
     * coordinata y della stanza
     */
    int _y_val;
    /*
     * variabile booleana che indica se la matrice è l'entrata del museo
     */
    boolean _isEntry;

    /*
     * in questa lista sono presenti le possibili direzioni che si possono prendere
     * a partire da questa stanza
     */
    List<DirectionType> _allowedDirections;

    /*
     * indica se è presente un oggetto per rubare
     */
    StealingToolType _stealingTool;


    /*
     * lista delle opere presenti nella stanza
     */
    List<Artwork> _artworksList;

    public Room(String _name, int _x_val, int _y_val, boolean _isEntry, List<DirectionType> _allowedDirections,
            StealingToolType _stealingTool, List<Artwork> _artworksList) {
        this._name = _name;
        this._x_val = _x_val;
        this._y_val = _y_val;
        this._isEntry = _isEntry;
        this._allowedDirections = _allowedDirections;
        this._stealingTool = _stealingTool;
        this._artworksList = _artworksList;
    }

    public String get_name() {
        return _name;
    }

    public int get_x_val() {
        return _x_val;
    }

    public int get_y_val() {
        return _y_val;
    }

    public boolean get_isEntry() {
        return _isEntry;
    }

    public List<DirectionType> get_allowedDirections() {
        return _allowedDirections;
    }

    public StealingToolType get_stealingTool() {
        return _stealingTool;
    }

    public List<Artwork> get_artworksList() {
        return _artworksList;
    }

    public void set_stealingTool(StealingToolType _stealingTool) {
        this._stealingTool = _stealingTool;
    }
}