package com.nanter1986.blockpusher;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Character.PlayerOne;
import com.nanter1986.blockpusher.Map.MapOne;

/**
 * Created by user on 29/8/2017.
 */

class Gameplay implements Screen {

    static Preferences prefs = Gdx.app.getPreferences("Pusher");
    MainClass game;
    DisplayToolkit tool;
    MapOne theMap;

    private static final Color BACKGROUND_COLOR = new Color(0.5f, 1f, 0f, 1.0f);
    private PlayerOne playerone;

    public Gameplay(MainClass game) {
        this.game = game;
        this.tool=new DisplayToolkit(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        this.tool.camera.update();
    }

    @Override
    public void show() {
        theMap=new MapOne(tool);
        playerone=new PlayerOne(tool);
    }

    @Override
    public void render(float delta) {

        tool.camera.update();
        updatePosition();
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        theMap.updatePosition(tool.batch);
        playerone.updatePosition(tool.batch);
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

    public void updatePosition(){
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                playerone.dir= MovableCharacter.Direction.LEFT;
                playerone.characterX -= tool.scW / 100;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                playerone.dir= MovableCharacter.Direction.RIGHT;
                playerone.characterX += tool.scW / 100;
            }else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                playerone.dir= MovableCharacter.Direction.UP;
                playerone.characterY += tool.scW / 100;
            }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                playerone.dir= MovableCharacter.Direction.DOWN;
                playerone.characterY -= tool.scW / 100;
            }

        }
        Gdx.app.log("pldir",playerone.dir.toString());
    }
}
