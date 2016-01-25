package com.mygdx.breakout.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.ControllerComponent;
import com.mygdx.breakout.components.TextureComponent;
import com.mygdx.breakout.components.TransformComponent;
import com.mygdx.breakout.managers.Assets;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class BrickFactory {
    public static Entity brick(PooledEngine engine, World world, RectangleMapObject spawn) {
        Entity brick = engine.createEntity();

        // AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        ControllerComponent controller = engine.createComponent(ControllerComponent.class);

        textureComponent.region = Assets.brick;

        // Keeping a reference to the light so it can be modified by powerups.
        BodyComponent bodyComponent = BodyFactory.brick(engine, world, textureComponent, spawn);

        brick.add(transform);
        brick.add(textureComponent);
        brick.add(bodyComponent);
        brick.add(controller);

        engine.addEntity(brick);

        return brick;
    }
}
