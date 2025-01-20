package com.museum.data;

import com.museum.enumerator.ArtworkType;

public class Artwork {
    String _name;
    int _value;
    int _weight;
    ArtworkType _type;

    public Artwork(String _name, int _value, int _weight, ArtworkType _type) {
        this._name = _name;
        this._value = _value;
        this._weight = _weight;
        this._type = _type;
    }
  
}
