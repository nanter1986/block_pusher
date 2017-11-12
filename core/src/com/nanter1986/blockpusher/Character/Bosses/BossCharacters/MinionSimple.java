package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BloodAnimator;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BossCrushChecker;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BossMover;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.DoubleCoordSystem;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.StepIncreaser;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 3/10/2017.
 */

public class MinionSimple extends MovableCharacter implements GeneralBoss {


    public MinionSimple(DisplayToolkit tool, GeneralMap map) {
        this.texture = tool.manager.get("villain.png", Texture.class);
        this.characterW = tool.universalWidthFactor;
        this.characterH = tool.universalWidthFactor;
        this.level = 1;
        moveReducerLimit = 64;
        switch (map.type) {
            case DEBUG_ENEMY:
                this.coord = new DoubleCoordSystem(4 * this.characterW,
                        2 * this.characterW,
                        4,
                        2,
                        this.characterW);
                break;
            case TUTORIAL1:
                this.coord = new DoubleCoordSystem(4 * this.characterW,
                        2 * this.characterW,
                        4,
                        2,
                        this.characterW);
                break;
            case TUTORIAL2:
                this.coord = new DoubleCoordSystem(4 * this.characterW,
                        2 * this.characterW,
                        4,
                        2,
                        this.characterW);
                break;
            default:
                boolean freeBlockFound = false;
                while (freeBlockFound == false) {
                    int theX = new Random().nextInt(map.width);
                    int theY = new Random().nextInt(map.height);
                    if (map.mapArray[theX][theY].type == BlockGeneral.Blocktypes.AIR) {
                        freeBlockFound = true;
                        this.coord = new DoubleCoordSystem(theX * this.characterW,
                                theY * this.characterW,
                                theX,
                                theY,
                                this.characterW);

                    }

                }
        }
        Gdx.app.log("enemy creation\n", "Enemy created at x:" + this.coord.getFixatedX() +
                "\nat y:" + this.coord.getFixatedY());

    }

    @Override
    public String nameOfBoss() {
        return "Minion";
    }

    @Override
    public ArrayList<BossSkill> skillSet(DisplayToolkit tool) {
        return null;
    }

    @Override
    public String firstDialog() {
        return null;
    }

    @Override
    public String PainDialog() {
        return null;
    }

    @Override
    public String BragDialog() {
        return null;
    }

    @Override
    public String WhenBossWinsDialog() {
        return null;
    }

    @Override
    public String WhenPlayerWinsDialog() {
        return null;
    }

    @Override
    public void updatePosition(SpriteBatch b, GeneralMap map, ArrayList<MovableCharacter> characters) {
        switch (dir) {
            case UP:
                b.draw(texture, this.coord.realX, this.coord.realY, characterW, characterH, 0, 0, 500, 500, false, false);
                break;
            case DOWN:
                b.draw(texture, this.coord.realX, this.coord.realY, characterW, characterH, 0, 1500, 500, 500, false, false);
                break;
            case LEFT:
                b.draw(texture, this.coord.realX, this.coord.realY, characterW, characterH, 0, 1000, 500, 500, false, false);
                break;
            case RIGHT:
                b.draw(texture, this.coord.realX, this.coord.realY, characterW, characterH, 0, 500, 500, 500, false, false);

                break;

        }
    }

    @Override
    public void bloodAnimation(DisplayToolkit tool) {
        new BloodAnimator(this).bloodAnimation(tool);
    }

    @Override
    public void moveCharacter(GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<MovableCharacter> projectiles) {
        new BossMover(this).moveBoss(map, enemies, projectiles);
    }

    @Override
    public void checkIfcrushed(GeneralMap map) {
        new BossCrushChecker(this).checkIfcrushed(map);
    }

    @Override
    public void increaseByStep(GeneralMap map) {
        new StepIncreaser(this).increaseByStep(map);
    }
}
