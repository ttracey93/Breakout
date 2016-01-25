package com.mygdx.breakout.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Dubforce on 1/24/2016.
 */
public class BreakoutLevel extends Level {
    private int bricks;

    public BreakoutLevel(TiledMap map, World world, int bricks) {
        super(map, world);

        this.bricks = bricks;
    }

    public int getBricks() {
        return bricks;
    }

    public void brickDestroyed() {
        bricks--;
    }
}
