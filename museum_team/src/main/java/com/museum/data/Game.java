package com.museum.data;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.museum.services.*;

//Dichiara a Jackson che questa classe ha un Serializer
@JsonSerialize(using = GameSerializer.class)
//Dichiara a Jackson che questa classe ha un Deserializer
@JsonDeserialize(using = GameDeserializer.class)
public class Game {
    Timer _timer;
    Player _player;
    Museum _museum;
    Guard _guard;
    int _currentProfit;

    /*
     * questo costruttore viene utilizzato quando non avvio il game con una configurazione salvata
     */
    public Game(List<Room> roomsList){
        this._museum = new Museum(roomsList);
    }
}
