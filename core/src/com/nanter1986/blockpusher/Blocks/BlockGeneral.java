package com.nanter1986.blockpusher.Blocks;

/**
 * Created by user on 29/8/2017.
 */

public class BlockGeneral {
    int blockX;
    int blockY;
    Blocktypes type;

    public BlockGeneral(int blockX, int blockY, Blocktypes type) {
        this.blockX = blockX;
        this.blockY = blockY;
        this.type = type;
    }

    public enum Blocktypes{
        AIR,
        WOOD,
        STONE,
        ROCK,
        WATER,
        FOOD
    }
}
