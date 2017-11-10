package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.FireBall;
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
 * Created by user on 2/10/2017.
 */

public class Nitar extends MovableCharacter implements GeneralBoss {

    public final Texture playerOne = new Texture(Gdx.files.internal("villain.png"));

    public Nitar(DisplayToolkit tool, GeneralMap map) {
        this.texture = playerOne;
        this.level = 2;
        moveReducerLimit = 32;
        this.characterW = tool.universalWidthFactor;
        this.characterH = tool.universalWidthFactor;
        this.skills = skillSet(tool);
        boolean freeBlockFound = false;
        switch (map.type) {
            case DEBUG_NITAR:
                this.coord = new DoubleCoordSystem(4 * this.characterW,
                        2 * this.characterW,
                        4,
                        2,
                        this.characterW);
                break;
            case DEBUG_ENEMY:

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

    }

    @Override
    public String nameOfBoss() {
        return "Nitar";
    }

    @Override
    public ArrayList<BossSkill> skillSet(DisplayToolkit tool) {
        ArrayList<BossSkill> skills = new ArrayList<BossSkill>();
        skills.add(new FireBall(tool));
        return skills;
    }

    @Override
    public String firstDialog() {
        return "Hello";
    }

    @Override
    public String PainDialog() {
        return "Ouch";
    }

    @Override
    public String BragDialog() {
        return "HAHAHA";
    }

    @Override
    public String WhenBossWinsDialog() {
        return "You are worthless";
    }

    @Override
    public String WhenPlayerWinsDialog() {
        return "It can't beeee...";
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
