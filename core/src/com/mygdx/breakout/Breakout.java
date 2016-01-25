package com.mygdx.breakout;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.breakout.managers.*;
import com.mygdx.breakout.screens.MainMenu;

public class Breakout extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		// prepare game data and assets
		Settings.load();
		Assets.load();
		Fonts.load();
		Sounds.load();
		Triggers.load();
		Destroyables.load();

		// set the initial screen
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		// calls current screens render method
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();

		this.getScreen().dispose();
	}
}
