package com.nanter1986.blockpusher;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.nanter1986.blockpusher.Buttons.MainMenuPlayButton;
import com.nanter1986.blockpusher.Buttons.TouchableButton;
import com.nanter1986.blockpusher.DataControl.DataControler;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.ArrayList;

/**
 * Created by user on 19/10/2017.
 */

public class MainMenuScreen implements Screen {

    private static final Color BACKGROUND_COLOR = new Color(0f, 0f, 0f, 1.0f);
    MainClass game;
    DisplayToolkit tool;
    DataControler data;
    int screenLineHeight;
    ArrayList<TouchableButton> buttons = new ArrayList<TouchableButton>();
    private boolean android;

    public MainMenuScreen(MainClass game) {
        this.game = game;
        this.tool = game.tool;
        data = new DataControler(tool);
        screenLineHeight = tool.scH / 10;
        this.tool.camera.update();
        Gdx.app.log("main menu created for game: ", game.toString());
        Gdx.app.log("screen line height:", screenLineHeight + "");
    }

    @Override
    public void show() {
        android = Gdx.app.getType() == Application.ApplicationType.Android;
        buttons.add(new MainMenuPlayButton(tool));
        //tool.prefs.clear();
    }

    @Override
    public void render(float delta) {
        drawEverythingHere();
        takeInput();
    }

    private void takeInput() {
        if (buttons.get(0).isButtonTouched()) {
            tool.prefs.clear();
            data.putStage(220);
            Gameplay gameplay = new Gameplay(game, new MapOne(game.tool), game.tool);
            Gdx.app.log("setting new screen to game: ", gameplay.toString());
            game.setScreen(gameplay);
        }
    }

    private void drawEverythingHere() {
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        for (TouchableButton t : buttons) {
            t.drawSelf(tool);
        }
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
        this.tool.dispose();
    }
}
