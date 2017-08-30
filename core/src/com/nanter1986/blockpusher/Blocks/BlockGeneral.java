package com.nanter1986.blockpusher.Blocks;

import com.badlogic.gdx.graphics.Texture;

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
    public int blockX;
    public int blockY;
    public Blocktypes type;

    public BlockGeneral(int blockX, int blockY, Blocktypes type) {
        this.blockX = blockX;
        this.blockY = blockY;
        this.type = type;
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
