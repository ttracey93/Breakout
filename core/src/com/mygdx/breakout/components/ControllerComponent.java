package com.mygdx.breakout.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class ControllerComponent implements Component {
    public final Vector2 leftAxis = new Vector2();
    public final Vector2 rightAxis = new Vector2();
}
