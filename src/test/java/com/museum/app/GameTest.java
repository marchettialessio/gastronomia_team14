package com.museum.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.museum.data.Game;
import com.museum.data.Room;
import com.museum.services.JacksonService;

import static com.museum.config.Constants.*;

public class GameTest {
    
    @Test 
    public void defaultConfigurationConstructor()
    {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("prova0-0", 0, 0, false, null, null, new ArrayList<>()));
        Game game = new Game(roomList);

        //controllo se la stanza è corretta
        assertEquals(game.get_museum().get_rooms_matrix()[0][0].get_name(), "prova0-0");

        //controllo se il player è corretto
        int expected_x = ENTRY_X_COORD;
        int expected_y = ENTRY_Y_COORD;
        assertTrue(game.get_player().get_x_coord() == expected_x && game.get_player().get_y_coord() == expected_y);

        //controllo profitto
        assertEquals(game.get_currentProfit(), 0);

        //controllo tempo rimanente
        assertEquals(game.get_timer().getTimeRemaining(), PLAYER_TIME);
    }

    @Test 
    public void ExistingConfigurationConstructor()
    {
        final String test1 = "src/test/testFiles/generalTest.json";
        Game game = JacksonService.deserializeGame(test1);

        //controllo se il player è corretto

        assertTrue(game.get_player().get_x_coord() == 2 && game.get_player().get_y_coord() == 3);

        //controllo inventario player
        assertEquals(game.get_player().get_artworkInventory().get(0).get_name(), "Natura morta con teschio");

        //controllo profitto
        assertEquals(game.get_currentProfit(), 0);

        //controllo tempo rimanente
        assertEquals(game.get_timer().getTimeRemaining(), 211);

        //controllo guardia
        assertTrue(game.get_guard().get_x_coord() == 2 && game.get_guard().get_y_coord() == 0);
    }

    @Test
    public void testUpdateRandomGuardPosition()
    {
        final String test1 = "src/test/testFiles/generalTest.json";
        Game game = JacksonService.deserializeGame(test1);
        game.UpdateRandomGuardPosition();
        //controllo le possibili posizioni i cui può essersi spostato dalla stanza di coordinate x=2 y=0
        int guardX = game.get_guard().get_x_coord();
        int guardY = game.get_guard().get_y_coord();
        assertTrue(guardX == 2 && guardY ==1 || guardX == 1 && guardY == 0);
    }
}
