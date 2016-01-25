package com.mygdx.breakout.factories;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.collision.ICollisionBits;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.ControllerComponent;
import com.mygdx.breakout.components.TextureComponent;
import com.mygdx.breakout.components.TransformComponent;
import com.mygdx.breakout.managers.Assets;
import com.mygdx.breakout.util.IConversions;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class PaddleFactory {
    public static Entity player(PooledEngine engine, World world, RayHandler rayHandler, RectangleMapObject spawn) {
        Entity paddle = engine.createEntity();

        // AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        ControllerComponent controller = engine.createComponent(ControllerComponent.class);

        textureComponent.region = Assets.paddle;

        // Keeping a reference to the light so it can be modified by powerups.
        BodyComponent bodyComponent = BodyFactory.paddle(engine, world, textureComponent, spawn);

        paddle.add(transform);
        paddle.add(textureComponent);
        paddle.add(bodyComponent);
        paddle.add(controller);

        engine.addEntity(paddle);

        BallFactory.ball(engine, world, spawn);

        return paddle;
    }
}
