package com.mygdx.breakout.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.ControllerComponent;
import com.mygdx.breakout.components.TransformComponent;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class ControllerSystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<BodyComponent> bm;
    private ComponentMapper<ControllerComponent> cm;

    public ControllerSystem() {
        super(Family.all(TransformComponent.class, BodyComponent.class, ControllerComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        bm = ComponentMapper.getFor(BodyComponent.class);
        cm = ComponentMapper.getFor(ControllerComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        BodyComponent body = bm.get(entity);
        ControllerComponent controller = cm.get(entity);

        // TODO: Implement controller usage

        // Controller pad = Controllers.getControllers().first();
        controller.leftAxis.set(0, 0);
        controller.rightAxis.set(0, 0);

        // Left stick
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            controller.leftAxis.x -= 1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            controller.leftAxis.x += 1;
        }

        // Right stick
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            controller.rightAxis.x -= 1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            controller.rightAxis.x += 1;
        }

        // set linear velocity
        body.body.setLinearVelocity(controller.leftAxis.x * body.moveSpeed.x, controller.leftAxis.y * body.moveSpeed.y);
    }
}
