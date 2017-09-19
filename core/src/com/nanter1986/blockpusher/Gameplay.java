package com.nanter1986.blockpusher;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.EnemyOne;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Character.PlayerOne;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.ArrayList;

/**
 * Created by user on 29/8/2017.
 */

class Gameplay implements Screen, InputProcessor {

    public int moveReducer = 0;
    static Preferences prefs = Gdx.app.getPreferences("Pusher");
    MainClass game;
    DisplayToolkit tool;
    MapOne theMap;
    ArrayList<EnemyOne>enemiesArraylist=new ArrayList<EnemyOne>();

    private static final Color BACKGROUND_COLOR = new Color(0.5f, 1f, 0f, 1.0f);
    private PlayerOne playerone;


    public Gameplay(MainClass game) {
        this.game = game;
        this.tool = new DisplayToolkit(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.tool.camera.update();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        Gdx.app.log("input processor set to:", Gdx.input.getInputProcessor().toString());
        theMap = new MapOne(tool);
        playerone = new PlayerOne(tool, theMap);
        for(int i=0;i<5;i++){
            enemiesArraylist.add(new EnemyOne(tool, theMap));
        }

        theMap.mapArray[playerone.characterX][playerone.characterY].type = BlockGeneral.Blocktypes.AIR;
    }

    @Override
    public void render(float delta) {

        if (moveReducer > 0) {
            moveReducer -= 1;
        } else {
            updatePosition();
            tool.camera.position.set(playerone.characterX * tool.universalWidthFactor, playerone.characterY * tool.universalWidthFactor, 0);
            tool.camera.update();

            Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                    BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            tool.batch.setProjectionMatrix(tool.camera.combined);
            tool.batch.begin();
            theMap.updatePosition(tool.batch);
            playerone.updatePosition(tool.batch);
            Gdx.app.log("render----------------------------------------------------------------------\n",
                    "camera position:" + tool.camera.position.toString() +
                            "\nplayer position x:" + playerone.characterX + " y:" + playerone.characterY +
                            "\nplayer direction:" + playerone.dir);
            for(EnemyOne e:enemiesArraylist){
                e.updatePosition(tool.batch);
                Gdx.app.log("enemy position:",e.characterX+" "+e.characterY+
                                "\n----------------------------------------------------------------------------------");
            }

            tool.batch.end();

        }


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

    public void updatePosition() {
        if (moveReducer == 0) {
            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && playerone.characterX > 0) {
                    playerone.dir = MovableCharacter.Direction.LEFT;
                    if (playerone.checkIfBlockAtTheFront(theMap)) {
                        playerone.characterX -= 1;
                    } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.characterX > 1) {
                        playerone.characterX -= 1;
                        theMap.mapArray[playerone.characterX - 1][playerone.characterY].type = theMap.mapArray[playerone.characterX][playerone.characterY].type;
                        theMap.mapArray[playerone.characterX][playerone.characterY].type = BlockGeneral.Blocktypes.AIR;
                    }

                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && playerone.characterX < theMap.MAP_WIDTH_IN_BLOCKS - 1) {
                    playerone.dir = MovableCharacter.Direction.RIGHT;
                    if (playerone.checkIfBlockAtTheFront(theMap)) {
                        playerone.characterX += 1;
                    } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.characterX < theMap.MAP_WIDTH_IN_BLOCKS - 2) {
                        playerone.characterX += 1;
                        theMap.mapArray[playerone.characterX + 1][playerone.characterY].type = theMap.mapArray[playerone.characterX][playerone.characterY].type;
                        theMap.mapArray[playerone.characterX][playerone.characterY].type = BlockGeneral.Blocktypes.AIR;
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && playerone.characterY < theMap.MAP_HEIGHT_IN_BLOCKS - 1) {
                    playerone.dir = MovableCharacter.Direction.UP;
                    if (playerone.checkIfBlockAtTheFront(theMap)) {
                        playerone.characterY += 1;

                    } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.characterY < theMap.MAP_HEIGHT_IN_BLOCKS - 2) {
                        playerone.characterY += 1;
                        theMap.mapArray[playerone.characterX][playerone.characterY + 1].type = theMap.mapArray[playerone.characterX][playerone.characterY].type;
                        theMap.mapArray[playerone.characterX][playerone.characterY].type = BlockGeneral.Blocktypes.AIR;
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && playerone.characterY > 0) {
                    playerone.dir = MovableCharacter.Direction.DOWN;
                    if (playerone.checkIfBlockAtTheFront(theMap)) {
                        playerone.characterY -= 1;

                    } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.characterY > 1) {
                        playerone.characterY -= 1;
                        theMap.mapArray[playerone.characterX][playerone.characterY - 1].type = theMap.mapArray[playerone.characterX][playerone.characterY].type;
                        theMap.mapArray[playerone.characterX][playerone.characterY].type = BlockGeneral.Blocktypes.AIR;
                    }

                }

                playerone.keepPlayerInBounds(theMap.MAP_WIDTH_IN_BLOCKS, theMap.MAP_HEIGHT_IN_BLOCKS);
                moveReducer = 8;
            }

        }

    }

    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
