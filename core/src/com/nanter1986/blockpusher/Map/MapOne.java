package com.nanter1986.blockpusher.Map;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.InScreenDrawer.CheckIfInScreenToDraw;

import java.util.Random;

/**
 * Created by user on 29/8/2017.
 */

public class MapOne {
    private static final Random RANDOM = new Random();
    public final int MAP_WIDTH_IN_BLOCKS=50;
    public final int MAP_HEIGHT_IN_BLOCKS=50;
    public int blocksize;
    public BlockGeneral[][] mapArray = new BlockGeneral[MAP_WIDTH_IN_BLOCKS][MAP_HEIGHT_IN_BLOCKS];

    public MapOne(DisplayToolkit tool) {
        blocksize=tool.universalWidthFactor;

        for(int i=0;i<MAP_WIDTH_IN_BLOCKS;i++){
            for(int j=0;j<MAP_HEIGHT_IN_BLOCKS;j++){
                int anInteger=RANDOM.nextInt(100);
                if(anInteger<50){
                    mapArray[i][j]=new BlockGeneral(i,j, BlockGeneral.Blocktypes.AIR);
                }else if(anInteger<60){
                    mapArray[i][j]=new BlockGeneral(i,j, BlockGeneral.Blocktypes.WATER);
                }else if(anInteger<100){
                    mapArray[i][j]=new BlockGeneral(i,j, BlockGeneral.Blocktypes.STONE);
                }
            }
        }
        logSelf();
    }

    public void updatePosition(DisplayToolkit tool){

        for(int i=0;i<50;i++) {
            for (int j = 0; j < 50; j++) {
                float blockX = mapArray[i][j].blockX * blocksize;
                float blockY = mapArray[i][j].blockY * blocksize;
                if (CheckIfInScreenToDraw.checkIfInScreen(tool, blockX, blockY)) {
                    boolean showExplosion = mapArray[i][j].explodedStart && mapArray[i][j].explodedEnd == false;
                    if (showExplosion) {

                        Gdx.app.log("show explosion: ", showExplosion + "( ' )");
                        mapArray[i][j].explode(tool);
                    } else if (mapArray[i][j].type == BlockGeneral.Blocktypes.AIR) {

                    } else {
                        tool.batch.draw(mapArray[i][j].tile, blockX, blockY, blocksize, blocksize);
                    }
                }

            }
        }
        //logSelf();

    }

    public void logSelf(){
        //String toLog="";
        Gdx.app.log("map one was created. ", "---MAP_WIDTH_IN_BLOCKS:"+MAP_WIDTH_IN_BLOCKS+
                "---MAP_HEIGHT_IN_BLOCKS:"+MAP_HEIGHT_IN_BLOCKS+
                "---blocksize:"+blocksize+
                "---RANDOM"+RANDOM.toString()+
                "---mapArray:"+mapArray.toString());
        /*for(int k=0;k<MAP_WIDTH_IN_BLOCKS;k++){
            for( int l=0;l<MAP_HEIGHT_IN_BLOCKS;l++){
                toLog+="---block x:"+mapArray[k][l].blockX+
                        "---block y:"+mapArray[k][l].blockY+
                        "---block type:"+mapArray[k][l].type+"\n";
            }
        }*/
        /*Gdx.app.log("mapArray:",toLog);*/
    }


}
