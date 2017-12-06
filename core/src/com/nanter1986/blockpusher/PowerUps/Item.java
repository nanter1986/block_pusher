package com.nanter1986.blockpusher.PowerUps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Map.GeneralMap;

/**
 * Created by user on 21/9/2017.
 */

public abstract class Item implements Disposable {
    public int itemX;
    public int itemY;
    public int itemW;
    public int itemH;
    public boolean collected;
    public boolean used;
    public Texture texture;

    public abstract void updatePosition(SpriteBatch b);


    @Override
    public void dispose() {
        this.texture = null;
    }

    public boolean checkIfcrushed(GeneralMap theMap) {
        boolean crushed = false;
        if (theMap.mapArray[itemX][itemY].type == BlockGeneral.Blocktypes.STONE
                || theMap.mapArray[itemX][itemY].type == BlockGeneral.Blocktypes.WATER) {
            crushed = true;
        }
        return crushed;
    }
}
