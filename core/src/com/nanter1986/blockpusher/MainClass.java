package com.nanter1986.blockpusher;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MainClass extends Game {
	MainClass game;
	DisplayToolkit tool;
	
	public MainClass(){

		game=this;
	}

	@Override
	public void create() {
		this.tool = new DisplayToolkit(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		setScreen(new MainMenuScreen(game));
	}

	@Override
	public void dispose() {
		super.dispose();
		this.tool.dispose();
	}
}
