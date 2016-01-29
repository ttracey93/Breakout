package com.mygdx.breakout.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.Breakout;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.TextureComponent;
import com.mygdx.breakout.components.TransformComponent;
import com.mygdx.breakout.managers.Assets;
import com.mygdx.breakout.managers.Triggers;
import com.mygdx.breakout.screens.GameScreen;
import com.mygdx.breakout.triggers.DoorTrigger;

/**
 * Created by Dubforce on 1/28/2016.
 */
public class DoorFactory {
    public static void door(Breakout game, PooledEngine engine, World world, RectangleMapObject spawn, String level) {
        Entity door = engine.createEntity();

        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        TextureComponent textureComponent = engine.createComponent(TextureComponent.class);

        textureComponent.region = Assets.door;

        BodyComponent bc = BodyFactory.door(engine, world, textureComponent, spawn);

        Triggers.addTrigger(bc.body, new DoorTrigger(game, level));

        door.add(transformComponent);
        door.add(textureComponent);
        door.add(bc);

        engine.addEntity(door);
    }
}
