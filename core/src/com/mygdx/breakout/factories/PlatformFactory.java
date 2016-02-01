package com.mygdx.breakout.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.PlatformComponent;
import com.mygdx.breakout.components.TextureComponent;
import com.mygdx.breakout.components.TransformComponent;
import com.mygdx.breakout.managers.Assets;
import com.mygdx.breakout.util.IConversions;
import com.mygdx.breakout.util.PlatformType;

/**
 * Created by Dubforce on 1/25/2016.
 */
public class PlatformFactory {
    public static Entity platform(PooledEngine engine, World world, RectangleMapObject spawn) {
        MapProperties props = spawn.getProperties();
        PlatformType pt = PlatformType.NORMAL;

        if(props.get("type") != null) {
            pt = PlatformType.valueOf(props.get("type").toString());
        }

        if(pt == null || pt == PlatformType.NORMAL) {
            System.out.println("normal");
            return normal(engine, world, spawn);
        }
        else if(pt == PlatformType.HORIZONTAL) {
            System.out.println("horiz");
            return horizontal(engine, world, spawn);
        }
        else if(pt == PlatformType.VERTICAL) {
            System.out.println("vertical");
            return vertical(engine, world, spawn);
        }

        return null;
    }

    public static Entity horizontal(PooledEngine engine, World world, RectangleMapObject spawn) {
        Entity platform = engine.createEntity();

        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        PlatformComponent platformComponent = engine.createComponent(PlatformComponent.class);

        BodyComponent body = BodyFactory.platform(engine, world, spawn);

        platformComponent.origin = body.body.getPosition().cpy();
        platformComponent.moveSpeed = new Vector2(3, 0);
        platformComponent.displacement = new Vector2(2.5f, 0);

        platform.add(transform);
        platform.add(textureComponent);
        platform.add(body);
        platform.add(platformComponent);

        engine.addEntity(platform);

        return platform;
    }

    public static Entity vertical(PooledEngine engine, World world, RectangleMapObject spawn) {
        Entity platform = engine.createEntity();

        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        PlatformComponent platformComponent = engine.createComponent(PlatformComponent.class);

        BodyComponent body = BodyFactory.platform(engine, world, spawn);

        platformComponent.origin = body.body.getPosition().cpy();
        platformComponent.moveSpeed = new Vector2(0, 3);
        platformComponent.displacement = new Vector2(0, 2.5f);

        platform.add(transform);
        platform.add(textureComponent);
        platform.add(body);
        platform.add(platformComponent);

        engine.addEntity(platform);

        return platform;
    }

    public static Entity normal(PooledEngine engine, World world, RectangleMapObject spawn) {
        Entity platform = engine.createEntity();

        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        PlatformComponent platformComponent = engine.createComponent(PlatformComponent.class);

        BodyComponent body = BodyFactory.platform(engine, world, spawn);

        platformComponent.origin = body.body.getPosition().cpy();

        platform.add(transform);
        platform.add(textureComponent);
        platform.add(body);
        platform.add(platformComponent);

        engine.addEntity(platform);

        return platform;
    }
}
