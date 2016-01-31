package com.mygdx.breakout.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.breakout.components.AIComponent;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.ControllerComponent;

/**
 * Created by Dubforce on 1/29/2016.
 */
public class AISystem extends IteratingSystem {
    ComponentMapper<AIComponent> am = ComponentMapper.getFor(AIComponent.class);
    ComponentMapper<ControllerComponent> cm = ComponentMapper.getFor(ControllerComponent.class);
    ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);

    public AISystem() {
        super(Family.all(AIComponent.class, ControllerComponent.class, BodyComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        AIComponent aiComponent = am.get(entity);
        ControllerComponent controllerComponent = cm.get(entity);
        BodyComponent bodyComponent = bm.get(entity);

        BodyComponent targetBody = bm.get(aiComponent.target);

        Vector2 distance = targetBody.body.getPosition().cpy().sub(bodyComponent.body.getPosition().cpy());
        controllerComponent.leftAxis.set(Math.signum(distance.x), Math.signum(distance.y));

        controllerComponent.action = controllerComponent.leftAxis.y > 0;
    }
}
