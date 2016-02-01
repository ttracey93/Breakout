package com.mygdx.breakout.systems;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.breakout.components.TextureComponent;
import com.mygdx.breakout.components.TransformComponent;
import com.mygdx.breakout.managers.Settings;
import com.mygdx.breakout.managers.Utils;
import com.mygdx.breakout.util.IConversions;
import com.mygdx.breakout.world.GameState;
import com.mygdx.breakout.world.Level;

import java.util.Comparator;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class RenderingSystem extends IteratingSystem {
    private SpriteBatch batch;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private OrthographicCamera camera;

    // map
    private Level level;
    private RayHandler rayHandler;

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<TextureComponent> texM;

    public RenderingSystem(SpriteBatch batch, RayHandler rayHandler) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get());

        this.batch = batch;
        this.rayHandler = rayHandler;

        tm = ComponentMapper.getFor(TransformComponent.class);
        texM = ComponentMapper.getFor(TextureComponent.class);

        renderQueue = new Array<Entity>();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity a, Entity b) {
                return (int)Math.signum(tm.get(b).position.z - tm.get(a).position.z);
            }
        };
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // render tiled map
        level.render(camera);

        batch.begin();

        for(Entity entity : renderQueue) {
            TextureComponent texture = texM.get(entity);

            if (texture.region == null) {
                continue;
            }

            TransformComponent transform = tm.get(entity);

            float width = texture.region.getRegionWidth();
            float height = texture.region.getRegionHeight();
            float x = width * 0.5f;
            float y = height * 0.5f;

            batch.draw(
                    texture.region,
                    transform.position.x, transform.position.y,
                    x, y,
                    width, height,
                    transform.scale.x, transform.scale.y,
                    transform.rotation
            );
        }

        batch.end();
        renderQueue.clear();

        // scale for physics/light
        camera.combined.scl(IConversions.MPP);

        if(Settings.debug) {
            level.debug(camera);
        }

        /*if(Settings.renderLight) {
            rayHandler.setCombinedMatrix(
                    camera.combined,
                    camera.position.x * IConversions.PPM, camera.position.y * IConversions.PPM,
                    camera.viewportWidth * IConversions.PPM, camera.viewportHeight * IConversions.PPM
            );

            rayHandler.updateAndRender();
        }*/
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
