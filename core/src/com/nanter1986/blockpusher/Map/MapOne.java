package com.nanter1986.blockpusher.Map;

import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 29/8/2017.
 */

public class MapOne extends GeneralMap {

    public final int MAP_MIN_WIDTH_IN_BLOCKS = 10;
    public final int MAP_MIN_HEIGHT_IN_BLOCKS = 10;


    public MapOne(DisplayToolkit tool) {
        super(tool);
        type = GameplayTypes.REGULAR;
        width = calculateWidth(tool);
        height = calculateHeight(tool);
        mapArray = new BlockGeneral[width][height];
        blocksize=tool.universalWidthFactor;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int anInteger=RANDOM.nextInt(100);
                if(anInteger<50){
                    mapArray[i][j] = new BlockGeneral(tool, i, j, BlockGeneral.Blocktypes.AIR);
                }else if(anInteger<60){
                    mapArray[i][j] = new BlockGeneral(tool, i, j, BlockGeneral.Blocktypes.WATER);
                }else if(anInteger<100){
                    mapArray[i][j] = new BlockGeneral(tool, i, j, BlockGeneral.Blocktypes.STONE);
                }
            }
        }
        //logSelf();
    }

    private int calculateHeight(DisplayToolkit tool) {
        int stage = tool.data.readStage();
        int height = MAP_MIN_HEIGHT_IN_BLOCKS + stage / 10;
        return height;
    }

    private int calculateWidth(DisplayToolkit tool) {
        int stage = tool.data.readStage();
        int width = MAP_MIN_WIDTH_IN_BLOCKS + stage / 10;
        return width;
    }


}
