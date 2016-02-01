package com.mygdx.breakout.pickups;

import com.badlogic.ashley.core.Entity;

/**
 * Created by Dubforce on 1/31/2016.
 */
public abstract class Pickup {
    public abstract void pickup(Entity target, Entity self);
}
