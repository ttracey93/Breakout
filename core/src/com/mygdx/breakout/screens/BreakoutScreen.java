package com.mygdx.breakout.screens;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.breakout.Breakout;
import com.mygdx.breakout.collision.BreakoutListener;
import com.mygdx.breakout.factories.LevelFactory;
import com.mygdx.breakout.managers.Destroyables;
import com.mygdx.breakout.managers.Triggers;
import com.mygdx.breakout.managers.Utils;
import com.mygdx.breakout.systems.BodySystem;
import com.mygdx.breakout.systems.CameraSystem;
import com.mygdx.breakout.systems.ControllerSystem;
import com.mygdx.breakout.systems.RenderingSystem;
import com.mygdx.breakout.util.IMapPath;
import com.mygdx.breakout.world.BreakoutLevel;
import com.mygdx.breakout.world.GameState;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class BreakoutScreen extends GameScreen {
    public BreakoutScreen(Breakout game) {
        this.game = game;
        state = GameState.PLAYING;
        Utils.state = state;

        guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        guiCam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        engine = new PooledEngine();

        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new BreakoutListener());
        Triggers.setWorld(world);
        Destroyables.setWorld(world);

        // global light settings
        rayHandler = new RayHandler(world);
        rayHandler.setShadows(false);

        engine.addSystem(new CameraSystem());
        engine.addSystem(new BodySystem());
        engine.addSystem(new ControllerSystem());
        engine.addSystem(new RenderingSystem(game.batch, rayHandler));

        level = LevelFactory.breakout(this, IMapPath.test, engine, world, rayHandler);
        Utils.setLevel(level);

        engine.getSystem(RenderingSystem.class).setLevel(level);
    }

    @Override
    public void render(float delta) {
        update(delta);
    }

    protected void update(float delta) {
        super.update(delta);

        if(((BreakoutLevel)level).getBricks() <= 0) {
            state = GameState.WON;
            Utils.state = state;
        }

        if(state == GameState.WON) {
            game.setScreen(new PlatformerScreen(game));
        }
    }

    @Override
    public void dispose() {
        super.dispose();

        // dispose of anything else
    }
}
