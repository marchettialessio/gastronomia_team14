package com.museum.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.museum.services.*;
import static com.museum.config.Constants.*;

//Dichiara a Jackson che questa classe ha un Serializer
@JsonSerialize(using = GameSerializer.class)
// Dichiara a Jackson che questa classe ha un Deserializer
@JsonDeserialize(using = GameDeserializer.class)
public class Game {

    Timer _timer;
    
    Player _player;

    Museum _museum;

    Guard _guard;

    /*
     * profitto parziale derivato dalle opere già portate all'entrata
     */
    int _currentProfit;

    /*
     * questo costruttore viene utilizzato quando si importa una configurazione esistente
     */
    public Game(Player _player, Museum _museum, Guard _guard, int _currentProfit) {
        this._player = _player;
        this._museum = _museum;
        this._guard = _guard;
        this._currentProfit = _currentProfit;
    }

    /*
     * questo costruttore viene utilizzato quando non avvio il game con una configurazione salvata
     */
    public Game(List<Room> roomsList) {
        this._museum = new Museum(roomsList);
        this._guard = GenerateRandomGuardPosition();
        this._currentProfit = 0;
        this._player = new Player(new ArrayList<>(), null, ENTRY_X_COORD, ENTRY_Y_COORD);
    }

    /*
     * metodo per generare la guardia in una posizione
     */
    private Guard GenerateRandomGuardPosition() {
        Random random = new Random();
        int x_coord = random.nextInt(2);
        int y_coord = random.nextInt(3);

        /*
         * la guardia non può essere già all'ingresso
         */
        if (x_coord == ENTRY_X_COORD) {
            while (y_coord == ENTRY_Y_COORD) {
                y_coord = random.nextInt(3);
            }
        }

        return new Guard(x_coord, y_coord);
    }

    public Player get_player() {
        return _player;
    }

    public Museum get_museum() {
        return _museum;
    }

    public Guard get_guard() {
        return _guard;
    }

    public int get_currentProfit() {
        return _currentProfit;
    }
}
