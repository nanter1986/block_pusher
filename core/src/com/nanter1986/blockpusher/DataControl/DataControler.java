package com.nanter1986.blockpusher.DataControl;

import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GameplayTypes;

/**
 * Created by user on 12/10/2017.
 */

public class DataControler {

    public final int STEPS_PER_ENEMY = 50;
    public final int BOMBS_MULTIPLIER = 50;
    public final int MORE_ENEMIES_EVERY_X_STAGES = 20;
    public final int MORE_BOMBS_EVERY_X_STAGES = 20;
    DisplayToolkit tool;

    public DataControler(DisplayToolkit tool) {
        this.tool = tool;
    }

    public int readLevel() {
        return tool.prefs.getInteger("level", 1);
    }

    public int readXP() {
        return tool.prefs.getInteger("xpPoints", 0);
    }

    public void putLevel(int level) {
        tool.prefs.putInteger("level", level);
        tool.prefs.flush();
    }

    public void putXP(int xp) {
        tool.prefs.putInteger("xpPoints", xp);
        tool.prefs.flush();
    }

    public void putBombs(int bombs) {
        tool.prefs.putInteger("numOfBombs", bombs);
        tool.prefs.flush();
    }

    public void putSteps(int steps) {
        tool.prefs.putInteger("numberOfSteps", steps);
        tool.prefs.flush();
    }

    public int readBombs() {
        return tool.prefs.getInteger("numOfBombs");
    }

    public int readSteps() {
        return tool.prefs.getInteger("numberOfSteps");
    }

    public int bombsToPoints(int numOfBombs) {
        return numOfBombs * BOMBS_MULTIPLIER;
    }

    public int stepsToPoints(int numOfSteps) {
        return numOfSteps;
    }

    public int readStage() {
        return tool.prefs.getInteger("stage", 1);
    }

    public void putStage(int stage) {
        tool.prefs.putInteger("stage", stage);
        tool.prefs.flush();
    }

    public void putGameplayType(GameplayTypes type) {
        tool.prefs.putString("gameplayType", type.toString());
        tool.prefs.flush();
    }

    public String readGameplayType() {
        return tool.prefs.getString("gameplayType", "");
    }
}
