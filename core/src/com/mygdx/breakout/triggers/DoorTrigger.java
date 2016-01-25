package com.mygdx.breakout.triggers;

import com.mygdx.breakout.screens.BreakoutScreen;

/**
 * Created by Dubforce on 1/24/2016.
 */
public class DoorTrigger implements Trigger {
    private BreakoutScreen breakoutScreen;
    private String level;

    public DoorTrigger(BreakoutScreen breakoutScreen, String level) {
        this.breakoutScreen = breakoutScreen;
        this.level = level;
    }

    @Override
    public void trigger() {
        breakoutScreen.setLevel(level);
    }

    @Override
    public void dispose() {

    }
}
