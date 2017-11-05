package com.nanter1986.blockpusher.Blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 29/8/2017.
 */

public class BlockGeneral {
    public static final Texture airT = new Texture("air.png");

    private static final Texture waterT=new Texture("water.png");
    private static final Texture iceT = new Texture("food.png");

    private static final Texture stoneT=new Texture("stone.png");
    private static final Texture rockT=new Texture("rock.png");


    private static final Texture explosion=new Texture(Gdx.files.internal("explosion.png"));
    public boolean explodedStart;
    public boolean explodedEnd;
    public int blockX;
    public int blockY;
    public Blocktypes type;
    public Texture tile;
    int explosionAnimationX;
    int explosionAnimationY;

    public BlockGeneral(int blockX, int blockY, Blocktypes type) {
        explosionAnimationX=0;
        explosionAnimationY=0;
        explodedStart=false;
        explodedEnd=false;
        this.blockX = blockX;
        this.blockY = blockY;
        this.type = type;
        setTile();
    }

    public void explode(DisplayToolkit tool){
        int whereToExplodeX=blockX*tool.universalWidthFactor;
        int whereToExplodeY=blockY*tool.universalWidthFactor;
        tool.batch.draw(explosion,whereToExplodeX,whereToExplodeY,tool.universalWidthFactor,tool.universalWidthFactor,explosionAnimationX*100,500-explosionAnimationY*100,100,100,false,false);
        explosionAnimationX++;
        if(explosionAnimationX==6){
            explosionAnimationX=0;
            explosionAnimationY++;
        }
        if(explosionAnimationY==6){
            explodedEnd=true;
        }
    }

    public void setTile() {
        switch (this.type) {
            case AIR:
                tile = airT;
                break;
            case STONE:
                tile = stoneT;
                break;
            case WATER:
                tile = waterT;
                break;
            case ICE:
                tile = iceT;
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
