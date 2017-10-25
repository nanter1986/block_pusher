package com.nanter1986.blockpusher.Map;

import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 29/8/2017.
 */

public class MapOne extends GeneralMap {

    public final int MAP_WIDTH_IN_BLOCKS=50;
    public final int MAP_HEIGHT_IN_BLOCKS=50;


    public MapOne(DisplayToolkit tool) {
        super(tool);
        width = MAP_WIDTH_IN_BLOCKS;
        height = MAP_HEIGHT_IN_BLOCKS;
        mapArray = new BlockGeneral[width][width];
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
        //logSelf();
    }






}
