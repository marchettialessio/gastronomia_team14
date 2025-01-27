package com.museum.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.museum.data.Player;
import com.museum.services.JacksonService;

public class PlayerTest {
    
    @Test
    public void testTakePartialProfit()
    {
        final String test1 = "src/test/testFiles/generalTest.json";
        Player player = JacksonService.deserializeGame(test1).get_player();

        //verifico se il profitto calcolato è corretto
        assertEquals(player.takePartialProfit(), 5250000);
    }

    @Test
    public void testCalculateInventoryWeight()
    {
        final String test1 = "src/test/testFiles/generalTest.json";
        Player player = JacksonService.deserializeGame(test1).get_player();

        //verifico se il peso calcolato è corretto
        assertEquals(player.calculateInventoryWeight(), 6);
    }
}
