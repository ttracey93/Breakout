package com.mygdx.breakout.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.PlatformComponent;
import com.mygdx.breakout.components.TextureComponent;
import com.mygdx.breakout.components.TransformComponent;
import com.mygdx.breakout.managers.Assets;
import com.mygdx.breakout.util.IConversions;

/**
 * Created by Dubforce on 1/25/2016.
 */
public class PlatformFactory {
    public static Entity horizontal(PooledEngine engine, World world, RectangleMapObject spawn) {
        return engine.createEntity();
    }

    public static Entity vertical(PooledEngine engine, World world, RectangleMapObject spawn) {
        return engine.createEntity();
    }

    public static Entity normal(PooledEngine engine, World world, RectangleMapObject spawn) {
        Entity platform = engine.createEntity();

        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        PlatformComponent platformComponent = engine.createComponent(PlatformComponent.class);

        texture.region = Assets.platform;

        BodyComponent body = BodyFactory.platform(engine, world, texture, spawn);

        platformComponent.origin = body.body.getPosition().cpy().scl(IConversions.METERS_PER_PIXEL);

        platform.add(transform);
        platform.add(texture);
        platform.add(body);
        platform.add(platformComponent);

        return platform;
    }
}
