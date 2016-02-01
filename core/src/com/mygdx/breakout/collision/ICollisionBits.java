package com.mygdx.breakout.collision;

/**
 * Created by Dubforce on 1/23/2016.
 */
public interface ICollisionBits {
    short BALL = 0x1;
    short BRICK = BALL << 1;
    short WALL = BRICK << 1;
    short PADDLE = WALL << 1;
    short CEILING = PADDLE << 1;
    short GROUND = CEILING << 1;
    short ENEMY = GROUND << 1;
    short PLATFORM = ENEMY << 1;
    short PLAYER = PLATFORM << 1;
    short DOOR = PLAYER << 1;
    short PICKUP = DOOR << 1;
}
