package com.nanter1986.blockpusher.Map.debug;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GameplayTypes;
import com.nanter1986.blockpusher.Map.GeneralMap;

/**
 * Created by user on 6/11/2017.
 */

public class NitarTestMap extends GeneralMap {
    public final int MAP_WIDTH_IN_BLOCKS = 5;
    public final int MAP_HEIGHT_IN_BLOCKS = 5;

    public NitarTestMap(DisplayToolkit tool) {
        super(tool);
        type = GameplayTypes.DEBUG_NITAR;
        this.width = MAP_WIDTH_IN_BLOCKS;
        this.height = MAP_HEIGHT_IN_BLOCKS;
        mapArray = new BlockGeneral[width][height];
        mapArray[0][0] = new BlockGeneral(0, 0, BlockGeneral.Blocktypes.STONE);
        mapArray[1][0] = new BlockGeneral(1, 0, BlockGeneral.Blocktypes.STONE);
        mapArray[2][0] = new BlockGeneral(2, 0, BlockGeneral.Blocktypes.STONE);
        mapArray[3][0] = new BlockGeneral(3, 0, BlockGeneral.Blocktypes.STONE);
        mapArray[4][0] = new BlockGeneral(4, 0, BlockGeneral.Blocktypes.STONE);
        mapArray[0][1] = new BlockGeneral(0, 1, BlockGeneral.Blocktypes.STONE);
        mapArray[1][1] = new BlockGeneral(1, 1, BlockGeneral.Blocktypes.STONE);
        mapArray[2][1] = new BlockGeneral(2, 1, BlockGeneral.Blocktypes.STONE);
        mapArray[3][1] = new BlockGeneral(3, 1, BlockGeneral.Blocktypes.STONE);
        mapArray[4][1] = new BlockGeneral(4, 1, BlockGeneral.Blocktypes.STONE);

        mapArray[0][2] = new BlockGeneral(0, 2, BlockGeneral.Blocktypes.STONE);
        mapArray[1][2] = new BlockGeneral(1, 2, BlockGeneral.Blocktypes.STONE);
        mapArray[2][2] = new BlockGeneral(2, 2, BlockGeneral.Blocktypes.STONE);
        mapArray[3][2] = new BlockGeneral(3, 2, BlockGeneral.Blocktypes.STONE);
        mapArray[4][2] = new BlockGeneral(4, 2, BlockGeneral.Blocktypes.AIR);

        mapArray[0][3] = new BlockGeneral(0, 3, BlockGeneral.Blocktypes.STONE);
        mapArray[1][3] = new BlockGeneral(1, 3, BlockGeneral.Blocktypes.STONE);
        mapArray[2][3] = new BlockGeneral(2, 3, BlockGeneral.Blocktypes.STONE);
        mapArray[3][3] = new BlockGeneral(3, 3, BlockGeneral.Blocktypes.STONE);
        mapArray[4][3] = new BlockGeneral(4, 3, BlockGeneral.Blocktypes.STONE);
        mapArray[0][4] = new BlockGeneral(0, 4, BlockGeneral.Blocktypes.STONE);
        mapArray[1][4] = new BlockGeneral(1, 4, BlockGeneral.Blocktypes.STONE);
        mapArray[2][4] = new BlockGeneral(2, 4, BlockGeneral.Blocktypes.STONE);
        mapArray[3][4] = new BlockGeneral(3, 4, BlockGeneral.Blocktypes.STONE);
        mapArray[4][4] = new BlockGeneral(4, 4, BlockGeneral.Blocktypes.STONE);
        Gdx.app.log("array", mapArray[0][2].type.toString());
    }
}
