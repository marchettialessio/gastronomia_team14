package com.museum.services;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.museum.data.Artwork;
import com.museum.data.Game;
import com.museum.data.Guard;
import com.museum.data.Museum;
import com.museum.data.Player;
import com.museum.data.Room;
import com.museum.data.Timer;
import com.museum.enumerator.DirectionType;

import static com.museum.config.Constants.*;

public class GameSerializer extends JsonSerializer<Game> {

    /*
     * metodo che effettua la serializzazione di game.
     * chiamato automaticamente quando scrivo sul file
     */
    @Override
    public void serialize(Game game, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Player _player = game.get_player();
        Museum _museum = game.get_museum();
        Guard _guard = game.get_guard();
        int _currentProfit = game.get_currentProfit();
        Timer _timer = game.get_timer();

        /*
         * inizio json
         */
        gen.writeStartObject();

        /*
         * Inizio oggetto player
         */
        gen.writeFieldName("player");

        gen.writeStartObject();

        gen.writeFieldName("x_coord");
        gen.writeNumber(_player.get_x_coord());

        gen.writeFieldName("y_coord");
        gen.writeNumber(_player.get_y_coord());

        gen.writeFieldName("stealingToolInventory");
        if (_player.get_stealingToolInventory() != null) {
            gen.writeString(_player.get_stealingToolInventory().name());
        } else {
            gen.writeNull();
        }

        gen.writeFieldName("artworkInventory");
        gen.writeStartArray();

        for (Artwork artWrk : _player.get_artworkInventory()) {
            gen.writeStartObject();

            gen.writeFieldName("type");
            gen.writeString(artWrk.get_type().name());

            gen.writeFieldName("name");
            gen.writeString(artWrk.get_name());

            gen.writeFieldName("value");
            gen.writeNumber(artWrk.get_value());

            gen.writeFieldName("weight");
            gen.writeNumber(artWrk.get_weight());

            gen.writeEndObject();
        }

        /*
         * fine oggetto player
         */
        gen.writeEndArray();
        gen.writeEndObject();

        /*
         * Inizio oggetto guardia
         */
        gen.writeFieldName("guardia");

        gen.writeStartObject();

        gen.writeFieldName("x_coord");
        gen.writeNumber(_guard.get_x_coord());

        gen.writeFieldName("y_coord");
        gen.writeNumber(_guard.get_y_coord());

        /*
         * fine oggetto guardia
         */
        gen.writeEndObject();

        /*
         * timer
         */
        gen.writeFieldName("timeRemaining");
        gen.writeNumber(_timer.getTimeRemaining());

        /*
         * current profit
         */
        gen.writeFieldName("currentProfit");
        gen.writeNumber(_currentProfit);

        /*
         * Inizio lista stanze
         */
        gen.writeFieldName("roomsList");

        gen.writeStartArray();

        /*
         * scorro tutta la matrice con le stanze
         */
        for (int i = 0; i < ROWS_NUM; i++) {
            for (int j = 0; j < COLUMNS_NUM; j++) {
                Room room = _museum.get_rooms_matrix()[i][j];
                /*
                 * inizio oggetto stanza
                 */
                gen.writeStartObject();

                gen.writeFieldName("name");
                gen.writeString(room.get_name());

                gen.writeFieldName("x_val");
                gen.writeNumber(room.get_x_val());

                gen.writeFieldName("y_val");
                gen.writeNumber(room.get_y_val());

                gen.writeFieldName("isEntry");
                gen.writeBoolean(room.get_isEntry());

                gen.writeFieldName("allowedDirections");
                gen.writeStartArray();

                for (DirectionType direct : room.get_allowedDirections()) {
                    gen.writeString(direct.name());
                }
                gen.writeEndArray();

                gen.writeFieldName("artworksList");
                gen.writeStartArray();

                for (Artwork artWrk : room.get_artworksList()) {
                    gen.writeStartObject();

                    gen.writeFieldName("type");
                    gen.writeString(artWrk.get_type().name());

                    gen.writeFieldName("name");
                    gen.writeString(artWrk.get_name());

                    gen.writeFieldName("value");
                    gen.writeNumber(artWrk.get_value());

                    gen.writeFieldName("weight");
                    gen.writeNumber(artWrk.get_weight());

                    gen.writeEndObject();
                }
                gen.writeEndArray();

                gen.writeFieldName("stealingTool");
                if (room.get_stealingTool() != null) {
                    gen.writeString(room.get_stealingTool().name());
                } else {
                    gen.writeNull();
                }

                /*
                 * fine oggetto stanza
                 */
                gen.writeEndObject();
            }
        }

        /*
         * fine lista stanze
         */
        gen.writeEndArray();

        /*
         * fine json
         */
        gen.writeEndObject();
    }

}
