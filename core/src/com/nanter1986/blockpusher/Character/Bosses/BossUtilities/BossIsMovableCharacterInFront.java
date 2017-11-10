package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;

/**
 * Created by user on 3/10/2017.
 */

public class BossIsMovableCharacterInFront {
    MovableCharacter boss;

    public BossIsMovableCharacterInFront(MovableCharacter boss) {
        this.boss = boss;
    }

    public MovableCharacter isMovableCharacterInFront(int howFar, ArrayList<MovableCharacter> enemies, GeneralMap map) {
        MovableCharacter foundCharacter = null;
        for (MovableCharacter c : enemies) {
            switch (boss.dir) {
                case UP:
                    int xToCheckUp = (boss.coord.getFixatedX());
                    int yToCheckUp = boss.coord.getFixatedY() + 1 + howFar;
                    if (xToCheckUp < map.width && xToCheckUp >= 0 && yToCheckUp < (map.height - 2) && yToCheckUp >= 0) {

                        if (c.coord.getFixatedX() == xToCheckUp && c.coord.getFixatedY() == yToCheckUp) {
                            foundCharacter = c;
                        }


                    }

                    break;
                case DOWN:
                    int xToCheckDown = boss.coord.getFixatedX();
                    int yToCheckDown = boss.coord.getFixatedY() - 1 - howFar;
                    if (xToCheckDown < map.width && xToCheckDown >= 0 && yToCheckDown < map.height && yToCheckDown > 0) {

                        if (c.coord.getFixatedX() == xToCheckDown && c.coord.getFixatedY() == yToCheckDown) {
                            foundCharacter = c;
                        }


                    }

                    break;
                case LEFT:
                    int xToCheckLeft = boss.coord.getFixatedX() - 1 - howFar;
                    int yToCheckLeft = boss.coord.getFixatedY();
                    if (xToCheckLeft < map.width && xToCheckLeft > 0 && yToCheckLeft < map.height && yToCheckLeft >= 0) {

                        if (c.coord.getFixatedX() == xToCheckLeft && c.coord.getFixatedY() == yToCheckLeft) {
                            foundCharacter = c;
                        }


                    }


                    break;

                case RIGHT:
                    int xToCheckRight = boss.coord.getFixatedX() + 1 + howFar;
                    int yToCheckRight = boss.coord.getFixatedY();

                    if (xToCheckRight < (map.width - 2) && xToCheckRight >= 0 && yToCheckRight < map.height && yToCheckRight >= 0) {
                        BlockGeneral.Blocktypes bt = map.mapArray[xToCheckRight][yToCheckRight].type;
                        Gdx.app.log("type to check", bt.toString() + "");

                        if (c.coord.getFixatedX() == xToCheckRight && c.coord.getFixatedY() == yToCheckRight) {
                            foundCharacter = c;
                        }


                    }

                    break;

            }
        }
        return foundCharacter;
    }
}
