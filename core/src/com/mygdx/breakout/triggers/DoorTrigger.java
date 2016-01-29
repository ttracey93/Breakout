package com.mygdx.breakout.triggers;

import com.mygdx.breakout.Breakout;
import com.mygdx.breakout.screens.GameScreen;
import com.mygdx.breakout.screens.PlatformerScreen;

/**
 * Created by Dubforce on 1/24/2016.
 */
public class DoorTrigger implements Trigger {
    private Breakout breakout;
    private String level;

    public DoorTrigger(Breakout breakout, String level) {
        this.breakout = breakout;
        this.level = level;
    }

    @Override
    public void trigger() {
        breakout.setScreen(new PlatformerScreen(breakout, level));
    }

    @Override
    public void dispose() {

    }
}
