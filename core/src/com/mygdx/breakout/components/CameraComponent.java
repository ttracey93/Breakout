package com.mygdx.breakout.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class CameraComponent implements Component {
    public Entity target;
    public OrthographicCamera camera;
    public float alpha = 1f;
}
