package com.mygdx.breakout.screens;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.breakout.Breakout;
import com.mygdx.breakout.factories.LevelFactory;
import com.mygdx.breakout.managers.Destroyables;
import com.mygdx.breakout.managers.Triggers;
import com.mygdx.breakout.managers.Utils;
import com.mygdx.breakout.systems.RenderingSystem;
import com.mygdx.breakout.world.BreakoutLevel;
import com.mygdx.breakout.world.GameState;
import com.mygdx.breakout.world.Level;

/**
 * Created by Dubforce on 1/25/2016.
 */
public abstract class GameScreen extends ScreenAdapter {
    // game reference
    protected Breakout game;

    // cameras
    protected OrthographicCamera guiCam;

    // Entity engine
    protected PooledEngine engine;

    // Box2D objects
    protected World world;
    protected RayHandler rayHandler;
    protected Level level;

    // Local state
    protected GameState state;

    public GameScreen(Breakout game) {
        this.game = game;
        this.state = GameState.PLAYING;

        Utils.setGame(game);
        Utils.setState(this.state);

        guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        guiCam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        engine = new PooledEngine();
    }


    @Override
    public void render(float delta) {
        update(delta);
        drawGUI();
    }

    protected void update(float delta) {
        if(state == GameState.PLAYING) {
            world.step(delta, 6, 2);
        }

        engine.update(delta);
        Triggers.update();
        Destroyables.update();
    }

    protected void drawGUI() {

    }

    public void setLevel(String levelName) {
        // remove all entities breakout the engine
        engine.removeAllEntities();

        // clear world of all bodies
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for(Body body : bodies) {
            world.destroyBody(body);
        }

        // remove all lights breakout the world
        rayHandler.removeAll();

        // generate new level
        level = LevelFactory.platformer(game, "maps/" + levelName, engine, world, rayHandler);

        // set level in rendering system
        engine.getSystem(RenderingSystem.class).setLevel(level);
    }
}
