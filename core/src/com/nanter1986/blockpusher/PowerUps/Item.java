package com.nanter1986.blockpusher.PowerUps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Character.MovableCharacter;

/**
 * Created by user on 21/9/2017.
 */

public abstract class Item {
    public int itemX;
    public int itemY;
    public int itemW;
    public int itemH;
    public boolean collected;
    public boolean used;

    public abstract void updatePosition(SpriteBatch b);


}
