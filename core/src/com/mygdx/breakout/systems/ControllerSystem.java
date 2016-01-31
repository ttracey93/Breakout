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
import com.mygdx.breakout.components.PlayerComponent;
import com.mygdx.breakout.components.TransformComponent;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class ControllerSystem extends IteratingSystem {
    private ComponentMapper<ControllerComponent> cm = ComponentMapper.getFor(ControllerComponent.class);
    private ComponentMapper<PlayerComponent> pm = ComponentMapper.getFor(PlayerComponent.class);

    public ControllerSystem() {
        super(Family.all(ControllerComponent.class, PlayerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
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

        controller.action = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }
}
