package com.museum.data;

import com.museum.enumerator.ArtworkType;
import com.museum.enumerator.StealingToolType;

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

    /*
     * determina se lo stealing tool può rubare l'opera
     */
    public boolean canBeTaken(StealingToolType stealingTool)
    {
        if(stealingTool == StealingToolType.HAMMER && this._type == ArtworkType.SCULPTURE)
        {
            return true;
        }

        if(stealingTool == StealingToolType.KNIFE && this._type == ArtworkType.PAINTING)
        {
            return true;
        }

        if(stealingTool == StealingToolType.LASER_CONTROLLER && this._type == ArtworkType.JEWELRY)
        {
            return true;
        }

        /*
         * in tutti gli altri casi
         */
        return false;
    }

    @Override
    public String toString() {
        return _name + " - €" + _value + " - " + _weight + "kg" + " - " + _type.name() + "\n";
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
