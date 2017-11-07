package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.GeneralMap;

/**
 * Created by user on 3/10/2017.
 */

public class BossWhatIsTheFrontBlock {

    public BlockGeneral whatIsTheFrontBlock(int howFar, MovableCharacter character, GeneralMap map) {
        BlockGeneral block = null;
        switch (character.dir) {
            case UP:
                int xToCheckUp = (character.getFixatedX());
                int yToCheckUp = character.getFixatedY() + howFar;
                if (xToCheckUp < map.width && xToCheckUp >= 0 && yToCheckUp < map.height && yToCheckUp >= 0) {
                    block = map.mapArray[xToCheckUp][yToCheckUp];


                }

                break;
            case DOWN:
                int xToCheckDown = character.getFixatedX();
                int yToCheckDown = character.getFixatedY() - howFar;
                if (xToCheckDown < map.width && xToCheckDown >= 0 && yToCheckDown < map.height && yToCheckDown >= 0) {
                    block = map.mapArray[xToCheckDown][yToCheckDown];

                }

                break;
            case LEFT:
                int xToCheckLeft = character.getFixatedX() - howFar;
                int yToCheckLeft = character.getFixatedY();
                if (xToCheckLeft < map.width && xToCheckLeft >= 0 && yToCheckLeft < map.height && yToCheckLeft >= 0) {
                    block = map.mapArray[xToCheckLeft][yToCheckLeft];


                }


                break;

            case RIGHT:
                int xToCheckRight = character.getFixatedX() + howFar;
                int yToCheckRight = character.getFixatedY();

                if (xToCheckRight < map.width && xToCheckRight >= 0 && yToCheckRight < map.height && yToCheckRight >= 0) {
                    block = map.mapArray[xToCheckRight][yToCheckRight];
                }

                break;

        }

        return block;
    }
}
