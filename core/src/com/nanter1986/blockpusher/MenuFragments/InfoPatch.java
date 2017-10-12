package com.nanter1986.blockpusher.MenuFragments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Character.PlayerOne;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.PowerUps.Item;

import java.util.ArrayList;

/**
 * Created by user on 26/9/2017.
 */

public class InfoPatch {
    public final Texture blackInfoPatch = new Texture(Gdx.files.internal("blackinfopatch.png"));
    public final Texture bomb = new Texture(Gdx.files.internal("bomb.png"));
    public int height;
    public int width;
    public int cellHeight;
    public int cellWidth;
    public float positionX;
    public float positionY;

    public InfoPatch(DisplayToolkit tool) {
        this.height = tool.scH / 12;
        this.width=tool.scW;
        this.cellHeight = height / 2;
        this.cellWidth=width/2;

    }

    public void stealPosition(DisplayToolkit tool){
        this.positionX=tool.camera.position.x-tool.scW/2;
        this.positionY = tool.camera.position.y + tool.scH / 2 - this.height;
        Gdx.app.log("info patch position:", this.positionX + "/" + this.positionY);
    }

    public void drawSelf(DisplayToolkit tool, ArrayList<MovableCharacter> enemies, ArrayList<Item> items, PlayerOne player) {
        tool.batch.draw(blackInfoPatch,positionX,positionY,width,height);
        tool.font.draw(tool.batch,"Enemies left:"+enemies.size(),positionX,positionY+cellHeight);
        tool.font.draw(tool.batch, "x:" + player.getFixatedX() + "/y:" + player.getFixatedY(), positionX + cellWidth, positionY + 3 * cellHeight);
        int iterator=0;
        for(Item b:items){
            float displayWidthInPatch = height / 2;
            float theX=positionX+iterator*displayWidthInPatch;
            float theY = positionY + cellHeight;
            tool.batch.draw(bomb,theX,theY,displayWidthInPatch,displayWidthInPatch);
            Gdx.app.log("bomb drawn:",theX+"/"+theY+"/"+displayWidthInPatch);
            iterator++;
        }

    }
}
