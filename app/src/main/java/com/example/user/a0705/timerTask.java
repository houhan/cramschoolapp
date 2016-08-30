package com.example.user.a0705;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2016/8/29.
 */
public class timerTask extends TimerTask {

    public void run()
    {
        Timer timer = new Timer(true);
        timer.schedule(new timerTask(), 0, 1000); //Do Something
    }
}
