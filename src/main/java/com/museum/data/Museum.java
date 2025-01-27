package com.museum.data;
import static com.museum.config.Constants.*;
import java.util.List;

/*
 * classe che rappresenta il museo
 */
public class Museum {
    /*
     * matrice che contiene le stanze mappate nelle loro coordinate
     */
    Room[][] _rooms_matrix;

    public Museum(List<Room> roomsList)
    {
        this._rooms_matrix = GenerateMatrix(roomsList);
    }

    /*
     * Genero la matrice che contiene le stanze, ognuna mappata nelle relative coordinate
     */
    private Room[][] GenerateMatrix(List<Room> roomsList)
    {
        Room[][] matrix = new Room[ROWS_NUM][COLUMNS_NUM]; 
        for(Room room : roomsList)
        {
            matrix[room.get_y_val()][room.get_x_val()] = room;
        }
        return matrix;
    }

    public Room[][] get_rooms_matrix() {
        return _rooms_matrix;
    }
}
