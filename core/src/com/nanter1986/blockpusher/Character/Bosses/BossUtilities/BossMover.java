package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Character.EnemyOne;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public class BossMover {

    private MovableCharacter character;

    public BossMover(MovableCharacter character) {
        this.character = character;
    }

    public void moveBoss(MapOne map, ArrayList<EnemyOne>enemies){
        if(character.moveReducer>0){
            character.moveReducer--;
            Gdx.app.log("reduce enemy moveReducer",character.moveReducer+"");
        }else{
            if(new BossFrontBlockChecker(character).checkIfBlockAtTheFront(map,enemies)){
                switch (character.dir){
                    case UP:
                        if(character.characterY<map.MAP_HEIGHT_IN_BLOCKS-1){
                            character.characterY+=1;
                        }else{
                            new RandomBossDirectioner(character);
                        }
                        break;
                    case DOWN:
                        if(character.characterY>1){
                            character.characterY-=1;
                        }else{
                            new RandomBossDirectioner(character);
                        }
                        break;
                    case LEFT:
                        if(character.characterX>1){
                            character.characterX-=1;
                        }else{
                            new RandomBossDirectioner(character);
                        }
                        break;
                    case RIGHT:
                        if(character.characterX<map.MAP_WIDTH_IN_BLOCKS-1){
                            character.characterX+=1;
                        }else{
                            new RandomBossDirectioner(character);
                        }
                        break;

                }
                Gdx.app.log("enemy walked to:",+character.characterX+"/"+character.characterY);
            }else{
                new RandomBossDirectioner(character);
            }
            character.moveReducer=64;
        }
    }
}
