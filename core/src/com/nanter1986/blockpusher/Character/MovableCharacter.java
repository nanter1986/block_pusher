package com.nanter1986.blockpusher.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.DisplayToolkit;

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
    public int moveReducer;
    public Direction dir;
    public int bloodAnimationX;
    public int bloodAnimationY;
    public boolean explodedStarted;
    public boolean explodedEnd;
    public int bloodDelayNumber;


    public abstract void updatePosition(SpriteBatch b);

    public abstract void bloodAnimation(DisplayToolkit tool);

    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
