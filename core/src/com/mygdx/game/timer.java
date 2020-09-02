package com.mygdx.game;

import java.util.Timer;
import java.util.TimerTask;

public class timer {
    Timer elapsed;

    public timer(int seconds){
        elapsed = new Timer();
        elapsed.schedule(new Reminder(), seconds * 1000);
    }

    class Reminder extends TimerTask{
        public void run(){
            Levels.time = 1;
            Levels.test = false;
        }
    }
}
