package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BloodAnimator;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BossCrushChecker;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BossMover;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 3/10/2017.
 */

public class MinionSimple extends MovableCharacter implements GeneralBoss {

    public final Texture playerOne = new Texture(Gdx.files.internal("villain.png"));

    public MinionSimple(DisplayToolkit tool, MapOne map) {
        this.texture = playerOne;
        boolean freeBlockFound = false;
        while (freeBlockFound == false) {
            int theX = new Random().nextInt(50);
            int theY = new Random().nextInt(50);
            if (map.mapArray[theX][theY].type == BlockGeneral.Blocktypes.AIR) {
                freeBlockFound = true;
                this.characterX = theX;
                this.characterY = theY;
            }

        }
        this.characterW = tool.universalWidthFactor;
        this.characterH = tool.universalWidthFactor;
        this.level = 1;
        moveReducerLimit = 64;
        Gdx.app.log("enemy creation\n", "Enemy created at x:" + this.characterX +
                "\nat y:" + this.characterY);
    }

    @Override
    public String nameOfBoss() {
        return "Minion";
    }

    @Override
    public ArrayList<BossSkill> skillSet() {
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
    public void updatePosition(SpriteBatch b, MapOne map, ArrayList<MovableCharacter> characters) {
        switch (dir) {
            case UP:
                b.draw(texture, characterX * characterW, characterY * characterW, characterW, characterH, 0, 0, 500, 500, false, false);
                break;
            case DOWN:
                b.draw(texture, characterX * characterW, characterY * characterW, characterW, characterH, 0, 1500, 500, 500, false, false);
                break;
            case LEFT:
                b.draw(texture, characterX * characterW, characterY * characterW, characterW, characterH, 0, 1000, 500, 500, false, false);
                break;
            case RIGHT:
                b.draw(texture, characterX * characterW, characterY * characterW, characterW, characterH, 0, 500, 500, 500, false, false);

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
