package com.mygdx.breakout.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.ControllerComponent;
import com.mygdx.breakout.components.JumpComponent;

/**
 * Created by Dubforce on 1/28/2016.
 */
public class JumpSystem extends IteratingSystem {
    private ComponentMapper<ControllerComponent> cm = ComponentMapper.getFor(ControllerComponent.class);
    private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<JumpComponent> jm = ComponentMapper.getFor(JumpComponent.class);

    public JumpSystem() {
        super(Family.all(ControllerComponent.class, BodyComponent.class, JumpComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ControllerComponent controllerComponent = cm.get(entity);
        BodyComponent bodyComponent = bm.get(entity);
        JumpComponent jumpComponent = jm.get(entity);

        if(controllerComponent.action && jumpComponent.onGround) {
            //bodyComponent.body.applyForceToCenter(jumpComponent.jumpForce, false);
            bodyComponent.body.setLinearVelocity(bodyComponent.body.getLinearVelocity().x, jumpComponent.jumpForce.y);
        }
        else if(controllerComponent.action && jumpComponent.canDoubleJump) {
            bodyComponent.body.setLinearVelocity(bodyComponent.body.getLinearVelocity().x, jumpComponent.doubleJumpForce.y);
            jumpComponent.canDoubleJump = false;
        }
    }
}
