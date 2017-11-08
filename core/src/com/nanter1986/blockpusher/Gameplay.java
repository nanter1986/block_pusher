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
import com.nanter1986.blockpusher.Character.Bosses.BossCharacters.Nitar;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Character.PlayerOne;
import com.nanter1986.blockpusher.DataControl.DataControler;
import com.nanter1986.blockpusher.Map.GeneralMap;
import com.nanter1986.blockpusher.MenuFragments.DialogBox;
import com.nanter1986.blockpusher.MenuFragments.DialogChooser;
import com.nanter1986.blockpusher.MenuFragments.InfoPatch;
import com.nanter1986.blockpusher.PowerUps.Bomb;
import com.nanter1986.blockpusher.PowerUps.Item;

import java.util.ArrayList;

/**
 * Created by user on 29/8/2017.
 */

class Gameplay implements Screen, InputProcessor {
    private static final Color BACKGROUND_COLOR = new Color(0.5f, 1f, 0f, 1.0f);
    private final int BOSS_FREQUENCY = 20;
    public int pauseReducer = 0;
    public int numOfSteps = 0;
    int stage;
    int dialogSenction;
    boolean winConditionsMet;
    int enemiesToGenerate;
    int bombsToGenerate;
    int stepsGoingToBonus;
    MainClass game;
    DisplayToolkit tool;
    GeneralMap theMap;
    DialogChooser dialog;
    OutsideWall theWall;
    ArrayList<MovableCharacter> enemiesArraylist = new ArrayList<MovableCharacter>();
    ArrayList<Item> itemsArraylist = new ArrayList<Item>();
    ArrayList<TouchableButton> dirpad = new ArrayList<TouchableButton>();
    InfoPatch infoPatch;
    boolean gamePaused = false;
    boolean isStageDevidedByBossFrecuency;
    private PlayerOne playerone;
    private int enemyLocatorIndex;
    private boolean cameraFollowPlayer;
    private boolean android;
    private boolean showingDialog;
    private boolean doneInitialStop;


