package com.mygdx.breakout.collision;

/**
 * Created by Dubforce on 1/23/2016.
 */
public interface IMaskBits {
    short PADDLE = ICollisionBits.BALL | ICollisionBits.WALL;
    short BALL = ICollisionBits.WALL | ICollisionBits.BRICK | ICollisionBits.PADDLE | ICollisionBits.ENEMY;
    short WALL = ICollisionBits.PADDLE | ICollisionBits.BALL;
    short BRICK = ICollisionBits.BALL;
    short CEILING = ICollisionBits.BALL | ICollisionBits.ENEMY;
    short GROUND = ICollisionBits.BALL | ICollisionBits.ENEMY;
}
