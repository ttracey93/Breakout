package com.mygdx.breakout.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.breakout.components.PlatformComponent;

/**
 * Created by Dubforce on 1/25/2016.
 */
public class PlatformSystem extends IteratingSystem {
    public PlatformSystem() {
        super(Family.all(PlatformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
