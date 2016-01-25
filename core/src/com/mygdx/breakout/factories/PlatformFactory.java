package com.mygdx.breakout.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Dubforce on 1/25/2016.
 */
public class PlatformFactory {
    public static Entity horizontal(PooledEngine engine, World world, RectangleMapObject spawn) {
        return engine.createEntity();
    }

    public static Entity vertical(PooledEngine engine, World world, RectangleMapObject spawn) {
        return engine.createEntity();
    }

    public static Entity normal(PooledEngine engine, World world, RectangleMapObject spawn) {

    }
}
