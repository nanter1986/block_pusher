package com.nanter1986.blockpusher;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Blocks.OutsideWall;
import com.nanter1986.blockpusher.Character.EnemyOne;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Character.PlayerOne;
import com.nanter1986.blockpusher.Map.MapOne;
import com.nanter1986.blockpusher.MenuFragments.InfoPatch;
import com.nanter1986.blockpusher.PowerUps.Bomb;
import com.nanter1986.blockpusher.PowerUps.Item;

import java.util.ArrayList;

/**
 * Created by user on 29/8/2017.
 */

class Gameplay implements Screen, InputProcessor {
    boolean winConditionsMet;
    public int pauseReducer = 0;
    public int numOfSteps = 0;
    public static Preferences prefs = Gdx.app.getPreferences("Pusher");
    MainClass game;
    DisplayToolkit tool;
    MapOne theMap;
    OutsideWall theWall;
    ArrayList<EnemyOne> enemiesArraylist = new ArrayList<EnemyOne>();
    ArrayList<Item> itemsArraylist = new ArrayList<Item>();
    InfoPatch infoPatch;
    boolean gamePaused = false;


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
        infoPatch = new InfoPatch(tool);
        Gdx.app.log("info patch dimensions:", infoPatch.height + "/" + infoPatch.width);
        theMap = new MapOne(tool);
        theWall= new OutsideWall(tool);
        playerone = new PlayerOne(tool, theMap);
        for (int i = 0; i < 5; i++) {
            enemiesArraylist.add(new EnemyOne(tool, theMap));
            itemsArraylist.add(new Bomb(tool, theMap));
            winConditionsMet = false;
        }
        playerone.collectedItems.add(new Bomb(tool, theMap));
        theMap.mapArray[playerone.characterX][playerone.characterY].type = BlockGeneral.Blocktypes.AIR;
    }


    @Override
    public void render(float delta) {
        if (winConditionsMet) {
            int numOfBombs = playerone.collectedItems.size();
            prefs.putInteger("numOfBombs", numOfBombs);
            Gdx.app.log("bombs left:", numOfBombs + "");
            prefs.putInteger("numberOfSteps",numOfSteps);
            Gdx.app.log("total steps:", numOfSteps + "");
            WinScreen win = new WinScreen(game);
            Gdx.app.log("setting new screen to game: ", win.toString());
            game.setScreen(win);
        } else if (gamePaused && pauseReducer == 0 && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gamePaused = false;
            Gdx.app.log("game paused", "false");
        } else if (gamePaused) {
            if (pauseReducer > 0) {
                pauseReducer--;
            }
        } else {
            checkIfWinConditionsAreMet();
            playerone.checkIfAlive(enemiesArraylist);
            playerone.collectItems(itemsArraylist);
            if (playerone.moveReducer > 0) {
                playerone.moveReducer -= 1;
            } else {
                playerone.moveReducer = 8;
                Gdx.app.log("new frame created fps :", (1 / delta) + "");
                updatePosition();
            }
            ArrayList<EnemyOne> toRemoveIfCrushed = new ArrayList<EnemyOne>();
            for (EnemyOne e : enemiesArraylist) {
                boolean crushedAndAnimatedBlood = e.checkIfcrushed(theMap) && e.explodedEnd ;
                Gdx.app.log("explosion ended, ready to remove enemy:", crushedAndAnimatedBlood + "");
                if (crushedAndAnimatedBlood) {
                    toRemoveIfCrushed.add(e);
                }
            }
            for (EnemyOne e : toRemoveIfCrushed) {
                Gdx.app.log("enemy number", enemiesArraylist.size() + "");
                Gdx.app.log("removing enemy", e.toString());
                enemiesArraylist.remove(e);
                Gdx.app.log("enemy number", enemiesArraylist.size() + "");
            }


            tool.camera.position.set(playerone.characterX * tool.universalWidthFactor, playerone.characterY * tool.universalWidthFactor, 0);
            infoPatch.stealPosition(tool);
            Gdx.app.log("info patch position:", infoPatch.positionX + "/" + infoPatch.positionY);
            tool.camera.update();

            Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                    BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            tool.batch.setProjectionMatrix(tool.camera.combined);
            tool.batch.begin();
            for (EnemyOne e : enemiesArraylist) {
                boolean exploding = e.explodedStarted && e.explodedEnd == false;
                Gdx.app.log("enemy exploding", e.characterX + "/" + e.characterY + "/" + exploding);
                if (exploding) {
                    e.bloodAnimation(tool);
                }

            }
            theMap.updatePosition(tool);
            theWall.drawSelf(theMap);
            for (Item item : itemsArraylist) {
                item.updatePosition(tool.batch);
            }
            for (EnemyOne e : enemiesArraylist) {
                boolean enemyAlive=e.explodedStarted==false;
                if(enemyAlive){
                    e.moveEnemy(theMap);
                }

            }
            playerone.updatePosition(tool.batch);

            Gdx.app.log("render----------------------------------------------------------------------\n",
                    "camera position:" + tool.camera.position.toString() +
                            "\nplayer position x:" + playerone.characterX + " y:" + playerone.characterY +
                            "\nplayer direction:" + playerone.dir);
            for (Item item : playerone.collectedItems) {
                Gdx.app.log("item in inventory", item.getClass().toString());
            }
            for (EnemyOne e : enemiesArraylist) {
                if (e.explodedStarted == false) {
                    e.updatePosition(tool.batch);
                    Gdx.app.log("enemy position:", e.characterX + " " + e.characterY + " " +
                            e.dir.toString() + " move reducer:" + e.moveReducer +
                            "\n----------------------------------------------------------------------------------");
                }

            }
            infoPatch.drawSelf(tool, enemiesArraylist, playerone.collectedItems);
            tool.batch.end();

        }
    }


    private void checkIfWinConditionsAreMet() {
        int enemiesLeft = enemiesArraylist.size();
        boolean wonGame = enemiesArraylist.size() == 0;
        if (wonGame) {
            winConditionsMet = true;

        }
        Gdx.app.log("won game: ", wonGame + "");
        Gdx.app.log("enemies left: ", enemiesLeft + "");
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
        if (playerone.stillAlive) {
            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && playerone.characterX > 0) {
                    playerone.dir = MovableCharacter.Direction.LEFT;
                    if (playerone.checkIfBlockAtTheFront(theMap)) {
                        playerone.characterX -= 1;
                        addOneStep();
                    } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.characterX > 1) {
                        playerone.characterX -= 1;
                        addOneStep();
                        theMap.mapArray[playerone.characterX - 1][playerone.characterY].type = theMap.mapArray[playerone.characterX][playerone.characterY].type;
                        theMap.mapArray[playerone.characterX][playerone.characterY].type = BlockGeneral.Blocktypes.AIR;
                    }

                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && playerone.characterX < theMap.MAP_WIDTH_IN_BLOCKS - 1) {
                    playerone.dir = MovableCharacter.Direction.RIGHT;
                    if (playerone.checkIfBlockAtTheFront(theMap)) {
                        playerone.characterX += 1;
                        addOneStep();
                    } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.characterX < theMap.MAP_WIDTH_IN_BLOCKS - 2) {
                        playerone.characterX += 1;
                        addOneStep();
                        theMap.mapArray[playerone.characterX + 1][playerone.characterY].type = theMap.mapArray[playerone.characterX][playerone.characterY].type;
                        theMap.mapArray[playerone.characterX][playerone.characterY].type = BlockGeneral.Blocktypes.AIR;
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && playerone.characterY < theMap.MAP_HEIGHT_IN_BLOCKS - 1) {
                    playerone.dir = MovableCharacter.Direction.UP;
                    if (playerone.checkIfBlockAtTheFront(theMap)) {
                        playerone.characterY += 1;
                        addOneStep();

                    } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.characterY < theMap.MAP_HEIGHT_IN_BLOCKS - 2) {
                        playerone.characterY += 1;
                        addOneStep();
                        theMap.mapArray[playerone.characterX][playerone.characterY + 1].type = theMap.mapArray[playerone.characterX][playerone.characterY].type;
                        theMap.mapArray[playerone.characterX][playerone.characterY].type = BlockGeneral.Blocktypes.AIR;
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && playerone.characterY > 0) {
                    playerone.dir = MovableCharacter.Direction.DOWN;
                    if (playerone.checkIfBlockAtTheFront(theMap)) {
                        playerone.characterY -= 1;
                        addOneStep();

                    } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.characterY > 1) {
                        playerone.characterY -= 1;
                        addOneStep();
                        theMap.mapArray[playerone.characterX][playerone.characterY - 1].type = theMap.mapArray[playerone.characterX][playerone.characterY].type;
                        theMap.mapArray[playerone.characterX][playerone.characterY].type = BlockGeneral.Blocktypes.AIR;
                    }

                } else if (gamePaused == false && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

                    gamePaused = true;
                    pauseReducer = 8;
                    Gdx.app.log("game paused", "true");
                } else if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT) && playerone.collectedItems.size() > 0) {
                    useBombOnBlock();
                }

                playerone.keepPlayerInBounds(theMap.MAP_WIDTH_IN_BLOCKS, theMap.MAP_HEIGHT_IN_BLOCKS);
                //moveReducer = 8;
            }

        }

    }

    private void addOneStep() {
        numOfSteps++;
        Gdx.app.log("total number of steps:", numOfSteps + "");
    }

    private void checkIfBlockRemovableAndRemove(int xToCheck, int yToCheck) {
        Gdx.app.log("checking if block removable at: ", xToCheck + "/" + yToCheck);
        if (theMap.mapArray[xToCheck][yToCheck].type != BlockGeneral.Blocktypes.AIR
                && theMap.mapArray[xToCheck][yToCheck].type != BlockGeneral.Blocktypes.WATER) {
            Gdx.app.log("block destroying:", "at x/y " + xToCheck + "/" + yToCheck +
                    " from " + theMap.mapArray[xToCheck][yToCheck].type.toString() + " to " + BlockGeneral.Blocktypes.AIR.toString());
            theMap.mapArray[xToCheck][yToCheck].explodedStart = true;
            Gdx.app.log("explosion started: ", theMap.mapArray[xToCheck][yToCheck].explodedStart + "");
            theMap.mapArray[xToCheck][yToCheck].type = BlockGeneral.Blocktypes.AIR;
            playerone.collectedItems.remove(0);
            for (Item i : playerone.collectedItems) {
                Gdx.app.log("remaining inventory: ", i.getClass().toString());
            }

        }
    }

    private void useBombOnBlock() {
        switch (playerone.dir) {
            case UP:
                int blockY = playerone.characterY + 1;
                Gdx.app.log("upward y coord:", blockY + "");
                boolean yLessThanTop = playerone.characterY + 1 <= 49;
                Gdx.app.log("is less than top:", yLessThanTop + "");
                if (yLessThanTop) {
                    checkIfBlockRemovableAndRemove(playerone.characterX, playerone.characterY + 1);
                }
                break;
            case DOWN:
                int blockYdown = playerone.characterY + 1;
                Gdx.app.log("downward y coord:", blockYdown + "");
                boolean yMoreThanBottom = playerone.characterY - 1 >= 0;
                Gdx.app.log("is more than bottom:", yMoreThanBottom + "");
                if (yMoreThanBottom) {
                    checkIfBlockRemovableAndRemove(playerone.characterX, playerone.characterY - 1);
                }
                break;
            case LEFT:
                int blockXleft = playerone.characterX - 1;
                Gdx.app.log("left x coord:", blockXleft + "");
                boolean xMoreThanLeftLimit = playerone.characterX - 1 >= 0;
                Gdx.app.log("is more than left limit:", xMoreThanLeftLimit + "");
                if (xMoreThanLeftLimit) {
                    checkIfBlockRemovableAndRemove(playerone.characterX - 1, playerone.characterY);
                }
                break;
            case RIGHT:
                int blockXright = playerone.characterX + 1;
                Gdx.app.log("right x coord:", blockXright + "");
                boolean xLessThanRightLimit = playerone.characterX + 1 <= 49;
                Gdx.app.log("is less than right limit:", xLessThanRightLimit + "");
                if (xLessThanRightLimit) {
                    checkIfBlockRemovableAndRemove(playerone.characterX + 1, playerone.characterY);
                }
                break;

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
