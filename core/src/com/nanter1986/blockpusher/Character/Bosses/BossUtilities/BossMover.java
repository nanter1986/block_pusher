package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
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

    public void moveBoss(MapOne map, ArrayList<MovableCharacter> enemies) {
        if (character.moveReducer > 1) {
            character.moveReducer--;
            character.increaseByStep();
            Gdx.app.log("reduce enemy moveReducer,step", character.moveReducer + "," + character.getStep());
        } else if (character.moveReducer == 1) {
            //character.fixatePosition();
            character.moveReducer -= 1;
            Gdx.app.log("fixate enemy position", character.moveReducer + "");
        }else{
            character.stepSequenceRunning = false;
            character.fixatePosition();
            if(new BossFrontBlockChecker(character).checkIfBlockAtTheFront(map,enemies)){
                switch (character.dir){
                    case UP:
                        if (character.getFixatedY() < map.MAP_HEIGHT_IN_BLOCKS - 1) {
                            character.stepSequenceRunning = true;
                        }else{
                            new RandomBossDirectioner(character).getRandomDirection();
                        }
                        break;
                    case DOWN:
                        if (character.getFixatedY() > 1) {
                            character.stepSequenceRunning = true;
                        }else{
                            new RandomBossDirectioner(character).getRandomDirection();
                        }
                        break;
                    case LEFT:
                        if (character.getFixatedX() > 1) {
                            character.stepSequenceRunning = true;
                        }else{
                            new RandomBossDirectioner(character).getRandomDirection();
                        }
                        break;
                    case RIGHT:
                        if (character.getFixatedX() < map.MAP_WIDTH_IN_BLOCKS - 1) {
                            character.stepSequenceRunning = true;
                        }else{
                            new RandomBossDirectioner(character).getRandomDirection();
                        }
                        break;

                }
                Gdx.app.log("enemy walked to:", +character.getFixatedX() + "/" + character.getFixatedY());
            }else{
                new RandomBossDirectioner(character).getRandomDirection();
            }
            ArrayList<BossSkill> skills = character.skills;
            for (BossSkill bs : skills) {
                //bs.executeSkill(character.level, character, map, enemies);
            }
            character.moveReducer = character.moveReducerLimit;
        }
    }
}