    public Gameplay(MainClass game, GeneralMap theMap) {
        this.game = game;
        this.theMap = theMap;
        this.tool = new DisplayToolkit(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.tool.camera.update();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        tool.data = new DataControler(tool);
        infoPatch = new InfoPatch(tool);
        stage = tool.data.readStage();
        dialogSenction = 0;
        dialog = new DialogChooser();
        enemiesToGenerate = howManyEnemiesToGenerate();
        bombsToGenerate = howManyBombsToGenerate();
        android = Gdx.app.getType() == Application.ApplicationType.Android;
        showingDialog = true;
        gamePaused = false;
        stepsGoingToBonus = enemiesToGenerate * tool.data.STEPS_PER_ENEMY;
        theWall = new OutsideWall(tool);
        playerone = new PlayerOne(tool, theMap);
        isStageDevidedByBossFrecuency = stage % BOSS_FREQUENCY == 0;
        spawnEnemies();
        spawnBombs();
        playerGetsItems();
        if (android) {
            dirpad = TouchableButton.dirPad(tool);
        }
        theMap.mapArray[playerone.getFixatedX()][playerone.getFixatedY()].type = BlockGeneral.Blocktypes.AIR;
        winConditionsMet = false;
        cameraFollowPlayer=true;

    }

    private void playerGetsItems() {
        switch (theMap.type) {
            case TUTORIAL1:

                break;
            default:
                playerone.collectedItems.add(new Bomb(tool, theMap));
        }

    }

    private void spawnBombs() {
        switch (theMap.type) {
            case DEBUG_NITAR:

                break;
            case DEBUG_ENEMY:

                break;
            case TUTORIAL1:

                break;
            case TUTORIAL2:
                itemsArraylist.add(new Bomb(tool, theMap));
                break;
            default:
                for (int i = 0; i < bombsToGenerate; i++) {
                    itemsArraylist.add(new Bomb(tool, theMap));
                }
        }

    }

    private void spawnEnemies() {
        switch (theMap.type) {
            case DEBUG_NITAR:
                enemiesArraylist.add(new Nitar(tool, theMap));
                break;
            case DEBUG_ENEMY:
                enemiesArraylist.add(new MinionSimple(tool, theMap));
                enemiesArraylist.add(new MinionSimple(tool, theMap));
                enemiesArraylist.get(1).coord.fixatedX = 3;
                enemiesArraylist.get(1).coord.realX = 3 * enemiesArraylist.get(1).characterW;
                break;
            case TUTORIAL1:
                enemiesArraylist.add(new MinionSimple(tool, theMap));
                break;
            case TUTORIAL2:
                enemiesArraylist.add(new MinionSimple(tool, theMap));
                break;
            default:
                if (isStageDevidedByBossFrecuency) {
                    for (int i = 0; i < enemiesToGenerate; i++) {
                        enemiesArraylist.add(new Nitar(tool, theMap));
                    }
                } else {
                    for (int i = 0; i < enemiesToGenerate; i++) {
                        enemiesArraylist.add(new MinionSimple(tool, theMap));
                    }
                }
        }


    }

    private int howManyBombsToGenerate() {
        int numOfBombs = (stage / tool.data.MORE_BOMBS_EVERY_X_STAGES) + 5;
        return numOfBombs;
    }

    private int howManyEnemiesToGenerate() {
        int numOfEnemies = (stage / tool.data.MORE_ENEMIES_EVERY_X_STAGES) + 1;
        return numOfEnemies;
    }


    @Override
    public void render(float delta) {
        boolean playerDied = !playerone.stillAlive;
        if (playerDied && playerone.explodedEnd) {
            tool.prefs.flush();
            DeathScreen deathScreen = new DeathScreen(game);
            game.setScreen(deathScreen);
            this.dispose();
        } else if (winConditionsMet) {
            doAfterWinConditionsHaveMet();

        } else if (gamePaused && pauseReducer == 0 && Gdx.input.justTouched() && showingDialog) {
            gamePaused = false;
            showingDialog = false;
        } else if (gamePaused && pauseReducer == 0 && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gamePaused = false;
        } else if (gamePaused) {
            if (pauseReducer > 0) {
                pauseReducer--;
            }
        } else {
            checkIfWinConditionsAreMet();
            playerone.checkIfAlive(enemiesArraylist);
            playerone.collectItems(itemsArraylist);
            getPlayerInputIfMoveReducerIsZero(delta);
            moveEnemies();
            removeEnemies();
            cameraOnPlayer();
            infoPatch.stealPosition(tool);
            drawEverythingHere();
            initialStop();


        }

    }

    private void moveEnemies() {
        for (MovableCharacter e : enemiesArraylist) {
            boolean enemyAlive = e.explodedStarted == false;
            if (enemyAlive) {
                e.moveCharacter(theMap, enemiesArraylist);
            }

        }
    }

    private void initialStop() {
        if (!doneInitialStop) {
            showingDialog = true;
            gamePaused = true;
            doneInitialStop = true;
        }

    }

    private void doAfterWinConditionsHaveMet() {
        int numOfBombs = playerone.collectedItems.size();
        tool.data.putBombs(numOfBombs);
        tool.data.putSteps(stepsGoingToBonus);
        WinScreen win = new WinScreen(game);
        tool.data.putGameplayType(theMap.type);
        tool.prefs.flush();
        game.setScreen(win);
    }

    private void drawEverythingHere() {
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        theMap.updatePositionBelow(tool);
        for (MovableCharacter e : enemiesArraylist) {
            boolean exploding = e.explodedStarted && e.explodedEnd == false;
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
                //e.moveCharacter(theMap, enemiesArraylist);
                e.updatePosition(tool.batch, theMap, enemiesArraylist);
            }

        }
        playerone.updatePosition(tool.batch, theMap, enemiesArraylist);

        for (Item item : playerone.collectedItems) {
            Gdx.app.log("item in inventory", item.getClass().toString());
        }

        theMap.updatePositionAbove(tool);
        theWall.drawSelf(theMap);
        infoPatch.drawSelf(tool, enemiesArraylist, playerone.collectedItems, playerone, stage);
        if (android) {
            for (TouchableButton t : dirpad) {
                t.drawSelf(tool, infoPatch);
            }
        }
        if (playerone.explodedStarted) {
            playerone.bloodAnimation(tool);
        }
        if (showingDialog) {
            new DialogBox(tool, infoPatch).drawText(tool, dialog.giveDialog(theMap, stage, dialogSenction));
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
            if (crushedAndAnimatedBlood) {

                toRemoveIfCrushed.add(e);
            }
        }
        for (MovableCharacter e : toRemoveIfCrushed) {

            enemiesArraylist.remove(e);

        }
    }

    private void getPlayerInputIfMoveReducerIsZero(float delta) {

        if (playerone.moveReducer > 1) {
            playerone.moveReducer -= 1;

            playerone.increaseByStep(theMap);
        } else if (playerone.moveReducer == 1) {
            playerone.fixatePosition();
            playerone.moveReducer -= 1;
        } else {

            playerone.stepSequenceRunning = false;
            playerone.moveReducer = playerone.moveReducerLimit;
            updatePosition();

        }
    }


