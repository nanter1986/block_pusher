package com.nanter1986.blockpusher.Character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by user on 29/8/2017.
 */

public abstract class MovableCharacter {
    public int characterX;
    public int characterY;
    public int characterW;
    public int characterH;


    public Direction dir;


    public abstract void updatePosition(SpriteBatch b);

    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
