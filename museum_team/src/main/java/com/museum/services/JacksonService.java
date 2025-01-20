package com.museum.services;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.museum.data.Game;

/*
 * classe per operazioni di serializzazione e deserializazione JSON
 */
public class JacksonService {

    /*
     * Metodo per serializzare il game.
     * Serve quando voglio salvare una partita su google cloud buckets
     */
    public static void serializeGame(Game game, String fileName) {
        File f;
        FileWriter fw = null;
        try {
            // serve a mappare un oggetto in JSON
            ObjectMapper om = new ObjectMapper();

            // file che voglio serializzare
            f = new File(fileName);

            // Classe per la scrittura su file
            fw = new FileWriter(f);
            
            // Scrittura sul file
            om.writeValue(fw, game);

        } catch (IOException e) {

        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * Metodo per deserializzare il game.
     * Può essere chiamato:
     * - quando non carico partite salvate e uso la configurazione iniziale
     * - quando carico una partita salvata e devo deserializzare un json da google
     * cloud buckets
     */
    public static Game deserializeGame(String fileName) {
        Game game = null;
        File f;
        FileReader fr = null;

        try {
            // serve a mappare un oggetto in JSON
            ObjectMapper om = new ObjectMapper();

            // file che voglio deserializzare
            f = new File(fileName);

            // Classe per la lettura da file
            fr = new FileReader(f);

            // Lettura da file
            game = om.readValue(fr, Game.class);

        } catch (IOException e) {
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return game;
    }
}
