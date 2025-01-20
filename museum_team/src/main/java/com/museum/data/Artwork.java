package com.museum.data;

import com.museum.enumerator.ArtworkType;

/*
 * classe che rappresenta un opera presente nel museo
 */
public class Artwork {
    /*
     * nome dell'opera d'arte
     */
    String _name;

    /*
     * valore in euro dell'opera
     */
    int _value;

    /*
     * peso dell'opera
     */
    int _weight;

    /*
     * tipo dell'opera
     */
    ArtworkType _type;

    public Artwork(String _name, int _value, int _weight, ArtworkType _type) {
        this._name = _name;
        this._value = _value;
        this._weight = _weight;
        this._type = _type;
    }

    public String get_name() {
        return _name;
    }

    public int get_value() {
        return _value;
    }

    public int get_weight() {
        return _weight;
    }

    public ArtworkType get_type() {
        return _type;
    }
}
