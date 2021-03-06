package com.ogurlek.chess;

import com.badlogic.gdx.Game;
import com.ogurlek.chess.screens.SplashScreen;

public class Chess extends Game {
	
	@Override
	public void create() {	
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {	
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width,height);
	}
	
	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
