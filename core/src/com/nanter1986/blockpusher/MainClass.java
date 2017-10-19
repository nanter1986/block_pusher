package com.nanter1986.blockpusher;

import com.badlogic.gdx.Game;

public class MainClass extends Game {
	MainClass game;
	
	public MainClass(){
		game=this;
	}

	@Override
	public void create() {
		setScreen(new MainMenuScreen(game));
	}
}
