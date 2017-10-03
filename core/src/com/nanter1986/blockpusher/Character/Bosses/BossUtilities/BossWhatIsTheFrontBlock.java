package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.MapOne;

/**
 * Created by user on 3/10/2017.
 */

public class BossWhatIsTheFrontBlock {

    public BlockGeneral whatIsTheFrontBlock(int howFar, MovableCharacter character, MapOne map) {
        BlockGeneral block = null;

        switch (character.dir) {
            case UP:
                int xToCheckUp = (character.characterX);
                int yToCheckUp = character.characterY + 1 + howFar;
                if (xToCheckUp < map.MAP_WIDTH_IN_BLOCKS && xToCheckUp >= 0 && yToCheckUp < (map.MAP_HEIGHT_IN_BLOCKS - 2) && yToCheckUp >= 0) {
                    block = map.mapArray[xToCheckUp][yToCheckUp];


                }

                break;
            case DOWN:
                int xToCheckDown = character.characterX;
                int yToCheckDown = character.characterY - 1 - howFar;
                if (xToCheckDown < map.MAP_WIDTH_IN_BLOCKS && xToCheckDown >= 0 && yToCheckDown < map.MAP_HEIGHT_IN_BLOCKS && yToCheckDown > 0) {
                    block = map.mapArray[xToCheckDown][yToCheckDown];

                }

                break;
            case LEFT:
                int xToCheckLeft = character.characterX - 1 - howFar;
                int yToCheckLeft = character.characterY;
                if (xToCheckLeft < map.MAP_WIDTH_IN_BLOCKS && xToCheckLeft > 0 && yToCheckLeft < map.MAP_HEIGHT_IN_BLOCKS && yToCheckLeft >= 0) {
                    block = map.mapArray[xToCheckLeft][yToCheckLeft];

                }


                break;

            case RIGHT:
                int xToCheckRight = character.characterX + 1 + howFar;
                int yToCheckRight = character.characterY;

                if (xToCheckRight < (map.MAP_WIDTH_IN_BLOCKS - 2) && xToCheckRight >= 0 && yToCheckRight < map.MAP_HEIGHT_IN_BLOCKS && yToCheckRight >= 0) {
                    block = map.mapArray[xToCheckRight][yToCheckRight];
                }

                break;

        }

        return block;
    }
}
