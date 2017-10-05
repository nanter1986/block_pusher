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
    public int characterX;
    public int characterY;
    public int characterW;
    public int characterH;
    public int level;
    public int moveReducer = 0;
    public int moveReducerLimit;
    public float distancePerFrame;
    public float whereToDrawX;
    public float whereToDrawY;
    public boolean doingSmooth = false;
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

    public void smoothAnimation() {
        if (doingSmooth) {
            switch (dir) {
                case UP:
                    whereToDrawY += distancePerFrame;
                    break;
                case DOWN:
                    whereToDrawY -= distancePerFrame;
                    break;
                case LEFT:
                    whereToDrawX -= distancePerFrame;
                    break;
                case RIGHT:
                    whereToDrawX += distancePerFrame;
                    break;

            }

        }

    }

    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
