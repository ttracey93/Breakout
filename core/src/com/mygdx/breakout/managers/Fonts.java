package com.mygdx.breakout.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Dubforce on 1/24/2016.
 */
public class Fonts {
    public static BitmapFont debug;

    public static void load() {
        debug = new BitmapFont();
        debug.setColor(Color.RED);
    }
}
