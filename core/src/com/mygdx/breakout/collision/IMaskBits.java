package com.mygdx.breakout.collision;

/**
 * Created by Dubforce on 1/23/2016.
 */
public interface IMaskBits {
    short PADDLE = ICollisionBits.BALL | ICollisionBits.WALL | ICollisionBits.PLAYER | ICollisionBits.ENEMY;
    short BALL = ICollisionBits.WALL | ICollisionBits.BRICK | ICollisionBits.PADDLE | ICollisionBits.ENEMY;
    short WALL = ICollisionBits.PADDLE | ICollisionBits.BALL | ICollisionBits.PLAYER | ICollisionBits.ENEMY;
    short BRICK = ICollisionBits.BALL;
    short CEILING = ICollisionBits.BALL | ICollisionBits.ENEMY | ICollisionBits.PLAYER;
    short GROUND = ICollisionBits.BALL | ICollisionBits.ENEMY | ICollisionBits.PLAYER;
    short PLATFORM = ICollisionBits.BALL | ICollisionBits.WALL | ICollisionBits.ENEMY
            | ICollisionBits.GROUND | ICollisionBits.CEILING | ICollisionBits.PLAYER;
    short PLAYER = ICollisionBits.PLATFORM | ICollisionBits.GROUND | ICollisionBits.CEILING | ICollisionBits.WALL |
            ICollisionBits.ENEMY | ICollisionBits.PADDLE | ICollisionBits.DOOR;
    short DOOR = ICollisionBits.PLAYER;
    short ENEMY = ICollisionBits.PADDLE | ICollisionBits.PLAYER | ICollisionBits.PLATFORM | ICollisionBits.GROUND |
            ICollisionBits.WALL | ICollisionBits.CEILING;
}
