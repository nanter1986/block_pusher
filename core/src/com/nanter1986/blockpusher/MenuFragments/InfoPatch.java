package com.nanter1986.blockpusher.MenuFragments;

import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Character.PlayerOne;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.PowerUps.Item;

import java.util.ArrayList;

/**
 * Created by user on 26/9/2017.
 */

public class InfoPatch extends GeneralMenuFragement {
    public InfoPatch(DisplayToolkit tool) {
        this.texture = tool.manager.get("blackinfopatch.png", Texture.class);
        this.height = tool.scH / 12;
        this.width=tool.scW;
        this.cellHeight = height / 2;
        this.cellWidth=width/2;

    }


    public void drawSelf(DisplayToolkit tool, ArrayList<MovableCharacter> enemies, ArrayList<Item> items, PlayerOne player, int stage) {
        drawFragment(tool);
        tool.font.draw(tool.batch,"Enemies left:"+enemies.size(),positionX,positionY+cellHeight);
        tool.font.draw(tool.batch, "Stage:" + stage, positionX + cellWidth, positionY + cellHeight);
        int iterator=0;
        for(Item b:items){
            float displayWidthInPatch = height / 2;
            float theX=positionX+iterator*displayWidthInPatch;
            float theY = positionY + cellHeight;
            tool.batch.draw(tool.manager.get("bomb.png", Texture.class), theX, theY, displayWidthInPatch, displayWidthInPatch);
            iterator++;
        }

    }
}
