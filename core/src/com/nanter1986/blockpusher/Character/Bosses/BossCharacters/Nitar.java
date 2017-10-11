package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BlockSmash;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BloodAnimator;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BossCrushChecker;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BossMover;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.DoubleCoordSystem;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 2/10/2017.
 */

public class Nitar extends MovableCharacter implements GeneralBoss {

    public final Texture playerOne = new Texture(Gdx.files.internal("villain.png"));

    public Nitar(DisplayToolkit tool, MapOne map) {
        Gdx.app.log("constructor nitar", "in constructor");
        this.texture = playerOne;
        boolean freeBlockFound = false;
        while (freeBlockFound == false) {
            int theX = new Random().nextInt(50);
            int theY = new Random().nextInt(50);
            Gdx.app.log("rXY", theX + "/" + theY);
            if (map.mapArray[theX][theY].type == BlockGeneral.Blocktypes.AIR) {
                freeBlockFound = true;

                this.characterW = tool.universalWidthFactor;
                this.characterH = tool.universalWidthFactor;
                this.coord = new DoubleCoordSystem(theX * this.characterW,
                        theY * this.characterW,
                        theX,
                        theY, this.characterW);

                this.level = 2;
                moveReducerLimit = 32;
                this.skills = skillSet();
                Gdx.app.log("enemy creation\n", "Enemy created at x:" + this.getFixatedX() +
                        "\nat y:" + this.getFixatedY());
            }

        }

    }

    @Override
    public String nameOfBoss() {
        return "Nitar";
    }

    @Override
    public ArrayList<BossSkill> skillSet() {
        ArrayList<BossSkill> skills = new ArrayList<BossSkill>();
        skills.add(new BlockSmash());
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
    public void updatePosition(SpriteBatch b, MapOne map, ArrayList<MovableCharacter> characters) {
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
    public void moveCharacter(MapOne map, ArrayList<MovableCharacter> enemies) {
        new BossMover(this).moveBoss(map, enemies);
    }

    @Override
    public void checkIfcrushed(MapOne map) {
        new BossCrushChecker(this).checkIfcrushed(map);
    }
}
