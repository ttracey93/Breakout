package com.mygdx.breakout.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.ControllerComponent;

/**
 * Created by Dubforce on 1/29/2016.
 */
public class MovementSystem extends IteratingSystem {
    public ComponentMapper<ControllerComponent> cm = ComponentMapper.getFor(ControllerComponent.class);
    public ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);

    public MovementSystem() {
        super(Family.all(ControllerComponent.class, BodyComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent body = bm.get(entity);
        ControllerComponent controller = cm.get(entity);

        // set linear velocity
        body.body.setLinearVelocity(controller.leftAxis.x * body.moveSpeed.x, body.body.getLinearVelocity().y);
        //body.body.applyForceToCenter(controller.leftAxis.x * body.moveSpeed.x, 0, false);
    }
}
