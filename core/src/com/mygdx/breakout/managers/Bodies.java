package com.mygdx.breakout.managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by Dubforce on 1/24/2016.
 */
public class Bodies {
    private static final ObjectMap<Body, Entity> objectMap = new ObjectMap<Body, Entity>(6666666);

    public static void add(Body body, Entity entity) {
        objectMap.put(body, entity);
    }

    public static Entity get(Body body) {
        return objectMap.get(body);
    }

    public static void remove(Body body) {
        objectMap.remove(body);
    }
}