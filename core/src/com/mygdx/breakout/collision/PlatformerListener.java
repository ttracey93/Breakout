package com.mygdx.breakout.collision;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Dubforce on 1/25/2016.
 */
public class PlatformerListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        Filter af = a.getFilterData();

        System.out.println("Platform listener");
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
