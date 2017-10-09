package com.nanter1986.blockpusher.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.ArrayList;

/**
 * Created by user on 29/8/2017.
 */

public abstract class MovableCharacter {
    public final Texture blood = new Texture(Gdx.files.internal("blood.png"));
    public Texture texture;

    //public int characterX;
    //public int characterY;
    public float realX;
    public float realY;
    public int characterW;
    public int characterH;
    public int level;
    public int moveReducer = 0;
    public int moveReducerLimit;
    public boolean stepSequenceRunning = false;


    public Direction dir = Direction.UP;

    public int bloodAnimationX = 0;
    public int bloodAnimationY = 0;
    public boolean explodedStarted = false;
    public boolean explodedEnd = false;
    public boolean crushed = false;
    public int bloodDelayNumber = 0;

    public ArrayList<BossSkill> skills = new ArrayList<BossSkill>();


    public abstract void updatePosition(SpriteBatch b, MapOne map, ArrayList<MovableCharacter> characters);

    public abstract void bloodAnimation(DisplayToolkit tool);

    public abstract void moveCharacter(MapOne map, ArrayList<MovableCharacter> enemies);

    public abstract void checkIfcrushed(MapOne map);


    public void increaseByStep() {
        if (stepSequenceRunning) {
            switch (dir) {
                case UP:
                    if (realY + getStep() < 49 * characterW) {
                        realY += getStep();
                    }
                    break;
                case DOWN:
                    if (realY - getStep() > 0) {
                        realY -= getStep();
                    }
                    break;
                case RIGHT:
                    if (realX + getStep() < 49 * characterW) {
                        realX += getStep();
                    }
                    break;
                case LEFT:
                    if (realX - getStep() > 0) {
                        realX -= getStep();
                    }
                    break;
            }
        }

    }

    public int getFixatedX() {
        Gdx.app.log("fixX", this.realX + "/" + this.characterW + "=" + Math.round(this.realX / this.characterW));
        return Math.round(this.realX) / this.characterW;

    }

    public int getFixatedY() {
        Gdx.app.log("fixY", this.realY + "/" + this.characterW + "=" + Math.round(this.realY / this.characterW));
        return Math.round(this.realY) / this.characterW;
    }

    public float getStep() {
        float chW = characterW;
        float limit = moveReducerLimit;
        return chW / limit;
    }

    public void fixatePosition() {
        this.realX = getFixatedX() * characterW;
        this.realY = getFixatedY() * characterW;
    }

    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
