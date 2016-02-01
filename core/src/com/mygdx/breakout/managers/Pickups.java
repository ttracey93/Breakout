package com.mygdx.breakout.managers;

import com.badlogic.ashley.core.Entity;
import com.mygdx.breakout.pickups.Pickup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dubforce on 1/31/2016.
 */
public class Pickups {
    private static final Map<Entity, Pickup> pickups = new HashMap<Entity, Pickup>();

    public static Pickup get(Entity pickup) {
        return pickups.get(pickup);
    }

    public static void put(Entity e, Pickup p) {
        pickups.put(e, p);
    }

    public static void activate(Entity e, Entity target) {
        Pickup pickup = pickups.get(e);

        if(pickup != null) {
            pickup.pickup(target, e);
            //Destroyables.destroy();
        }
    }

    public static void remove(Entity e) {
        pickups.remove(e);
    }
}
