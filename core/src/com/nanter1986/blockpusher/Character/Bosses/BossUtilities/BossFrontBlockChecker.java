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

    public BossFrontBlockChecker(MovableCharacter character) {
        this.character = character;
    }

    public boolean checkIfBlockAtTheFront(GeneralMap map, ArrayList<MovableCharacter> enemies) {
        boolean isFreeToPass=true;
        switch (character.dir){
            case UP:
                int xToCheckUp = character.coord.fixatedX;
                int yToCheckUp = character.coord.fixatedY + 1;
                if (xToCheckUp < map.width && xToCheckUp >= 0 && yToCheckUp < map.height && yToCheckUp >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckUp][yToCheckUp].type;
                    Gdx.app.log("check enemies", character.coord.fixatedX + "/" +
                            character.coord.realX + "/" +
                            character.coord.fixatedY + "/" +
                            character.coord.realY + "/" +
                            xToCheckUp + "/" +
                            yToCheckUp + "/" +
                            bt.toString());
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if (e.getFixatedX() == character.getFixatedX() && e.getFixatedY() == character.getFixatedY() + 1) {
                                isFreeToPass=false;
                            }
                        }
                    }

                }

                break;
            case DOWN:
                int xToCheckDown = character.getFixatedX();
                int yToCheckDown = character.getFixatedY() - 1;
                if (xToCheckDown < map.width && xToCheckDown >= 0 && yToCheckDown < map.height && yToCheckDown >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckDown][yToCheckDown].type;
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if (e.getFixatedX() == character.getFixatedX() && e.getFixatedY() == character.getFixatedY() - 1) {
                                isFreeToPass=false;
                            }
                        }
                    }
                }

                break;
            case LEFT:
                int xToCheckLeft = character.getFixatedX() - 1;
                int yToCheckLeft = character.getFixatedY();
                if (xToCheckLeft < map.width && xToCheckLeft > 0 && yToCheckLeft < map.height && yToCheckLeft >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckLeft][yToCheckLeft].type;
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if (e.getFixatedX() == character.getFixatedX() - 1 && e.getFixatedY() == character.getFixatedY()) {
                                isFreeToPass=false;
                            }
                        }
                    }
                }


                break;

            case RIGHT:
                int xToCheckRight = character.getFixatedX() + 1;
                int yToCheckRight = character.getFixatedY();

                if (xToCheckRight < map.width && xToCheckRight >= 0 && yToCheckRight < map.height && yToCheckRight >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckRight][yToCheckRight].type;
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if (e.getFixatedX() == character.getFixatedX() + 1 && e.getFixatedY() == character.getFixatedY()) {
                                isFreeToPass=false;
                            }
                        }
                    }
                }

                break;

        }
        return isFreeToPass;
    }
}
