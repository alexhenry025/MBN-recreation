/*
 * This is the main launcher class
 * 2019 - Ghanem & Usman
 * Megaman Battle Network 6
 */

package com.mygdx.game;
import com.mygdx.game.Maps;

import java.util.Timer;
import java.util.TimerTask;

public class Fadeout_timer {
    Timer elapsed;

    public Fadeout_timer(int seconds){
        elapsed = new Timer();
        elapsed.schedule(new Reminder(), seconds * 1000);
    }

    class Reminder extends TimerTask{
        public void run(){
            Maps.time = 1; // will set the time to 1
            Maps.fade_out = false; // will stop the animation
        }
    }
}
