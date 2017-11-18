package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.GeneralMap;

/**
 * Created by user on 18/11/2017.
 */

public class ProjectileStepIncreaser {
    MovableCharacter m;

    public ProjectileStepIncreaser(MovableCharacter m) {
        this.m = m;
    }

    public float getStep() {
        float chW = m.characterW;
        float limit = m.moveReducerLimit;
        return chW / limit;
    }

    public void increaseByStep(GeneralMap map) {
        if (m.stepSequenceRunning) {
            switch (m.dir) {
                case UP:
                    if (m.coord.realY + getStep() <= (map.height) * m.characterW) {
                        m.coord.realY += getStep();
                    }
                    break;
                case DOWN:
                    if (m.coord.realY - getStep() >= -1 * m.characterW) {
                        m.coord.realY -= getStep();
                    }
                    break;
                case RIGHT:

                    if (m.coord.realX + getStep() < (map.width) * m.characterW) {
                        m.coord.realX += getStep();
                    }
                    break;
                case LEFT:
                    if (m.coord.realX - getStep() >= -1 * m.characterW) {
                        m.coord.realX -= getStep();
                    }
                    break;
            }
        }

    }
}
