package com.museum.data;

/*
 * classe che rappresenta una guardia del museo
 */
public class Guard {
    /*
     * coordinata x della guardia
     */
    int _x_coord;

    /*
     * coordinata y della guardia
     */
    int _y_coord;

    public Guard(int _x_coord, int _y_coord) {
        this._x_coord = _x_coord;
        this._y_coord = _y_coord;
    }

    public int get_x_coord() {
        return _x_coord;
    }

    public int get_y_coord() {
        return _y_coord;
    }

    public void set_x_coord(int _x_coord) {
        this._x_coord = _x_coord;
    }

    public void set_y_coord(int _y_coord) {
        this._y_coord = _y_coord;
    }
}
