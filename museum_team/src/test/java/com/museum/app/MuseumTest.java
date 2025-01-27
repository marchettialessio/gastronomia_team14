package com.museum.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.museum.data.Museum;
import com.museum.data.Room;

public class MuseumTest {

    @Test
    public void testMuseum()
    {
        List<Room> list = new ArrayList<>();
        list.add(new Room("prova", 2, 3, false, null, null, new ArrayList<>()));
        Museum museum = new Museum(list);

        //controllo che la stanza sia nella posizione corretta della matrice
        assertTrue(museum.get_rooms_matrix()[3][2].get_name() == "prova");
    }
}
