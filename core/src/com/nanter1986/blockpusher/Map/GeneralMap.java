package com.nanter1986.blockpusher.Map;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.InScreenDrawer.CheckIfInScreenToDraw;

import java.util.Random;

/**
 * Created by user on 25/10/2017.
 */

public abstract class GeneralMap {
    public static final Random RANDOM = new Random();
    public GameplayTypes type;
    public int width;
    public int height;
    public int blocksize;
    public BlockGeneral[][] mapArray;

    public GeneralMap(DisplayToolkit tool) {
        this.blocksize = tool.universalWidthFactor;
    }

    public void updatePosition(DisplayToolkit tool) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
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

    public void logSelf() {
        //String toLog="";
        Gdx.app.log("map one was created. ", "---MAP_WIDTH_IN_BLOCKS:" + width +
                "---MAP_HEIGHT_IN_BLOCKS:" + height +
                "---blocksize:" + blocksize +
                "---RANDOM" + RANDOM.toString() +
                "---mapArray:" + mapArray.toString());
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
