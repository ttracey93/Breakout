package com.mygdx.breakout.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.components.*;
import com.mygdx.breakout.managers.Assets;

/**
 * Created by Dubforce on 1/29/2016.
 */
public class EnemyFactory {
    public static Entity walking(PooledEngine engine, World world, RectangleMapObject spawn, Entity target) {
        Entity enemy = engine.createEntity();

        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        HealthComponent healthComponent = engine.createComponent(HealthComponent.class);
        JumpComponent jumpComponent = engine.createComponent(JumpComponent.class);
        AIComponent aiComponent = engine.createComponent(AIComponent.class);
        ControllerComponent controllerComponent = engine.createComponent(ControllerComponent.class);

        aiComponent.target = target;
        textureComponent.region = Assets.enemy;
        healthComponent.health = 5;
        jumpComponent.jumpForce = new Vector2(0, 150f);

        BodyComponent bodyComponent = BodyFactory.enemy(engine, world, textureComponent, spawn);

        enemy.add(transformComponent);
        enemy.add(textureComponent);
        enemy.add(healthComponent);
        //enemy.add(jumpComponent);
        enemy.add(aiComponent);
        enemy.add(bodyComponent);
        enemy.add(controllerComponent);

        engine.addEntity(enemy);

        return enemy;
    }
}
