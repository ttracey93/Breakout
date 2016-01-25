package com.mygdx.breakout.managers;

import com.badlogic.ashley.core.Entity;
import com.mygdx.breakout.world.GameState;
import com.mygdx.breakout.world.Level;

/**
 * Created by Dubforce on 1/24/2016.
 */
public class Utils {
    public static Level level;
    public static GameState state;
    public static Entity player;

    public static Level getLevel() {
        return level;
    }

    public static void setLevel(Level level) {
        Utils.level = level;
    }

    public static GameState getState() {
        return state;
    }

    public static void setState(GameState state) {
        Utils.state = state;
    }

    public static Entity getPlayer() {
        return player;
    }

    public static void setPlayer(Entity player) {
        Utils.player = player;
    }
}
