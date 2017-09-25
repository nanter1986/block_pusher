package com.nanter1986.blockpusher.Blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 29/8/2017.
 */

public class BlockGeneral {
    private static final Texture airT=new Texture("air.png");
    private static final Texture woodT=new Texture("wood.png");
    private static final Texture waterT=new Texture("water.png");
    private static final Texture foodT=new Texture("food.png");
    private static final Texture stoneT=new Texture("stone.png");
    private static final Texture rockT=new Texture("rock.png");
    private static final Texture playerOne = new Texture(Gdx.files.internal("playerone.png"));

    private static final Texture explosion=new Texture(Gdx.files.internal("explosion.png"));
    int explosionAnimationX;
    int explosionAnimationY;
    public boolean explodedStart;
    public boolean explodedEnd;


    public int blockX;
    public int blockY;
    public Blocktypes type;

    public BlockGeneral(int blockX, int blockY, Blocktypes type) {
        explosionAnimationX=0;
        explosionAnimationY=0;
        explodedStart=false;
        explodedEnd=false;
        this.blockX = blockX;
        this.blockY = blockY;
        this.type = type;
        Gdx.app.log("block created ","x:"+blockX+" y:"+blockY+" type:"+type+" exp animation x:"+explosionAnimationX+" exp animation y:"+explosionAnimationY);
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
        Gdx.app.log("showing explosion:",explosionAnimationX+" "+explosionAnimationY+" "+explosion.toString()+
                " at "+whereToExplodeX+"/"+whereToExplodeY+" width:"+tool.universalWidthFactor);
        if(explosionAnimationY==6){
            explodedEnd=true;
            Gdx.app.log("explosion ended: ",explodedEnd+"");
        }
    }

    public enum Blocktypes{
        AIR(airT,true),
        WOOD(woodT,false),
        STONE(stoneT,false),
        ROCK(rockT,false),
        WATER(waterT,false),
        FOOD(foodT,false);

        private Texture tile;
        private boolean passThrough;


        Blocktypes(Texture t,boolean pTh){
            this.tile=t;
            this.passThrough=pTh;
        }

        public Texture getTile() {
            return tile;
        }

        public boolean isPassThrough() {
            return passThrough;
        }
    }
}
