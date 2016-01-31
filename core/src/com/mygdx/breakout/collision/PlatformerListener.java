package com.mygdx.breakout.collision;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.HealthComponent;
import com.mygdx.breakout.managers.Bodies;
import com.mygdx.breakout.managers.Triggers;

/**
 * Created by Dubforce on 1/25/2016.
 */
public class PlatformerListener implements ContactListener {
    private ComponentMapper<BodyComponent> bm;
    private ComponentMapper<HealthComponent> hm;

    public PlatformerListener() {
        bm = ComponentMapper.getFor(BodyComponent.class);
        hm = ComponentMapper.getFor(HealthComponent.class);
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        Filter af = a.getFilterData();
        Filter bf = b.getFilterData();

        switch(bf.categoryBits) {
            case ICollisionBits.PLAYER:
                if(af.categoryBits == ICollisionBits.GROUND) {
                    playerCollidedWithGround(b, a, true, contact.getWorldManifold().getNormal());
                }
                else if(af.categoryBits == ICollisionBits.PLATFORM) {
                    playerCollidedWithGround(b, a, true, contact.getWorldManifold().getNormal());
                }
                else if(af.categoryBits == ICollisionBits.DOOR) {
                    Triggers.activate(a.getBody());
                }
                else if(af.categoryBits == ICollisionBits.ENEMY) {
                    playerCollidedWithEnemy(b, a, true, contact.getWorldManifold().getNormal());
                }
                break;
            case ICollisionBits.ENEMY:
                if(af.categoryBits == ICollisionBits.GROUND) {
                    playerCollidedWithGround(b, a, true, contact.getWorldManifold().getNormal());
                }
                else if(af.categoryBits == ICollisionBits.PLATFORM) {
                    playerCollidedWithGround(b, a, true, contact.getWorldManifold().getNormal());
                }
                else if(af.categoryBits == ICollisionBits.PLAYER) {
                    playerCollidedWithEnemy(a, b, true, contact.getWorldManifold().getNormal());
                }
            case ICollisionBits.GROUND:
                if(af.categoryBits == ICollisionBits.PLAYER) {
                    playerCollidedWithGround(a, b, true, contact.getWorldManifold().getNormal());
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
                    playerCollidedWithGround(b, a, false, contact.getWorldManifold().getNormal());
                }
                else if(af.categoryBits == ICollisionBits.PLATFORM) {
                    playerCollidedWithGround(b, a, false, contact.getWorldManifold().getNormal());
                }
                else if(af.categoryBits == ICollisionBits.ENEMY) {
                    playerCollidedWithEnemy(b, a, false, contact.getWorldManifold().getNormal());
                }
                break;
            case ICollisionBits.ENEMY:
                if(af.categoryBits == ICollisionBits.GROUND || af.categoryBits == ICollisionBits.PLATFORM) {
                    playerCollidedWithGround(b, a, false, contact.getWorldManifold().getNormal());
                }
                else if(af.categoryBits == ICollisionBits.PLAYER) {
                    playerCollidedWithEnemy(a, b, false, contact.getWorldManifold().getNormal());
                }
            case ICollisionBits.GROUND:
                if(af.categoryBits == ICollisionBits.PLAYER) {
                    playerCollidedWithGround(a, b, false, contact.getWorldManifold().getNormal());
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
    private void playerCollidedWithGround(Fixture player, Fixture ground, boolean contactStarted, Vector2 normal) {
        if(Bodies.get(player.getBody()) != null) {
            BodyComponent bc = bm.get(Bodies.get(player.getBody()));

            if(normal.y == 1.0f) {
                bc.onGround = true;

                if(contactStarted) {
                    bc.canDoubleJump = true;
                }
            }
            else {
                bc.onGround = false;
            }
        }
    }

    private void playerCollidedWithEnemy(Fixture player, Fixture enemy, boolean contactStarted, Vector2 normal) {
        BodyComponent bc = bm.get(Bodies.get(player.getBody()));
        BodyComponent ebc = bm.get(Bodies.get(enemy.getBody()));
        HealthComponent healthComponent = hm.get(Bodies.get(player.getBody()));

        float vx = Math.signum(bc.body.getPosition().cpy().sub(ebc.body.getPosition().cpy()).x) * 1500f;

        if(contactStarted) {
            healthComponent.health -= 1;

            System.out.println(healthComponent.health);

            //bc.body.applyForceToCenter(new Vector2(vx, 100f), false);


            healthComponent.timeSinceLastHit = 0f;
        }
    }
}
