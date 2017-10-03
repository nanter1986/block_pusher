package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public class BossFrontBlockChecker {

    private MovableCharacter character;

    public BossFrontBlockChecker(MovableCharacter character) {
        this.character = character;
    }

    public boolean checkIfBlockAtTheFront(MapOne map, ArrayList<MovableCharacter> enemies) {
        boolean isFreeToPass=true;
        switch (character.dir){
            case UP:
                int xToCheckUp=(character.characterX);
                int yToCheckUp=character.characterY+1;
                if(xToCheckUp<map.MAP_WIDTH_IN_BLOCKS && xToCheckUp>=0 && yToCheckUp<(map.MAP_HEIGHT_IN_BLOCKS-2) && yToCheckUp>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckUp][yToCheckUp].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if(e.characterX==character.characterX && e.characterY==character.characterY+1){
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction UP");
                            }
                        }
                    }

                }

                break;
            case DOWN:
                int xToCheckDown=character.characterX;
                int yToCheckDown=character.characterY-1;
                if(xToCheckDown<map.MAP_WIDTH_IN_BLOCKS && xToCheckDown>=0 && yToCheckDown<map.MAP_HEIGHT_IN_BLOCKS && yToCheckDown>0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckDown][yToCheckDown].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if(e.characterX==character.characterX && e.characterY==character.characterY-1){
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction DOWN");
                            }
                        }
                    }
                }

                break;
            case LEFT:
                int xToCheckLeft=character.characterX-1;
                int yToCheckLeft=character.characterY;
                if(xToCheckLeft<map.MAP_WIDTH_IN_BLOCKS && xToCheckLeft>0 && yToCheckLeft<map.MAP_HEIGHT_IN_BLOCKS && yToCheckLeft>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckLeft][yToCheckLeft].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if(e.characterX==character.characterX-1 && e.characterY==character.characterY){
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction LEFT");
                            }
                        }
                    }
                }


                break;

            case RIGHT:
                int xToCheckRight=character.characterX+1;
                int yToCheckRight=character.characterY;

                if(xToCheckRight<(map.MAP_WIDTH_IN_BLOCKS-2) && xToCheckRight>=0 && yToCheckRight<map.MAP_HEIGHT_IN_BLOCKS && yToCheckRight>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckRight][yToCheckRight].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if(e.characterX==character.characterX+1 && e.characterY==character.characterY){
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
