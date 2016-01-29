package com.mygdx.breakout.collision;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.managers.Bodies;
import com.mygdx.breakout.managers.Triggers;

/**
 * Created by Dubforce on 1/25/2016.
 */
public class PlatformerListener implements ContactListener {
    private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        Filter af = a.getFilterData();
        Filter bf = b.getFilterData();

        switch(bf.categoryBits) {
            case ICollisionBits.PLAYER:
                if(af.categoryBits == ICollisionBits.GROUND) {
                    playerCollidedWithGround(b, a, true);
                }
                else if(af.categoryBits == ICollisionBits.PLATFORM) {
                    playerCollidedWithGround(b, a, true);
                }
                else if(af.categoryBits == ICollisionBits.DOOR) {
                    Triggers.activate(a.getBody());
                }
                break;
            case ICollisionBits.GROUND:
                if(af.categoryBits == ICollisionBits.PLAYER) {
                    playerCollidedWithGround(a, b, true);
                }
                break;
            case ICollisionBits.DOOR:
                if(af.categoryBits == ICollisionBits.PLAYER) {
                    Triggers.activate(b.getBody());
                }
            default:
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        Filter af = a.getFilterData();
        Filter bf = b.getFilterData();

        switch(bf.categoryBits) {
            case ICollisionBits.PLAYER:
                if(af.categoryBits == ICollisionBits.GROUND) {
                    playerCollidedWithGround(b, a, false);
                }
                else if(af.categoryBits == ICollisionBits.PLATFORM) {
                    playerCollidedWithGround(b, a, false);
                }
                break;
            case ICollisionBits.GROUND:
                if(af.categoryBits == ICollisionBits.PLAYER) {
                    playerCollidedWithGround(a, b, false);
                }
            default:
                break;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    // Utility methods
    private void playerCollidedWithGround(Fixture player, Fixture ground, boolean contactStarted) {
        BodyComponent bc = bm.get(Bodies.get(player.getBody()));
        bc.onGround = contactStarted;
    }
}
