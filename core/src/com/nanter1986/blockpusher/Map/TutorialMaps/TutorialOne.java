package com.nanter1986.blockpusher.Map.TutorialMaps;

import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GameplayTypes;
import com.nanter1986.blockpusher.Map.GeneralMap;

/**
 * Created by user on 25/10/2017.
 */

public class TutorialOne extends GeneralMap {
    public final int MAP_WIDTH_IN_BLOCKS = 5;
    public final int MAP_HEIGHT_IN_BLOCKS = 5;


    public TutorialOne(DisplayToolkit tool) {
        this.blocksize = tool.universalWidthFactor;
        type = GameplayTypes.TUTORIAL1;
        this.width = MAP_WIDTH_IN_BLOCKS;
        this.height = MAP_HEIGHT_IN_BLOCKS;
        mapArray = new BlockGeneral[width][height];
        mapArray[0][0] = new BlockGeneral(tool, 0, 0, BlockGeneral.Blocktypes.WATER);
        mapArray[1][0] = new BlockGeneral(tool, 1, 0, BlockGeneral.Blocktypes.WATER);
        mapArray[2][0] = new BlockGeneral(tool, 2, 0, BlockGeneral.Blocktypes.WATER);
        mapArray[3][0] = new BlockGeneral(tool, 3, 0, BlockGeneral.Blocktypes.WATER);
        mapArray[4][0] = new BlockGeneral(tool, 4, 0, BlockGeneral.Blocktypes.WATER);
        mapArray[0][1] = new BlockGeneral(tool, 0, 1, BlockGeneral.Blocktypes.WATER);
        mapArray[1][1] = new BlockGeneral(tool, 1, 1, BlockGeneral.Blocktypes.WATER);
        mapArray[2][1] = new BlockGeneral(tool, 2, 1, BlockGeneral.Blocktypes.WATER);
        mapArray[3][1] = new BlockGeneral(tool, 3, 1, BlockGeneral.Blocktypes.WATER);
        mapArray[4][1] = new BlockGeneral(tool, 4, 1, BlockGeneral.Blocktypes.WATER);

        mapArray[0][2] = new BlockGeneral(tool, 0, 2, BlockGeneral.Blocktypes.AIR);
        mapArray[1][2] = new BlockGeneral(tool, 1, 2, BlockGeneral.Blocktypes.STONE);
        mapArray[2][2] = new BlockGeneral(tool, 2, 2, BlockGeneral.Blocktypes.AIR);
        mapArray[3][2] = new BlockGeneral(tool, 3, 2, BlockGeneral.Blocktypes.AIR);
        mapArray[4][2] = new BlockGeneral(tool, 4, 2, BlockGeneral.Blocktypes.AIR);

        mapArray[0][3] = new BlockGeneral(tool, 0, 3, BlockGeneral.Blocktypes.WATER);
        mapArray[1][3] = new BlockGeneral(tool, 1, 3, BlockGeneral.Blocktypes.WATER);
        mapArray[2][3] = new BlockGeneral(tool, 2, 3, BlockGeneral.Blocktypes.WATER);
        mapArray[3][3] = new BlockGeneral(tool, 3, 3, BlockGeneral.Blocktypes.WATER);
        mapArray[4][3] = new BlockGeneral(tool, 4, 3, BlockGeneral.Blocktypes.WATER);
        mapArray[0][4] = new BlockGeneral(tool, 0, 4, BlockGeneral.Blocktypes.WATER);
        mapArray[1][4] = new BlockGeneral(tool, 1, 4, BlockGeneral.Blocktypes.WATER);
        mapArray[2][4] = new BlockGeneral(tool, 2, 4, BlockGeneral.Blocktypes.WATER);
        mapArray[3][4] = new BlockGeneral(tool, 3, 4, BlockGeneral.Blocktypes.WATER);
        mapArray[4][4] = new BlockGeneral(tool, 4, 4, BlockGeneral.Blocktypes.WATER);
        setAllTiles(tool);
    }
}
