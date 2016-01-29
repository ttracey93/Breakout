package com.mygdx.breakout.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.components.*;
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

    public static Entity player(PooledEngine engine, World world, RectangleMapObject spawn) {
        Entity ball = engine.createEntity();

        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        ControllerComponent controllerComponent = engine.createComponent(ControllerComponent.class);
        JumpComponent jumpComponent = engine.createComponent(JumpComponent.class);

        textureComponent.region = Assets.ball;

        // Keeping a reference to the light so it can be modified by powerups.
        BodyComponent bodyComponent = BodyFactory.player(engine, world, textureComponent, spawn);
        transform.position.set(bodyComponent.body.getPosition().x, bodyComponent.body.getPosition().y, 0);
        jumpComponent.jumpForce = new Vector2(0, 250f);

        ball.add(transform);
        ball.add(textureComponent);
        ball.add(bodyComponent);
        ball.add(controllerComponent);
        ball.add(jumpComponent);

        engine.addEntity(ball);

        CameraFactory.camera(engine, ball);

        return ball;
    }
}
