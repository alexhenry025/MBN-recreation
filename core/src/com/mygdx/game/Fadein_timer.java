/*
 * This is the main launcher class
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */

package com.mygdx.game;
import java.util.Timer;
import java.util.TimerTask;

public class Fadein_timer {
    Timer elapsed;

    public Fadein_timer(int seconds){
        elapsed = new Timer();
        elapsed.schedule(new Fadein_timer.Reminder(), seconds * 1000);
    }

    class Reminder extends TimerTask {
        public void run(){
            Levels.fade_in = false; // will stop the animation
        }
    }

}
