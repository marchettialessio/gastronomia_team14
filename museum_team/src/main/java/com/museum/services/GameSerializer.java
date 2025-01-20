package com.museum.services;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.museum.data.Game;

public class GameSerializer extends JsonSerializer<Game>
{

    @Override
    public void serialize(Game game, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        
    }
    
}
