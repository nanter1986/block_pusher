package com.nanter1986.blockpusher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by user on 23/9/2017.
 */

public class WinScreen implements Screen{

    MainClass game;
    DisplayToolkit tool;
    int screenLineHeight;

    private static final Color BACKGROUND_COLOR = new Color(0.5f, 1f, 0f, 1.0f);



    public WinScreen(MainClass game) {
        this.game = game;
        this.tool = new DisplayToolkit(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        screenLineHeight=tool.scH/10;
        this.tool.camera.update();
        Gdx.app.log("win screen created for game: ",game.toString());
        Gdx.app.log("screen line height:",screenLineHeight+"");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        int numOfBombs=tool.prefs.getInteger("numOfBombs");
        int numOfSteps=tool.prefs.getInteger("numOfsteps");
        tool.font.draw(tool.batch,"bombs:"+numOfBombs+"",0,1*screenLineHeight);
        tool.font.draw(tool.batch,"steps:"+numOfSteps+"",0,2*screenLineHeight);
        tool.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
