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
import com.mygdx.breakout.collision.PlatformerListener;
import com.mygdx.breakout.components.PlatformComponent;
import com.mygdx.breakout.factories.LevelFactory;
import com.mygdx.breakout.managers.Destroyables;
import com.mygdx.breakout.managers.Fonts;
import com.mygdx.breakout.managers.Triggers;
import com.mygdx.breakout.managers.Utils;
import com.mygdx.breakout.systems.*;
import com.mygdx.breakout.util.IMapPath;
import com.mygdx.breakout.world.GameState;

/**
 * Created by Dubforce on 1/25/2016.
 */
public class PlatformerScreen extends GameScreen {
    public PlatformerScreen(Breakout game) {
        this(game, IMapPath.gameOver.replace("maps/", ""));
    }

    public PlatformerScreen(Breakout game, String levelName) {
        super(game);

        world = new World(new Vector2(0, -25f), true);
        world.setContactListener(new PlatformerListener());
        Triggers.setWorld(world);
        Destroyables.setWorld(world);

        // global light settings
        rayHandler = new RayHandler(world);
        rayHandler.setShadows(false);

        engine.addSystem(new CameraSystem());
        engine.addSystem(new BodySystem());
        engine.addSystem(new ControllerSystem());
        engine.addSystem(new RenderingSystem(game.batch, rayHandler));
        engine.addSystem(new PlatformSystem());
        engine.addSystem(new JumpSystem());
        engine.addSystem(new AISystem());
        engine.addSystem(new HealthSystem(engine, world));
        engine.addSystem(new MovementSystem());

        level = LevelFactory.platformer(game, "maps/" + levelName, engine, world, rayHandler);
        Utils.setLevel(level);

        engine.getSystem(RenderingSystem.class).setLevel(level);
    }

    protected void update(float delta) {
        super.update(delta);
    }

    @Override
    protected void drawGUI() {
        game.batch.begin();

        //game.batch.draw

        game.batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();

        // dispose of anything else
    }
}
