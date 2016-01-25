package com.mygdx.breakout.collision;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.breakout.managers.Destroyables;
import com.mygdx.breakout.managers.Utils;
import com.mygdx.breakout.world.BreakoutLevel;
import com.mygdx.breakout.world.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class BreakoutListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        Filter af = a.getFilterData();

        switch(af.categoryBits) {
            case ICollisionBits.BALL:
                ballCollision(a, b);
                break;
            case ICollisionBits.BRICK:
                brickCollision(a, b);
                break;
            case ICollisionBits.PADDLE:
                paddleCollision(a, b);
                break;
            case ICollisionBits.WALL:
                wallCollision(a, b);
                break;
            default:
                break;
        }
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

    // collision sub routines
    private void ballCollision(Fixture ball, Fixture other) {
        switch(other.getFilterData().categoryBits) {
            case ICollisionBits.WALL:
                checkBallVelocity(ball, other);
                break;
            case ICollisionBits.BRICK:
                Destroyables.destroy(other.getBody());
                break;
            default:
                break;
        }
    }

    private void wallCollision(Fixture wall, Fixture other) {
        switch(other.getFilterData().categoryBits) {
            case ICollisionBits.BALL:
                checkBallVelocity(other, wall);
                break;
            case ICollisionBits.PADDLE:
                break;
            default:
                break;
        }

    }

    private void paddleCollision(Fixture paddle, Fixture other) {

    }

    private void brickCollision(Fixture brick, Fixture other) {
        switch(other.getFilterData().categoryBits) {
            case ICollisionBits.BALL:
                Destroyables.destroy(brick.getBody());

                Level level = Utils.getLevel();

                if(level instanceof BreakoutLevel) {
                    ((BreakoutLevel) level).brickDestroyed();
                }
                break;
        }
    }

    private void checkBallVelocity(Fixture ball, Fixture other) {

    }
}

