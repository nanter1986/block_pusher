package com.nanter1986.blockpusher.PowerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.Random;

/**
 * Created by user on 21/9/2017.
 */

public class Bomb extends Item{
    public final Texture playerOne = new Texture(Gdx.files.internal("bomb.png"));

    public Bomb(DisplayToolkit tool, GeneralMap map) {

        this.itemW = tool.universalWidthFactor;
        this.itemH = tool.universalWidthFactor;
        this.collected = false;
        this.used = false;
        switch (map.type) {
            case TUTORIAL1:

                break;
            default:
                boolean freeBlockFound = false;
                while (freeBlockFound == false) {
                    int theX = new Random().nextInt(map.width);
                    int theY = new Random().nextInt(map.height);
                    if (map.mapArray[theX][theY].type == BlockGeneral.Blocktypes.AIR) {
                        freeBlockFound = true;
                        this.itemX = theX;
                        this.itemY = theY;
                    }


                }
        }
        Gdx.app.log("item created:", "item x:" + this.itemX +
                " item y:" + this.itemY +
                " item type:" + this.getClass().toString());
    }

    @Override
    public void updatePosition(SpriteBatch b) {
        b.draw(playerOne,itemX*itemW,itemY*itemW,itemW,itemH);
        Gdx.app.log("item drawn at:",itemX+"/"+itemY);
    }
}
