package com.nanter1986.blockpusher;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.nanter1986.blockpusher.Buttons.NextWinScreenButton;
import com.nanter1986.blockpusher.Buttons.TouchableButton;
import com.nanter1986.blockpusher.DataControl.DataControler;
import com.nanter1986.blockpusher.Map.GameplayTypes;
import com.nanter1986.blockpusher.Map.MapOne;
import com.nanter1986.blockpusher.Map.TutorialMaps.TutorialTwo;

import java.util.ArrayList;

/**
 * Created by user on 23/9/2017.
 */

public class WinScreen implements Screen{

    private static final Color BACKGROUND_COLOR = new Color(0.5f, 1f, 0f, 1.0f);
    private static final int XpPerLevel = 1000;
    MainClass game;
    DisplayToolkit tool;
    DataControler data;
    int screenLineHeight;
    ArrayList<TouchableButton> buttons = new ArrayList<TouchableButton>();
    int numOfBombs;
    int numOfSteps;
    int xpGained;
    int xpForNextLevel;
    GameplayTypes previosGameplayType;
    private boolean android;



    public WinScreen(MainClass game) {
        this.game = game;
        this.tool = new DisplayToolkit(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        data = new DataControler(tool);
        screenLineHeight=tool.scH/10;
        this.tool.camera.update();
        Gdx.app.log("win screen created for game: ",game.toString());
        Gdx.app.log("screen line height:",screenLineHeight+"");
    }

    @Override
    public void show() {
        android = Gdx.app.getType() == Application.ApplicationType.Android;
        buttons.add(new NextWinScreenButton(tool));
        numOfBombs = data.readBombs();
        numOfSteps = data.readSteps();
        Gdx.app.log("type", data.readGameplayType());
        previosGameplayType = GameplayTypes.valueOf(data.readGameplayType());
        boolean numOfStepsSmallerThanZero = numOfSteps < 0;
        if (numOfStepsSmallerThanZero) {
            numOfSteps = 0;
        }
        xpGained = data.stepsToPoints(numOfSteps) + data.bombsToPoints(numOfBombs) + data.readStage() * 100;
        calculateLevelsAndXP();
    }

    @Override
    public void render(float delta) {
        drawEverythingHere();
        takeInput();
    }

    private void takeInput() {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            desktopControls();
        } else if (android) {
            androidControls();
        }
    }

    private void androidControls() {
        if (buttons.get(0).isButtonTouched()) {
            Gameplay gameplay = new Gameplay(game, new MapOne(tool), game.tool);
            Gdx.app.log("setting new screen to game: ", gameplay.toString());
            game.setScreen(gameplay);
        }
    }

    private void desktopControls() {
        if (buttons.get(0).isButtonTouched()) {
            tool.prefs.flush();
            switch (previosGameplayType) {
                case TUTORIAL1:
                    Gameplay tut2 = new Gameplay(game, new TutorialTwo(tool), game.tool);
                    Gdx.app.log("setting new screen to game: ", tut2.toString());
                    game.setScreen(tut2);
                    break;
                case TUTORIAL2:
                    Gameplay regular1 = new Gameplay(game, new MapOne(tool), game.tool);
                    Gdx.app.log("setting new screen to game: ", regular1.toString());
                    game.setScreen(regular1);
                    break;
                case REGULAR:
                    Gameplay regular2 = new Gameplay(game, new MapOne(tool), game.tool);
                    Gdx.app.log("setting new screen to game: ", regular2.toString());
                    game.setScreen(regular2);
                    break;
            }


        }
    }

    private void drawEverythingHere() {
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();

        tool.font.draw(tool.batch,"bombs:"+numOfBombs+"",0,1*screenLineHeight);
        tool.font.draw(tool.batch,"steps:"+numOfSteps+"",0,2*screenLineHeight);
        tool.font.draw(tool.batch, "xp gained:" + xpGained + "", 0, 3 * screenLineHeight);
        tool.font.draw(tool.batch, "level:" + data.readLevel() + "", 0, 4 * screenLineHeight);
        tool.font.draw(tool.batch, "xp for next level:" + xpForNextLevel + "", 0, 5 * screenLineHeight);
        for (TouchableButton t : buttons) {
            t.drawSelf(tool);
        }
        tool.batch.end();
    }

    private void calculateLevelsAndXP() {
        int newXpTotal = xpGained + data.readXP();
        int levels = data.readLevel();
        while (newXpTotal >= XpPerLevel) {
            newXpTotal -= XpPerLevel;
            levels++;
            Gdx.app.log("level gained", "+1");
        }

        data.putLevel(levels);
        data.putXP(newXpTotal);
        int nextStage = data.readStage() + 1;
        data.putStage(nextStage);
        xpForNextLevel = XpPerLevel - data.readXP();
        Gdx.app.log("winscreen",
                "next stage:" + nextStage +
                        "\nlevels:" + levels +
                        "\nxpTotal:" + data.readXP() +
                        "\nxp for next level:" + xpForNextLevel);
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
