package com.nanter1986.blockpusher.DataControl;

import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 12/10/2017.
 */

public class DataControler {

    public final int STEPS_PER_ENEMY = 50;
    public final int BOMBS_MULTIPLIER = 50;
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
    }

    public void putXP(int xp) {
        tool.prefs.getInteger("xpPoints", xp);
    }

    public void putBombs(int bombs) {
        tool.prefs.putInteger("numOfBombs", bombs);
    }

    public void putSteps(int steps) {
        tool.prefs.putInteger("numberOfSteps", steps);
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
}
