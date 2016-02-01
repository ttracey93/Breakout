package com.mygdx.breakout.factories;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.mygdx.breakout.components.CameraComponent;
import com.mygdx.breakout.components.TransformComponent;
import com.mygdx.breakout.systems.RenderingSystem;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class CameraFactory {
    private static ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

    public static Entity camera(PooledEngine engine, Entity target) {
        Entity entity = engine.createEntity();

        TransformComponent transform = tm.get(target);

        CameraComponent camera = new CameraComponent();
        camera.camera = engine.getSystem(RenderingSystem.class).getCamera();
        camera.camera.position.set(transform.position.x, transform.position.y, 0);
        camera.target = target;

        entity.add(camera);

        engine.addEntity(entity);

        return entity;
    }
}
