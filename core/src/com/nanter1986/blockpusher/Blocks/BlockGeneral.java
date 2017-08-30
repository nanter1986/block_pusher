package com.nanter1986.blockpusher.Blocks;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by user on 29/8/2017.
 */

public class BlockGeneral {
    public int blockX;
    public int blockY;
    public Blocktypes type;

    public BlockGeneral(int blockX, int blockY, Blocktypes type) {
        this.blockX = blockX;
        this.blockY = blockY;
        this.type = type;
    }

    public enum Blocktypes{
        AIR(new Texture("air.png"),true),
        WOOD(new Texture("wood.png"),false),
        STONE(new Texture("stone.png"),false),
        ROCK(new Texture("rock.png"),false),
        WATER(new Texture("water.png"),false),
        FOOD(new Texture("food.png"),false);

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
