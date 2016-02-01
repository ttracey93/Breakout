package com.mygdx.breakout.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.TextureComponent;
import com.mygdx.breakout.components.TransformComponent;
import com.mygdx.breakout.managers.Assets;
import com.mygdx.breakout.managers.Pickups;
import com.mygdx.breakout.pickups.JumpRefresher;
import com.mygdx.breakout.util.PickupType;

/**
 * Created by Dubforce on 1/31/2016.
 */
public class PickupFactory {
    public static Entity pickup(PooledEngine engine, World world, RectangleMapObject spawn) {
        MapProperties props = spawn.getProperties();
        PickupType pt = PickupType.valueOf(props.get("type").toString());

        if(pt == null) {
            return null;
        }

        if(pt == PickupType.JUMP_REFRESHER) {
            return jumpRefresher(engine, world, spawn);
        }

        return null;
    }

    public static Entity jumpRefresher(PooledEngine engine, World world, RectangleMapObject spawn) {
        Entity jumpRefresher = engine.createEntity();

        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        textureComponent.region = Assets.jumpRefresher;

        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        BodyComponent bodyComponent = BodyFactory.pickup(engine, world, spawn);

        jumpRefresher.add(textureComponent);
        jumpRefresher.add(transformComponent);
        jumpRefresher.add(bodyComponent);

        Pickups.put(jumpRefresher, new JumpRefresher());

        engine.addEntity(jumpRefresher);

        return jumpRefresher;
    }
}
