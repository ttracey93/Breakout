package com.mygdx.breakout.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.PlatformComponent;
import com.mygdx.breakout.components.PlayerComponent;

/**
 * Created by Dubforce on 1/25/2016.
 */
public class PlatformSystem extends IteratingSystem {
    private ComponentMapper<PlatformComponent> pm = ComponentMapper.getFor(PlatformComponent.class);
    private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);

    public PlatformSystem() {
        super(Family.all(PlatformComponent.class, BodyComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlatformComponent pc = pm.get(entity);
        BodyComponent bc = bm.get(entity);

        if(pc.displacement != null && pc.moveSpeed != null) {
            Vector2 displacement = bc.body.getPosition().cpy().sub(pc.origin);

            if(displacement.y > pc.displacement.y || displacement.y < -pc.displacement.y) {
                pc.moveSpeed.y *= -1;
            }

            if (displacement.x > pc.displacement.x || displacement.x < -pc.displacement.x) {
                pc.moveSpeed.x *= -1;
            }

            bc.body.setLinearVelocity(pc.moveSpeed);
        }
    }
}
