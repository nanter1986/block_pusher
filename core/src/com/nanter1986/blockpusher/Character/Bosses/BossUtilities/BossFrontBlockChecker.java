package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public class BossFrontBlockChecker {

    private MovableCharacter character;
    private boolean isFreeToPass;

    public BossFrontBlockChecker(MovableCharacter character) {
        this.character = character;
    }

    public boolean checkIfBlockAtTheFront(GeneralMap map, ArrayList<MovableCharacter> enemies) {
        isFreeToPass = true;
        switch (character.dir){
            case UP:
                int xToCheckUp = character.coord.fixatedX;
                int yToCheckUp = character.coord.fixatedY + 1;
                if (xToCheckUp < map.width && xToCheckUp >= 0 && yToCheckUp < map.height && yToCheckUp >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckUp][yToCheckUp].type;
                    whatToDoBasedOnBlockType(bt, enemies);

                }

                break;
            case DOWN:
                int xToCheckDown = character.coord.getFixatedX();
                int yToCheckDown = character.coord.getFixatedY() - 1;
                if (xToCheckDown < map.width && xToCheckDown >= 0 && yToCheckDown < map.height && yToCheckDown >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckDown][yToCheckDown].type;
                    whatToDoBasedOnBlockType(bt, enemies);
                }

                break;
            case LEFT:
                int xToCheckLeft = character.coord.getFixatedX() - 1;
                int yToCheckLeft = character.coord.getFixatedY();
                if (xToCheckLeft < map.width && xToCheckLeft >= 0 && yToCheckLeft < map.height && yToCheckLeft >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckLeft][yToCheckLeft].type;
                    whatToDoBasedOnBlockType(bt, enemies);
                }


                break;

            case RIGHT:
                int xToCheckRight = character.coord.getFixatedX() + 1;
                int yToCheckRight = character.coord.getFixatedY();

                if (xToCheckRight < map.width && xToCheckRight >= 0 && yToCheckRight < map.height && yToCheckRight >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckRight][yToCheckRight].type;
                    whatToDoBasedOnBlockType(bt, enemies);
                }

                break;

        }
        return isFreeToPass;
    }

    private void whatToDoBasedOnBlockType(BlockGeneral.Blocktypes bt, ArrayList<MovableCharacter> enemies) {
        if (bt == BlockGeneral.Blocktypes.AIR) {
            Gdx.app.log("currentTest", bt.toString());
            for (MovableCharacter e : enemies) {
                if (e.coord.getFixatedX() == character.coord.getFixatedX() && e.coord.getFixatedY() == character.coord.getFixatedY() + 1) {
                    isFreeToPass = false;
                }
            }
        } else if (bt == BlockGeneral.Blocktypes.ICE) {
            Gdx.app.log("currentTest", bt.toString());
            for (MovableCharacter e : enemies) {
                if (e.coord.getFixatedX() == character.coord.getFixatedX() && e.coord.getFixatedY() == character.coord.getFixatedY() + 1) {
                    isFreeToPass = false;
                }
            }
        } else if (bt == BlockGeneral.Blocktypes.STONE) {
            isFreeToPass = false;
        } else if (bt == BlockGeneral.Blocktypes.WATER) {
            isFreeToPass = false;
        }
    }
}
