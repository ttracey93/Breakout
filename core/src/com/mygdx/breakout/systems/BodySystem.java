package com.mygdx.breakout.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.TransformComponent;
import com.mygdx.breakout.managers.Bodies;
import com.mygdx.breakout.util.IConversions;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class BodySystem extends IteratingSystem {
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<BodyComponent> bm;

    public BodySystem() {
        super(Family.all(TransformComponent.class, BodyComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        bm = ComponentMapper.getFor(BodyComponent.class);

        priority = 0;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        BodyComponent body = bm.get(entity);

        transform.position.set(body.body.getPosition().x * IConversions.MPP,
                body.body.getPosition().y * IConversions.MPP, transform.position.z);

        Bodies.add(body.body, entity);

        if(!body.body.isActive()) {
            getEngine().removeEntity(entity);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.R)) {
            if(body.rootPosition != null) {
                body.body.setTransform(body.rootPosition, 0f);
            }
        }
    }
}
