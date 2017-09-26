package com.nanter1986.blockpusher.MenuFragments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.Character.EnemyOne;
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
    public float positionX;
    public float positionY;

    public InfoPatch(DisplayToolkit tool) {
        this.height=tool.scH/8;
        this.width=tool.scW;

    }

    public void stealPosition(DisplayToolkit tool){
        this.positionX=tool.camera.position.x-tool.scW/2;
        this.positionY=tool.camera.position.y-tool.scH/2;
    }

    public void drawSelf(DisplayToolkit tool, ArrayList<EnemyOne>enemies, ArrayList<Item>items){
        tool.batch.draw(blackInfoPatch,positionX,positionY,width,height);
        tool.font.draw(tool.batch,"Enemies left:"+enemies.size(),positionX,positionY+height/3);

        int iterator=0;
        for(Item b:items){
            float displayWidthInPatch=height/3;
            float theX=positionX+iterator*displayWidthInPatch;
            float theY=positionY+(2*height)/3;
            tool.batch.draw(bomb,theX,theY,displayWidthInPatch,displayWidthInPatch);
            Gdx.app.log("bomb drawn:",theX+"/"+theY+"/"+displayWidthInPatch);
            iterator++;
        }

    }
}
