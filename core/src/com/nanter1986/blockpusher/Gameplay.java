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
import com.nanter1986.blockpusher.Character.Bosses.BossCharacters.MinionSimple;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Character.PlayerOne;
import com.nanter1986.blockpusher.DataControl.DataControler;
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


    public int pauseReducer = 0;
    public int numOfSteps = 0;
    boolean winConditionsMet;
    int enemiesToGenerate;
    int bombsToGenerate;
    int stepsGoingToBonus;
    MainClass game;
    DisplayToolkit tool;
    MapOne theMap;
    OutsideWall theWall;
    DataControler data;
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
        data = new DataControler(tool);
        infoPatch = new InfoPatch(tool);
        Gdx.app.log("info patch dimensions:", infoPatch.height + "/" + infoPatch.width);
        enemiesToGenerate = 10;
        bombsToGenerate = 5;
        android = Gdx.app.getType() == Application.ApplicationType.Android;
        stepsGoingToBonus = enemiesToGenerate * data.STEPS_PER_ENEMY;
        theMap = new MapOne(tool);
        theWall = new OutsideWall(tool);
        playerone = new PlayerOne(tool, theMap);
        for (int i = 0; i < enemiesToGenerate; i++) {
            enemiesArraylist.add(new MinionSimple(tool, theMap));
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
        theMap.mapArray[playerone.getFixatedX()][playerone.getFixatedY()].type = BlockGeneral.Blocktypes.AIR;
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
        data.putBombs(numOfBombs);
        Gdx.app.log("bombs left:", numOfBombs + "");
        data.putSteps(stepsGoingToBonus);
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
            Gdx.app.log("enemy exploding", e.getFixatedX() + "/" + e.getFixatedY() + "/" + exploding);
            if (exploding) {
                e.bloodAnimation(tool);
            }

        }

        for (Item item : itemsArraylist) {
            item.updatePosition(tool.batch);
        }
        for (MovableCharacter e : enemiesArraylist) {
            boolean enemyAlive = e.explodedStarted == false;
            if (enemyAlive) {
                e.moveCharacter(theMap, enemiesArraylist);
                e.updatePosition(tool.batch, theMap, enemiesArraylist);
            }

        }
        playerone.updatePosition(tool.batch, theMap, enemiesArraylist);

        Gdx.app.log("render----------------------------------------------------------------------\n",
                "is android:" + android +
                        "\ncamera position:" + tool.camera.position.toString() +
                        "\nplayer position x:" + playerone.getFixatedX() + " y:" + playerone.getFixatedY() +
                        "\nplayer direction:" + playerone.dir);
        for (Item item : playerone.collectedItems) {
            Gdx.app.log("item in inventory", item.getClass().toString());
        }
        /*for (MovableCharacter e : enemiesArraylist) {
            if (e.explodedStarted == false) {
                e.updatePosition(tool.batch, theMap, enemiesArraylist);
                Gdx.app.log("enemy position:", e.getFixatedX() + " " + e.getFixatedY() + " " +
                        e.dir.toString() + " move reducer:" + e.moveReducer +
                        "\n----------------------------------------------------------------------------------");
            }

        }*/
        theMap.updatePosition(tool);
        theWall.drawSelf(theMap);
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
            tool.camera.position.set(playerone.coord.realX, playerone.coord.realY, 0);

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
        if (playerone.moveReducer > 1) {
            playerone.moveReducer -= 1;

            playerone.increaseByStep();
        } else if (playerone.moveReducer == 1) {
            playerone.fixatePosition();
            playerone.moveReducer -= 1;
        } else {

            playerone.stepSequenceRunning = false;
            playerone.moveReducer = playerone.moveReducerLimit;
            Gdx.app.log("new frame created fps :", (1 / delta) + " fps");

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

    public void blockSwitcher(BlockGeneral bNext, BlockGeneral bNextNext) {

        bNextNext.type = bNext.type;
        bNext.type = BlockGeneral.Blocktypes.AIR;
        Gdx.app.log("types of switch", bNextNext.type + "/" + bNext.type);
        bNextNext.setTile();
        bNext.setTile();
        Gdx.app.log("tiles of switch", bNextNext.tile + "/" + bNext.tile);
    }

    public void desktopControls() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && playerone.getFixatedX() > 0) {
            playerone.dir = MovableCharacter.Direction.LEFT;
            if (playerone.checkIfBlockAtTheFront(theMap)) {
                playerone.stepSequenceRunning = true;
                addOneStep();

            } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.getFixatedX() > 1) {
                playerone.stepSequenceRunning = true;
                addOneStep();
                BlockGeneral next = theMap.mapArray[playerone.getFixatedX() - 1][playerone.getFixatedY()];
                BlockGeneral nextNext = theMap.mapArray[playerone.getFixatedX() - 2][playerone.getFixatedY()];
                blockSwitcher(next, nextNext);
            }


        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && playerone.getFixatedX() < theMap.MAP_WIDTH_IN_BLOCKS - 1) {
            playerone.dir = MovableCharacter.Direction.RIGHT;
            if (playerone.checkIfBlockAtTheFront(theMap)) {
                playerone.stepSequenceRunning = true;
                addOneStep();
            } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.getFixatedX() < theMap.MAP_WIDTH_IN_BLOCKS - 2) {
                playerone.stepSequenceRunning = true;
                addOneStep();
                BlockGeneral nextNext = theMap.mapArray[playerone.getFixatedX() + 2][playerone.getFixatedY()];
                BlockGeneral next = theMap.mapArray[playerone.getFixatedX() + 1][playerone.getFixatedY()];
                blockSwitcher(next, nextNext);
            }


        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && playerone.getFixatedY() < theMap.MAP_HEIGHT_IN_BLOCKS - 1) {
            playerone.dir = MovableCharacter.Direction.UP;
            if (playerone.checkIfBlockAtTheFront(theMap)) {
                playerone.stepSequenceRunning = true;
                addOneStep();
            } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.getFixatedY() < theMap.MAP_HEIGHT_IN_BLOCKS - 2) {
                playerone.stepSequenceRunning = true;
                addOneStep();
                BlockGeneral nextNext = theMap.mapArray[playerone.getFixatedX()][playerone.getFixatedY() + 2];
                BlockGeneral next = theMap.mapArray[playerone.getFixatedX()][playerone.getFixatedY() + 1];
                blockSwitcher(next, nextNext);
            }


        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && playerone.getFixatedY() > 0) {
            playerone.dir = MovableCharacter.Direction.DOWN;
            if (playerone.checkIfBlockAtTheFront(theMap)) {
                playerone.stepSequenceRunning = true;
                addOneStep();
            } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.getFixatedY() > 1) {
                playerone.stepSequenceRunning = true;
                addOneStep();
                BlockGeneral nextNext = theMap.mapArray[playerone.getFixatedX()][playerone.getFixatedY() - 2];
                BlockGeneral next = theMap.mapArray[playerone.getFixatedX()][playerone.getFixatedY() - 1];
                blockSwitcher(next, nextNext);
            }


        } else if (gamePaused == false && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

            gamePaused = true;
            pauseReducer = 8;
            Gdx.app.log("game paused", "true");
        } else if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT) && playerone.collectedItems.size() > 0) {
            useBombOnBlock();
        } else if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            cameraFollowPlayer = false;
            if (enemyLocatorIndex >= enemiesArraylist.size()) {
                enemyLocatorIndex = 0;
            }
            tool.camera.position.set(enemiesArraylist.get(enemyLocatorIndex).getFixatedX() * tool.universalWidthFactor,
                    enemiesArraylist.get(enemyLocatorIndex).getFixatedY() * tool.universalWidthFactor,
                    0);
            enemyLocatorIndex++;


        } else if (Gdx.input.isKeyPressed(Input.Keys.G)) {
            cameraFollowPlayer = true;
        }

        playerone.keepPlayerInBounds(theMap.MAP_WIDTH_IN_BLOCKS, theMap.MAP_HEIGHT_IN_BLOCKS);
        //moveReducer = 8;
    }

    public void androidControls() {
        if (dirpad.get(2).isButtonTouched() && playerone.getFixatedX() > 0) {
            playerone.dir = MovableCharacter.Direction.LEFT;
            if (playerone.checkIfBlockAtTheFront(theMap)) {
                playerone.stepSequenceRunning = true;
                addOneStep();
            } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.getFixatedX() > 1) {
                playerone.stepSequenceRunning = true;
                addOneStep();
                BlockGeneral next = theMap.mapArray[playerone.getFixatedX() - 1][playerone.getFixatedY()];
                BlockGeneral nextNext = theMap.mapArray[playerone.getFixatedX() - 2][playerone.getFixatedY()];
                blockSwitcher(next, nextNext);

            }

        } else if (dirpad.get(3).isButtonTouched() && playerone.getFixatedX() < theMap.MAP_WIDTH_IN_BLOCKS - 1) {
            playerone.dir = MovableCharacter.Direction.RIGHT;
            if (playerone.checkIfBlockAtTheFront(theMap)) {
                playerone.stepSequenceRunning = true;
                addOneStep();
            } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.getFixatedX() < theMap.MAP_WIDTH_IN_BLOCKS - 2) {
                playerone.stepSequenceRunning = true;
                addOneStep();
                BlockGeneral nextNext = theMap.mapArray[playerone.getFixatedX() + 2][playerone.getFixatedY()];
                BlockGeneral next = theMap.mapArray[playerone.getFixatedX() + 1][playerone.getFixatedY()];
                blockSwitcher(next, nextNext);
            }
        } else if (dirpad.get(0).isButtonTouched() && playerone.getFixatedY() < theMap.MAP_HEIGHT_IN_BLOCKS - 1) {
            playerone.dir = MovableCharacter.Direction.UP;
            if (playerone.checkIfBlockAtTheFront(theMap)) {
                playerone.stepSequenceRunning = true;
                addOneStep();

            } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.getFixatedY() < theMap.MAP_HEIGHT_IN_BLOCKS - 2) {
                playerone.stepSequenceRunning = true;
                addOneStep();
                BlockGeneral nextNext = theMap.mapArray[playerone.getFixatedX()][playerone.getFixatedY() + 2];
                BlockGeneral next = theMap.mapArray[playerone.getFixatedX()][playerone.getFixatedY() + 1];
                blockSwitcher(next, nextNext);

            }
        } else if (dirpad.get(1).isButtonTouched() && playerone.getFixatedY() > 0) {
            playerone.dir = MovableCharacter.Direction.DOWN;
            if (playerone.checkIfBlockAtTheFront(theMap)) {
                playerone.stepSequenceRunning = true;
                addOneStep();

            } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.getFixatedY() > 1) {
                playerone.stepSequenceRunning = true;
                addOneStep();
                BlockGeneral nextNext = theMap.mapArray[playerone.getFixatedX()][playerone.getFixatedY() - 2];
                BlockGeneral next = theMap.mapArray[playerone.getFixatedX()][playerone.getFixatedY() - 1];
                blockSwitcher(next, nextNext);
            }

        } else if (gamePaused == false && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

            gamePaused = true;
            pauseReducer = 8;
            Gdx.app.log("game paused", "true");
        } else if (dirpad.get(4).isButtonTouched() && playerone.collectedItems.size() > 0) {
            useBombOnBlock();
        } else if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            cameraFollowPlayer = false;
            if (enemyLocatorIndex >= enemiesArraylist.size()) {
                enemyLocatorIndex = 0;
            }
            tool.camera.position.set(enemiesArraylist.get(enemyLocatorIndex).getFixatedX() * tool.universalWidthFactor,
                    enemiesArraylist.get(enemyLocatorIndex).getFixatedY() * tool.universalWidthFactor,
                    0);
            enemyLocatorIndex++;


        } else if (Gdx.input.isKeyPressed(Input.Keys.G)) {
            cameraFollowPlayer = true;
        }

        playerone.keepPlayerInBounds(theMap.MAP_WIDTH_IN_BLOCKS, theMap.MAP_HEIGHT_IN_BLOCKS);
        //moveReducer = 8;
    }

    public void updatePosition() {
        if (playerone.stillAlive) {
            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                desktopControls();
            } else if (android) {
                androidControls();
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
                int blockY = playerone.getFixatedY() + 1;
                Gdx.app.log("upward y coord:", blockY + "");
                boolean yLessThanTop = playerone.getFixatedY() + 1 <= 49;
                Gdx.app.log("is less than top:", yLessThanTop + "");
                if (yLessThanTop) {
                    checkIfBlockRemovableAndRemove(playerone.getFixatedX(), playerone.getFixatedY() + 1);
                }
                break;
            case DOWN:
                int blockYdown = playerone.getFixatedY() + 1;
                Gdx.app.log("downward y coord:", blockYdown + "");
                boolean yMoreThanBottom = playerone.getFixatedY() - 1 >= 0;
                Gdx.app.log("is more than bottom:", yMoreThanBottom + "");
                if (yMoreThanBottom) {
                    checkIfBlockRemovableAndRemove(playerone.getFixatedX(), playerone.getFixatedY() - 1);
                }
                break;
            case LEFT:
                int blockXleft = playerone.getFixatedX() - 1;
                Gdx.app.log("left x coord:", blockXleft + "");
                boolean xMoreThanLeftLimit = playerone.getFixatedX() - 1 >= 0;
                Gdx.app.log("is more than left limit:", xMoreThanLeftLimit + "");
                if (xMoreThanLeftLimit) {
                    checkIfBlockRemovableAndRemove(playerone.getFixatedX() - 1, playerone.getFixatedY());
                }
                break;
            case RIGHT:
                int blockXright = playerone.getFixatedX() + 1;
                Gdx.app.log("right x coord:", blockXright + "");
                boolean xLessThanRightLimit = playerone.getFixatedX() + 1 <= 49;
                Gdx.app.log("is less than right limit:", xLessThanRightLimit + "");
                if (xLessThanRightLimit) {
                    checkIfBlockRemovableAndRemove(playerone.getFixatedX() + 1, playerone.getFixatedY());
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
