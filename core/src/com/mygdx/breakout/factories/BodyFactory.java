package com.mygdx.breakout.factories;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.breakout.collision.ICollisionBits;
import com.mygdx.breakout.collision.IMaskBits;
import com.mygdx.breakout.components.BodyComponent;
import com.mygdx.breakout.components.TextureComponent;
import com.mygdx.breakout.util.IConversions;

import java.util.Random;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class BodyFactory {
    public static BodyComponent paddle(PooledEngine engine, World world, TextureComponent tc, RectangleMapObject spawn) {
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);

        // Initialize body component
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        spawn.getRectangle().getCenter(bodyDef.position);
        bodyDef.position.scl(IConversions.PPM);

        // Create a body in the world using our definition
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(
                tc.region.getRegionWidth() * IConversions.PPM / 2, // hx
                tc.region.getRegionHeight() * IConversions.PPM / 2  // hy
        );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.filter.categoryBits = ICollisionBits.PADDLE;
        fixtureDef.filter.maskBits = IMaskBits.PADDLE;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();

        bodyComponent.bodyDef = bodyDef;
        bodyComponent.body = body;
        bodyComponent.shape = shape;
        bodyComponent.fixtureDef = fixtureDef;
        bodyComponent.fixture = fixture;
        bodyComponent.moveSpeed.set(5, 5);

        return bodyComponent;
    }

    public static void wall(World world, RectangleMapObject object) {
        // Initialize body component
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        object.getRectangle().getPosition(bodyDef.position); // sets body position
        bodyDef.position.scl(IConversions.PPM);

        // Create a body in the world using our definition
        Body body = world.createBody(bodyDef);

        PolygonShape shape = ShapeFactory.fromRectangle(object);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.filter.categoryBits = ICollisionBits.WALL;
        fixtureDef.filter.maskBits = IMaskBits.WALL;

        body.createFixture(fixtureDef);

        shape.dispose();
    }

    public static void ceiling(World world, RectangleMapObject object) {
        // Initialize body component
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        object.getRectangle().getPosition(bodyDef.position); // sets body position
        bodyDef.position.scl(IConversions.PPM);

        // Create a body in the world using our definition
        Body body = world.createBody(bodyDef);

        PolygonShape shape = ShapeFactory.fromRectangle(object);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.filter.categoryBits = ICollisionBits.CEILING;
        fixtureDef.filter.maskBits = IMaskBits.CEILING;

        body.createFixture(fixtureDef);

        shape.dispose();
    }

    public static void ground(World world, RectangleMapObject object) {
        // Initialize body component
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        object.getRectangle().getPosition(bodyDef.position); // sets body position
        bodyDef.position.scl(IConversions.PPM);

        // Create a body in the world using our definition
        Body body = world.createBody(bodyDef);

        PolygonShape shape = ShapeFactory.fromRectangle(object);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.filter.categoryBits = ICollisionBits.GROUND;
        fixtureDef.filter.maskBits = IMaskBits.GROUND;

        body.createFixture(fixtureDef);

        shape.dispose();
    }

    public static BodyComponent brick(PooledEngine engine, World world, TextureComponent tc, RectangleMapObject spawn) {
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);

        // Initialize body component
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        spawn.getRectangle().getCenter(bodyDef.position);
        bodyDef.position.scl(IConversions.PPM);

        // Create a body in the world using our definition
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(
                tc.region.getRegionWidth() * IConversions.PPM / 2, // hx
                tc.region.getRegionHeight() * IConversions.PPM / 2  // hy
        );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.filter.categoryBits = ICollisionBits.BRICK;
        fixtureDef.filter.maskBits = IMaskBits.BRICK;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();

        bodyComponent.bodyDef = bodyDef;
        bodyComponent.body = body;
        bodyComponent.shape = shape;
        bodyComponent.fixtureDef = fixtureDef;
        bodyComponent.fixture = fixture;
        bodyComponent.moveSpeed.set(0, 0);

        return bodyComponent;
    }

    public static BodyComponent ball(PooledEngine engine, World world, TextureComponent tc, RectangleMapObject spawn) {
        BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);

        // Initialize body component
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        spawn.getRectangle().getCenter(bodyDef.position);
        bodyDef.position.y += 15; // 15 pixels away breakout paddle
        bodyDef.position.scl(IConversions.PPM);

        // Create a body in the world using our definition
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(
                tc.region.getRegionWidth() * IConversions.PPM / 2, // hx
                tc.region.getRegionHeight() * IConversions.PPM / 2  // hy
        );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.restitution = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.filter.categoryBits = ICollisionBits.BALL;
        fixtureDef.filter.maskBits = IMaskBits.BALL;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();

        bodyComponent.bodyDef = bodyDef;
        bodyComponent.body = body;
        bodyComponent.shape = shape;
        bodyComponent.fixtureDef = fixtureDef;
        bodyComponent.fixture = fixture;
        bodyComponent.body.setLinearVelocity(0, 5);
        bodyComponent.rootPosition = bodyComponent.body.getPosition().cpy();

        return bodyComponent;
    }
}
