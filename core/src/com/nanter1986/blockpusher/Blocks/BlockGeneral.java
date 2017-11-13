package com.nanter1986.blockpusher.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 29/8/2017.
 */

public class BlockGeneral {
    public boolean explodedStart;
    public boolean explodedEnd;
    public int blockX;
    public int blockY;
    public Blocktypes type;
    public Texture tile;
    int explosionAnimationX;
    int explosionAnimationY;


    public BlockGeneral(DisplayToolkit tool, int blockX, int blockY, Blocktypes type) {
        explosionAnimationX=0;
        explosionAnimationY=0;
        explodedStart=false;
        explodedEnd=false;
        this.blockX = blockX;
        this.blockY = blockY;
        this.type = type;
        setTile(tool);
    }

    public void explode(DisplayToolkit tool) {
        int whereToExplodeX=blockX*tool.universalWidthFactor;
        int whereToExplodeY=blockY*tool.universalWidthFactor;
        tool.batch.draw(tool.manager.get("explosion.png", Texture.class), whereToExplodeX, whereToExplodeY, tool.universalWidthFactor, tool.universalWidthFactor, explosionAnimationX * 100, 500 - explosionAnimationY * 100, 100, 100, false, false);
        explosionAnimationX++;
        if(explosionAnimationX==6){
            explosionAnimationX=0;
            explosionAnimationY++;
        }
        if(explosionAnimationY==6){
            explodedEnd=true;
        }
    }

    public void setTile(DisplayToolkit tool) {
        switch (this.type) {
            case AIR:
                tile = tool.manager.get("air.png", Texture.class);
                break;
            case STONE:
                tile = tool.manager.get("stone.png", Texture.class);
                break;
            case WATER:
                tile = tool.manager.get("water.png", Texture.class);
                break;
            case ICE:
                tile = tool.manager.get("ice.png", Texture.class);
                break;
        }
    }

    public enum Blocktypes {
        AIR,
        STONE,
        WATER,
        ICE

    }
}
