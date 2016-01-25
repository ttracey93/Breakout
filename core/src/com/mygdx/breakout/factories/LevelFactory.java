package com.mygdx.breakout.factories;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.screens.*;
import com.mygdx.breakout.triggers.SpawnType;
import com.mygdx.breakout.world.BreakoutLevel;
import com.mygdx.breakout.world.PlatformerLevel;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class LevelFactory {
    public static BreakoutLevel breakout(GameScreen screen, String mapPath, PooledEngine engine, World world, RayHandler rayHandler) {
        TiledMap map = new TmxMapLoader().load(mapPath);

        // add Box2D objects to world, and create entities?
        MapObjects walls = map.getLayers().get("Walls").getObjects();
        MapObjects bricks = map.getLayers().get("Bricks").getObjects();
        MapObjects spawns = map.getLayers().get("Spawns").getObjects();

        for(MapObject wall : walls) {
            BodyFactory.wall(world, (RectangleMapObject)wall);
        }

        for(MapObject brick : bricks) {
            BrickFactory.brick(engine, world, (RectangleMapObject)brick);
        }

        for(MapObject spawn : spawns) {
            MapProperties spawnProperties = spawn.getProperties();
            SpawnType type = SpawnType.valueOf(spawnProperties.get("type").toString());

            if(type.equals(SpawnType.PADDLE)) {
                System.out.println("Spawning paddle");

                Entity player = PaddleFactory.player(engine, world, rayHandler, (RectangleMapObject) spawn);
                CameraFactory.camera(engine, player);
            }
        }

        return new BreakoutLevel(map, world, bricks.getCount());
    }

    public static PlatformerLevel platformer(String mapPath, PooledEngine engine, World world, RayHandler rayHandler) {
        TiledMap map = new TmxMapLoader().load(mapPath);

        // object layers
        MapObjects walls = map.getLayers().get("Walls").getObjects();
        MapObjects ground = map.getLayers().get("Ground").getObjects();
        MapObjects ceiling = map.getLayers().get("Ceiling").getObjects();
        MapObjects platforms = map.getLayers().get("Platforms").getObjects();

        for(MapObject wall : walls) {
            BodyFactory.wall(world, (RectangleMapObject)wall);
        }

        for(MapObject floor : ground) {
            BodyFactory.ground(world, (RectangleMapObject)floor);
        }

        for(MapObject roof : ceiling) {
            BodyFactory.ceiling(world, (RectangleMapObject)roof);
        }

        for(MapObject platform : platforms) {
            PlatformFactory.normal(engine, world, (RectangleMapObject)platform);
        }

        return new PlatformerLevel(map, world);
    }
}
