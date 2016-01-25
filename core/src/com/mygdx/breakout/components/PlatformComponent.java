package com.mygdx.breakout.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dubforce on 1/25/2016.
 */
public class PlatformComponent implements Component {
    public Vector2 origin;
    public Vector2 moveSpeed;
    public Vector2 displacement;
}
