package com.mygdx.breakout.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.HealthComponent;
import com.mygdx.breakout.managers.Bodies;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class HealthSystem extends IteratingSystem {
    private PooledEngine engine;
    private World world;

    private ComponentMapper<BodyComponent> bm;
    private ComponentMapper<HealthComponent> hm;

    public HealthSystem(PooledEngine engine, World world) {
        super(Family.all(BodyComponent.class, HealthComponent.class).get());

        bm = ComponentMapper.getFor(BodyComponent.class);
        hm = ComponentMapper.getFor(HealthComponent.class);

        this.engine = engine;
        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent body = bm.get(entity);
        HealthComponent health = hm.get(entity);

        if(health.health <= 0) {
            Bodies.remove(body.body);
            world.destroyBody(body.body);
            engine.removeEntity(entity);
        }
    }
}