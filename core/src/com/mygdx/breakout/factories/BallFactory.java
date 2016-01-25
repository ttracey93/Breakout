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
public class BallFactory {
    public static Entity ball(PooledEngine engine, World world, RectangleMapObject spawn) {
        Entity ball = engine.createEntity();

        // AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        textureComponent.region = Assets.ball;

        // Keeping a reference to the light so it can be modified by powerups.
        BodyComponent bodyComponent = BodyFactory.ball(engine, world, textureComponent, spawn);

        ball.add(transform);
        ball.add(textureComponent);
        ball.add(bodyComponent);

        engine.addEntity(ball);

        return ball;
    }
}
