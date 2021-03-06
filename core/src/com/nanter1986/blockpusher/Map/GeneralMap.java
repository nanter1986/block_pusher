package com.nanter1986.blockpusher.Map;

import com.badlogic.gdx.utils.Disposable;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.InScreenDrawer.CheckIfInScreenToDraw;

import java.util.Random;

/**
 * Created by user on 25/10/2017.
 */

public abstract class GeneralMap implements Disposable {
    public static final Random RANDOM = new Random();
    public GameplayTypes type;
    public int width;
    public int height;
    public int blocksize;
    public BlockGeneral[][] mapArray;


    public void updatePositionAbove(DisplayToolkit tool) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                float blockX = mapArray[i][j].blockX * blocksize;
                float blockY = mapArray[i][j].blockY * blocksize;
                if (CheckIfInScreenToDraw.checkIfInScreen(tool, blockX, blockY)) {
                    boolean showExplosion = mapArray[i][j].explodedStart && mapArray[i][j].explodedEnd == false;
                    if (showExplosion) {
                        mapArray[i][j].explode(tool);
                    } else if (mapArray[i][j].type == BlockGeneral.Blocktypes.STONE) {
                        tool.batch.draw(mapArray[i][j].tile, blockX, blockY, blocksize, blocksize);
                    }
                }

            }
        }
    }

    @Override
    public void dispose() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mapArray[i][j].dispose();
                mapArray[i][j] = null;
            }
        }
        mapArray = null;
    }

    public void updatePositionBelow(DisplayToolkit tool) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                float blockX = mapArray[i][j].blockX * blocksize;
                float blockY = mapArray[i][j].blockY * blocksize;
                if (CheckIfInScreenToDraw.checkIfInScreen(tool, blockX, blockY)) {
                    boolean showExplosion = mapArray[i][j].explodedStart && mapArray[i][j].explodedEnd == false;
                    if (showExplosion) {
                        mapArray[i][j].explode(tool);
                    } else if (mapArray[i][j].type == BlockGeneral.Blocktypes.AIR) {
                        tool.batch.draw(mapArray[i][j].tile, blockX, blockY, blocksize, blocksize);
                    } else if (mapArray[i][j].type == BlockGeneral.Blocktypes.ICE) {
                        tool.batch.draw(mapArray[i][j].tile, blockX, blockY, blocksize, blocksize);
                    } else if (mapArray[i][j].type == BlockGeneral.Blocktypes.WATER) {
                        tool.batch.draw(mapArray[i][j].tile, blockX, blockY, blocksize, blocksize);
                    }
                }

            }
        }
    }

    public void setAllTiles(DisplayToolkit tool) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mapArray[i][j].setTile(tool);
            }
        }

    }


}
