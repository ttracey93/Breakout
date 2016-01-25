package com.mygdx.breakout.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Dubforce on 1/23/2016.
 */
public class Assets {
    public static TextureRegion brick;
    public static TextureRegion paddle;
    public static TextureRegion ball;

    public static Texture playButton;

    public static void load() {
        brick = new TextureRegion(new Texture("game/brick.png"));
        paddle = new TextureRegion(new Texture("game/paddle.png"));
        ball = new TextureRegion(new Texture("game/ball.png"));

        playButton = new Texture("UI/menus/play.png");
    }
}
