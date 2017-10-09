package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.ArrayList;

/**
 * Created by user on 3/10/2017.
 */

public class BossIsMovableCharacterInFront {
    MovableCharacter boss;

    public BossIsMovableCharacterInFront(MovableCharacter boss) {
        this.boss = boss;
    }

    public MovableCharacter isMovableCharacterInFront(int howFar, ArrayList<MovableCharacter> enemies, MapOne map) {
        MovableCharacter foundCharacter = null;
        for (MovableCharacter c : enemies) {
            switch (boss.dir) {
                case UP:
                    int xToCheckUp = (boss.getFixatedX());
                    int yToCheckUp = boss.getFixatedY() + 1 + howFar;
                    if (xToCheckUp < map.MAP_WIDTH_IN_BLOCKS && xToCheckUp >= 0 && yToCheckUp < (map.MAP_HEIGHT_IN_BLOCKS - 2) && yToCheckUp >= 0) {

                        if (c.getFixatedX() == xToCheckUp && c.getFixatedY() == yToCheckUp) {
                            foundCharacter = c;
                        }


                    }

                    break;
                case DOWN:
                    int xToCheckDown = boss.getFixatedX();
                    int yToCheckDown = boss.getFixatedY() - 1 - howFar;
                    if (xToCheckDown < map.MAP_WIDTH_IN_BLOCKS && xToCheckDown >= 0 && yToCheckDown < map.MAP_HEIGHT_IN_BLOCKS && yToCheckDown > 0) {

                        if (c.getFixatedX() == xToCheckDown && c.getFixatedY() == yToCheckDown) {
                            foundCharacter = c;
                        }


                    }

                    break;
                case LEFT:
                    int xToCheckLeft = boss.getFixatedX() - 1 - howFar;
                    int yToCheckLeft = boss.getFixatedY();
                    if (xToCheckLeft < map.MAP_WIDTH_IN_BLOCKS && xToCheckLeft > 0 && yToCheckLeft < map.MAP_HEIGHT_IN_BLOCKS && yToCheckLeft >= 0) {

                        if (c.getFixatedX() == xToCheckLeft && c.getFixatedY() == yToCheckLeft) {
                            foundCharacter = c;
                        }


                    }


                    break;

                case RIGHT:
                    int xToCheckRight = boss.getFixatedX() + 1 + howFar;
                    int yToCheckRight = boss.getFixatedY();

                    if (xToCheckRight < (map.MAP_WIDTH_IN_BLOCKS - 2) && xToCheckRight >= 0 && yToCheckRight < map.MAP_HEIGHT_IN_BLOCKS && yToCheckRight >= 0) {
                        BlockGeneral.Blocktypes bt = map.mapArray[xToCheckRight][yToCheckRight].type;
                        Gdx.app.log("type to check", bt.toString() + "");

                        if (c.getFixatedX() == xToCheckRight && c.getFixatedY() == yToCheckRight) {
                            foundCharacter = c;
                        }


                    }

                    break;

            }
        }
        return foundCharacter;
    }
}