    private void checkIfWinConditionsAreMet() {
        int enemiesLeft = enemiesArraylist.size();
        boolean wonGame = enemiesArraylist.size() == 0;
        if (wonGame) {
            winConditionsMet = true;

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

    public void blockSwitcher(BlockGeneral bNext, BlockGeneral bNextNext) {

        bNextNext.type = bNext.type;
        bNext.type = BlockGeneral.Blocktypes.AIR;

        bNextNext.setTile();
        bNext.setTile();

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


        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && playerone.getFixatedX() < theMap.width - 1) {
            playerone.dir = MovableCharacter.Direction.RIGHT;
            if (playerone.checkIfBlockAtTheFront(theMap)) {
                playerone.stepSequenceRunning = true;
                addOneStep();
            } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.getFixatedX() < theMap.width - 2) {
                playerone.stepSequenceRunning = true;
                addOneStep();
                BlockGeneral nextNext = theMap.mapArray[playerone.getFixatedX() + 2][playerone.getFixatedY()];
                BlockGeneral next = theMap.mapArray[playerone.getFixatedX() + 1][playerone.getFixatedY()];
                blockSwitcher(next, nextNext);
            }


        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && playerone.getFixatedY() < theMap.height - 1) {
            playerone.dir = MovableCharacter.Direction.UP;
            if (playerone.checkIfBlockAtTheFront(theMap)) {
                playerone.stepSequenceRunning = true;
                addOneStep();
            } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.getFixatedY() < theMap.height - 2) {
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
        } else if (Gdx.input.isKeyPressed(Input.Keys.H)) {
            playerone.stillAlive = false;
            playerone.explodedStarted = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
            for (MovableCharacter m : enemiesArraylist) {
                m.crushed = true;
                m.explodedStarted = true;
            }
        }

        playerone.keepPlayerInBounds(theMap.width, theMap.height);
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

        } else if (dirpad.get(3).isButtonTouched() && playerone.getFixatedX() < theMap.width - 1) {
            playerone.dir = MovableCharacter.Direction.RIGHT;
            if (playerone.checkIfBlockAtTheFront(theMap)) {
                playerone.stepSequenceRunning = true;
                addOneStep();
            } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.getFixatedX() < theMap.width - 2) {
                playerone.stepSequenceRunning = true;
                addOneStep();
                BlockGeneral nextNext = theMap.mapArray[playerone.getFixatedX() + 2][playerone.getFixatedY()];
                BlockGeneral next = theMap.mapArray[playerone.getFixatedX() + 1][playerone.getFixatedY()];
                blockSwitcher(next, nextNext);
            }
        } else if (dirpad.get(0).isButtonTouched() && playerone.getFixatedY() < theMap.height - 1) {
            playerone.dir = MovableCharacter.Direction.UP;
            if (playerone.checkIfBlockAtTheFront(theMap)) {
                playerone.stepSequenceRunning = true;
                addOneStep();

            } else if (playerone.checkIfAirBehindBlock(theMap) && playerone.checkIfWater(theMap) && playerone.getFixatedY() < theMap.height - 2) {
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

        playerone.keepPlayerInBounds(theMap.width, theMap.height);
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
    }

    private void checkIfBlockRemovableAndRemove(int xToCheck, int yToCheck) {

        if (theMap.mapArray[xToCheck][yToCheck].type != BlockGeneral.Blocktypes.AIR
                && theMap.mapArray[xToCheck][yToCheck].type != BlockGeneral.Blocktypes.WATER) {
            theMap.mapArray[xToCheck][yToCheck].explodedStart = true;
            theMap.mapArray[xToCheck][yToCheck].type = BlockGeneral.Blocktypes.AIR;
            playerone.collectedItems.remove(0);

        }
    }

    private void useBombOnBlock() {
        switch (playerone.dir) {
            case UP:
                int blockY = playerone.getFixatedY() + 1;
                boolean yLessThanTop = playerone.getFixatedY() + 1 < theMap.height;
                if (yLessThanTop) {
                    checkIfBlockRemovableAndRemove(playerone.getFixatedX(), playerone.getFixatedY() + 1);
                }
                break;
            case DOWN:
                int blockYdown = playerone.getFixatedY() + 1;
                boolean yMoreThanBottom = playerone.getFixatedY() - 1 >= 0;
                if (yMoreThanBottom) {
                    checkIfBlockRemovableAndRemove(playerone.getFixatedX(), playerone.getFixatedY() - 1);
                }
                break;
            case LEFT:
                int blockXleft = playerone.getFixatedX() - 1;
                boolean xMoreThanLeftLimit = playerone.getFixatedX() - 1 >= 0;
                if (xMoreThanLeftLimit) {
                    checkIfBlockRemovableAndRemove(playerone.getFixatedX() - 1, playerone.getFixatedY());
                }
                break;
            case RIGHT:
                int blockXright = playerone.getFixatedX() + 1;
                boolean xLessThanRightLimit = playerone.getFixatedX() + 1 < theMap.width;
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
