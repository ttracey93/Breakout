package com.mygdx.breakout.factories;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.Breakout;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.managers.Triggers;
import com.mygdx.breakout.screens.*;
import com.mygdx.breakout.triggers.DoorTrigger;
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

    public static PlatformerLevel platformer(Breakout game, String mapPath, PooledEngine engine, World world, RayHandler rayHandler) {
        TiledMap map = new TmxMapLoader().load(mapPath);

        // object layers
        MapObjects walls = getObjects(map, "Walls");
        MapObjects ground = getObjects(map, "Ground");
        MapObjects ceiling = getObjects(map, "Ceiling");
        MapObjects platforms = getObjects(map, "Platforms");
        MapObjects spawns = getObjects(map, "Spawns");
        MapObjects enemies = getObjects(map, "Enemies");
        MapObjects pickups = getObjects(map, "Pickups");

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
            PlatformFactory.platform(engine, world, (RectangleMapObject)platform);
        }

        Entity player = null;

        for(MapObject spawn : spawns) {
            MapProperties props = spawn.getProperties();
            SpawnType st = SpawnType.valueOf(props.get("type").toString());

            if(st == SpawnType.PLAYER) {
                player = BallFactory.player(engine, world, (RectangleMapObject)spawn);
            }
            else if(st == SpawnType.DOOR) {
                System.out.println("Spawning door");

                DoorFactory.door(game, engine, world, (RectangleMapObject)spawn, props.get("level").toString());
            }
        }

        for(MapObject enemy : enemies) {
            EnemyFactory.walking(engine, world, (RectangleMapObject) enemy, player);
        }

        for(MapObject pickup : pickups) {
            PickupFactory.pickup(engine, world, (RectangleMapObject)pickup);
        }

        return new PlatformerLevel(map, world);
    }

    private static MapObjects getObjects(TiledMap map, String layerName) {
        MapLayer layer = map.getLayers().get(layerName);

        if(layer != null) {
            return layer.getObjects();
        }

        return new MapObjects();
    }
}
