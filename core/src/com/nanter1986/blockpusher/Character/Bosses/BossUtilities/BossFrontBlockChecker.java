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
                int xToCheckUp = (character.getFixatedX());
                int yToCheckUp = character.getFixatedY() + 1;
                if (xToCheckUp < map.width && xToCheckUp >= 0 && yToCheckUp < (map.height - 2) && yToCheckUp >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckUp][yToCheckUp].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if (e.getFixatedX() == character.getFixatedX() && e.getFixatedY() == character.getFixatedY() + 1) {
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction UP");
                            }
                        }
                    }

                }

                break;
            case DOWN:
                int xToCheckDown = character.getFixatedX();
                int yToCheckDown = character.getFixatedY() - 1;
                if (xToCheckDown < map.width && xToCheckDown >= 0 && yToCheckDown < map.height && yToCheckDown > 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckDown][yToCheckDown].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if (e.getFixatedX() == character.getFixatedX() && e.getFixatedY() == character.getFixatedY() - 1) {
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction DOWN");
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
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if (e.getFixatedX() == character.getFixatedX() - 1 && e.getFixatedY() == character.getFixatedY()) {
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction LEFT");
                            }
                        }
                    }
                }


                break;

            case RIGHT:
                int xToCheckRight = character.getFixatedX() + 1;
                int yToCheckRight = character.getFixatedY();

                if (xToCheckRight < (map.width - 2) && xToCheckRight >= 0 && yToCheckRight < map.height && yToCheckRight >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckRight][yToCheckRight].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if (e.getFixatedX() == character.getFixatedX() + 1 && e.getFixatedY() == character.getFixatedY()) {
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction RIGHT");
                            }
                        }
                    }
                }

                break;

        }
        return isFreeToPass;
    }
}
