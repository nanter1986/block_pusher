package com.nanter1986.blockpusher;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Blocks.OutsideWall;
import com.nanter1986.blockpusher.Buttons.TouchableButton;
import com.nanter1986.blockpusher.Character.Bosses.BossCharacters.Nitar;
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
    private static final Color BACKGROUND_COLOR = new Color(0.5f, 1f, 0f, 1.0f);
    public final int STEPS_MULTIPLIER = 50;
    public int pauseReducer = 0;
    public int numOfSteps = 0;
    boolean winConditionsMet;
    int enemiesToGenerate;
    int bombsToGenerate;
    int stepsGoingToBonus;
    //public static Preferences prefs = Gdx.app.getPreferences("Pusher");
    MainClass game;
    DisplayToolkit tool;
    MapOne theMap;
    OutsideWall theWall;
    ArrayList<MovableCharacter> enemiesArraylist = new ArrayList<MovableCharacter>();
    ArrayList<Item> itemsArraylist = new ArrayList<Item>();
    ArrayList<TouchableButton> dirpad = new ArrayList<TouchableButton>();
    InfoPatch infoPatch;
    boolean gamePaused = false;
    private PlayerOne playerone;
    private int enemyLocatorIndex;
    private boolean cameraFollowPlayer;
    private boolean android;


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
        enemiesToGenerate = 30;
        bombsToGenerate = 5;
        android = Gdx.app.getType() == Application.ApplicationType.Android;
        stepsGoingToBonus = enemiesToGenerate * STEPS_MULTIPLIER;
        theMap = new MapOne(tool);
        theWall = new OutsideWall(tool);
        playerone = new PlayerOne(tool, theMap);
        for (int i = 0; i < enemiesToGenerate; i++) {
            enemiesArraylist.add(new Nitar(tool, theMap));
        }
        for (int i = 0; i < bombsToGenerate; i++) {
            itemsArraylist.add(new Bomb(tool, theMap));
        }
        Gdx.app.log("is android", "boolean test " + android);
        if (android) {
            Gdx.app.log("is android", "yes, creating d pad");
            dirpad = TouchableButton.dirPad(tool);
        }
        playerone.collectedItems.add(new Bomb(tool, theMap));
        theMap.mapArray[playerone.characterX][playerone.characterY].type = BlockGeneral.Blocktypes.AIR;
        winConditionsMet = false;
        cameraFollowPlayer=true;
        //tool.camera.zoom=5f;
        Gdx.app.log("cam info", tool.camera.zoom + "/" + tool.camera.viewportHeight);
    }


    @Override
    public void render(float delta) {
        if (winConditionsMet) {
            doAfterWinConditionsHaveMet();

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
            getPlayerInputIfMoveReducerIsZero(delta);
            removeEnemies();
            cameraOnPlayer();
            infoPatch.stealPosition(tool);
            drawEverythingHere();



        }
    }

    private void doAfterWinConditionsHaveMet() {
        int numOfBombs = playerone.collectedItems.size();
        tool.prefs.putInteger("numOfBombs", numOfBombs);
        Gdx.app.log("bombs left:", numOfBombs + "");
        tool.prefs.putInteger("numberOfSteps", numOfSteps);
        Gdx.app.log("total steps:", numOfSteps + "");
        tool.prefs.putInteger("stepsToBonus", stepsGoingToBonus);
        Gdx.app.log("steps to bonus:", stepsGoingToBonus + "");
        WinScreen win = new WinScreen(game);
        Gdx.app.log("setting new screen to game: ", win.toString());
        game.setScreen(win);
    }

    private void drawEverythingHere() {
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        for (MovableCharacter e : enemiesArraylist) {
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
        for (MovableCharacter e : enemiesArraylist) {
            boolean enemyAlive = e.explodedStarted == false;
            if (enemyAlive) {
                e.moveCharacter(theMap, enemiesArraylist);
            }

        }
        playerone.updatePosition(tool.batch, theMap, enemiesArraylist);

        Gdx.app.log("render----------------------------------------------------------------------\n",
                "is android:" + android +
                        "\ncamera position:" + tool.camera.position.toString() +
                        "\nplayer position x:" + playerone.characterX + " y:" + playerone.characterY +
                        "\nplayer direction:" + playerone.dir);
        for (Item item : playerone.collectedItems) {
            Gdx.app.log("item in inventory", item.getClass().toString());
        }
        for (MovableCharacter e : enemiesArraylist) {
            if (e.explodedStarted == false) {
                e.updatePosition(tool.batch, theMap, enemiesArraylist);
                Gdx.app.log("enemy position:", e.characterX + " " + e.characterY + " " +
                        e.dir.toString() + " move reducer:" + e.moveReducer +
                        "\n----------------------------------------------------------------------------------");
            }

        }
        infoPatch.drawSelf(tool, enemiesArraylist, playerone.collectedItems, playerone);
        Gdx.app.log("is android", "boolean test before draw " + android + " size " + dirpad.size());
        if (android) {
            for (TouchableButton t : dirpad) {
                Gdx.app.log("is android", t.toString());
                t.drawSelf(tool, infoPatch);
            }
        }
        tool.batch.end();
    }

    private void cameraOnPlayer() {
        if(cameraFollowPlayer){
            tool.camera.position.set(playerone.characterX * tool.universalWidthFactor, playerone.characterY * tool.universalWidthFactor, 0);

        }
        tool.camera.update();
    }

    private void removeEnemies() {
        ArrayList<MovableCharacter> toRemoveIfCrushed = new ArrayList<MovableCharacter>();
        for (MovableCharacter e : enemiesArraylist) {
            e.checkIfcrushed(theMap);
            boolean crushedAndAnimatedBlood = e.crushed && e.explodedEnd;
            Gdx.app.log("explosion ended, ready to remove enemy:", crushedAndAnimatedBlood + "");
            if (crushedAndAnimatedBlood) {

                toRemoveIfCrushed.add(e);
                Gdx.app.log("adding to remove", crushedAndAnimatedBlood + " " + e.toString());
            }
        }
        for (MovableCharacter e : toRemoveIfCrushed) {
            Gdx.app.log("enemy number", enemiesArraylist.size() + "");
            Gdx.app.log("removing enemy", e.toString());
            enemiesArraylist.remove(e);
            Gdx.app.log("enemy number", enemiesArraylist.size() + "");
        }
    }

    private void getPlayerInputIfMoveReducerIsZero(float delta) {
        if (playerone.moveReducer > 0) {
            playerone.moveReducer -= 1;
        } else {
            playerone.moveReducer = 8;
            Gdx.app.log("new frame created fps :", (1 / delta) + "");
            updatePosition();
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
                } else if (Gdx.input.isKeyPressed(Input.Keys.F)) {
                    cameraFollowPlayer=false;
                    if (enemyLocatorIndex >= enemiesArraylist.size()) {
                        enemyLocatorIndex = 0;
                    }
                    tool.camera.position.set(enemiesArraylist.get(enemyLocatorIndex).characterX * tool.universalWidthFactor,
                            enemiesArraylist.get(enemyLocatorIndex).characterY * tool.universalWidthFactor,
                            0);
                    enemyLocatorIndex++;


                }else if (Gdx.input.isKeyPressed(Input.Keys.G)) {
                    cameraFollowPlayer=true;
                }

                playerone.keepPlayerInBounds(theMap.MAP_WIDTH_IN_BLOCKS, theMap.MAP_HEIGHT_IN_BLOCKS);
                //moveReducer = 8;
            } else if (android) {

            }

        }

    }

    private void addOneStep() {
        numOfSteps++;
        stepsGoingToBonus--;
        Gdx.app.log("total number of steps:", numOfSteps + "");
        Gdx.app.log("number of steps to bonus:", stepsGoingToBonus + "");
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
