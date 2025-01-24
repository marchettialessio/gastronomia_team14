package com.museum.data;

import com.museum.app.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import static com.museum.config.Constants.*;

public class Timer {

    /*
     * tempo rimanente in secondi
     */
    private int timeRemaining;

    /*
     * il timeline per gestire il decremento del tempo
     */
    private final Timeline timeline;

    /*
     * il timeline per gestire il movimento della guardia
     */
    private final Timeline guardTimeline;

    /*
     * il controller principale
     */
    private final Controller controller;

    public Timer(int startTime, Controller controller) {
        this.timeRemaining = startTime;
        this.controller = controller;

        /*
         * Crea un Timeline che si attiva ogni secondo per decrementare il tempo sulla
         * UI
         */
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> tick()));

        /*
         * Crea un Timeline che si attiva ogni GUARD_TIME per muovere la guardia
         */
        this.guardTimeline = new Timeline(new KeyFrame(Duration.seconds(GUARD_TIME), e -> controller.updateGuard()));
        this.timeline.setCycleCount(Timeline.INDEFINITE); // Il timer si ripete indefinitamente
        this.guardTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    /*
     * Metodo chiamato ogni secondo per aggiornare il timer
     */
    private void tick() {
        if (timeRemaining > 0) {
            timeRemaining--;
            controller.updateTimer(timeRemaining); // chiama il metodo nel controller
        } else {
            timeline.stop(); // ferma il timer quando il tempo è esaurito
            guardTimeline.stop();
            controller.timerFinished(); // notifica il controller che il timer è finito
        }
    }

    /*
     * Metodo per avviare il timer
     */
    public void start() {
        timeline.play();
        guardTimeline.play();
    }

    /*
     * Metodo per fermare il timer
     */
    public void stop() {
        timeline.stop();
        guardTimeline.stop();
    }

    /*
     * Metodo per decrementare il tempo di un certo valore
     * (usato quando un player incrocia una guardia)
     */
    public void decrement(int seconds) {
        if (seconds > 0) {
            timeRemaining = Math.max(0, timeRemaining - seconds);
            controller.updateTimer(timeRemaining);
            if (timeRemaining == 0) {
                timeline.stop(); // ferma il timer quando il tempo è esaurito
                controller.timerFinished(); // notifica il controller che il timer è finito
            }
        }
    }

    // Metodo per ottenere il tempo rimanente
    public int getTimeRemaining() {
        return timeRemaining;
    }
}
