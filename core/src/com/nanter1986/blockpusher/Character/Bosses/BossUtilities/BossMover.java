package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public class BossMover {

    private MovableCharacter character;
    private DisplayToolkit tool;


    public BossMover(DisplayToolkit tool, MovableCharacter character) {
        this.tool = tool;
        this.character = character;
    }

    public void moveBoss(GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<MovableCharacter> projectiles) {
        if (character.moveReducer > 0) {
            character.moveReducer--;
            character.increaseByStep(map);
        } else {
            character.coord.fixatePosition();
            character.stepSequenceRunning = false;
            if(new BossFrontBlockChecker(character).checkIfBlockAtTheFront(map,enemies)){
                switch (character.dir){
                    case UP:
                        if (character.coord.getFixatedY() < map.height - 1) {
                            character.stepSequenceRunning = true;
                        }else{
                            new RandomBossDirectioner(character).getRandomDirection();
                        }
                        break;
                    case DOWN:
                        if (character.coord.getFixatedY() > 0) {
                            character.stepSequenceRunning = true;
                        }else{
                            new RandomBossDirectioner(character).getRandomDirection();
                        }
                        break;
                    case LEFT:
                        if (character.coord.getFixatedX() > 0) {
                            character.stepSequenceRunning = true;
                        }else{
                            new RandomBossDirectioner(character).getRandomDirection();
                        }
                        break;
                    case RIGHT:
                        if (character.coord.getFixatedX() < map.width - 1) {
                            character.stepSequenceRunning = true;
                        }else{
                            new RandomBossDirectioner(character).getRandomDirection();
                        }
                        break;

                }
                character.moveReducer = character.moveReducerLimit;
            }else{
                new RandomBossDirectioner(character).getRandomDirection();
                character.moveReducer = character.moveReducerLimit;
            }
            ArrayList<BossSkill> skills = character.skills;
            for (BossSkill bs : skills) {
                bs.executeSkill(tool, character.level, character, map, enemies, projectiles);
            }

        }
    }
}
