package com.mygdx.breakout.managers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.triggers.Trigger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dubforce on 1/24/2016.
 */
public class Destroyables {
    private static List<Body> destroyables;

    private static World world;

    public static void load() {
        destroyables = new ArrayList<Body>();
    }

    public static void destroy(Body body) {
        destroyables.add(body);
    }

    public static void update() {
        for(Body body : destroyables) {
            if(world != null) {
                world.destroyBody(body);
                body.setActive(false);
            }
        }

        destroyables.clear();
    }

    // util
    public static void setWorld(World world) {
        Destroyables.world = world;
    }
}
