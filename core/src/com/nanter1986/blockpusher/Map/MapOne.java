package com.nanter1986.blockpusher.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;

import java.util.Random;

/**
 * Created by user on 29/8/2017.
 */

public class MapOne {
    public final int MAP_WIDTH_IN_BLOCKS=50;
    public final int MAP_HEIGHT_IN_BLOCKS=50;
    public int blocksize;
    public int mapX;
    public int mapY;
    private static final Random RANDOM = new Random();
    BlockGeneral[][] mapArray=new BlockGeneral[50][50];

    public MapOne(DisplayToolkit tool) {
        blocksize=tool.universalWidthFactor;
        mapX=MAP_WIDTH_IN_BLOCKS/2*blocksize;
        mapY=MAP_HEIGHT_IN_BLOCKS/2*blocksize;
        for(int i=0;i<MAP_WIDTH_IN_BLOCKS;i++){
            for(int j=0;j<MAP_HEIGHT_IN_BLOCKS;j++){
                int anInteger=RANDOM.nextInt(100);
                if(anInteger<50){
                    mapArray[i][j]=new BlockGeneral(i*blocksize,j*blocksize, BlockGeneral.Blocktypes.AIR);
                }else if(anInteger<60){
                    mapArray[i][j]=new BlockGeneral(i*blocksize,j*blocksize, BlockGeneral.Blocktypes.WATER);
                }else if(anInteger<70){
                    mapArray[i][j]=new BlockGeneral(i*blocksize,j*blocksize, BlockGeneral.Blocktypes.STONE);
                }else if(anInteger<80){
                    mapArray[i][j]=new BlockGeneral(i*blocksize,j*blocksize, BlockGeneral.Blocktypes.WOOD);
                }else if(anInteger<90){
                    mapArray[i][j]=new BlockGeneral(i*blocksize,j*blocksize, BlockGeneral.Blocktypes.ROCK);
                }else if(anInteger<100){
                    mapArray[i][j]=new BlockGeneral(i*blocksize,j*blocksize, BlockGeneral.Blocktypes.FOOD);
                }
            }
        }
    }

    public void updatePosition(SpriteBatch b){
        for(int i=0;i<50;i++) {
            for (int j = 0; j < 50; j++) {
                b.draw(mapArray[i][j].type.getTile(),mapArray[i][j].blockX,mapArray[i][j].blockY,blocksize,blocksize);
            }
        }

    }


}
