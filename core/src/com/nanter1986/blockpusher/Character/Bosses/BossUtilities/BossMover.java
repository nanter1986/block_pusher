package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.GeneralMap;
import com.nanter1986.blockpusher.projectiles.Projectile;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public class BossMover {

    private MovableCharacter character;


    public BossMover(MovableCharacter character) {
        this.character = character;
    }

    public void moveBoss(GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<Projectile> projectiles) {
        if (character.moveReducer > 0) {
            character.moveReducer--;
            character.increaseByStep(map);
        } else {
            character.fixatePosition();
            character.stepSequenceRunning = false;
            if(new BossFrontBlockChecker(character).checkIfBlockAtTheFront(map,enemies)){
                switch (character.dir){
                    case UP:
                        if (character.getFixatedY() < map.height - 1) {
                            character.stepSequenceRunning = true;
                        }else{
                            new RandomBossDirectioner(character).getRandomDirection();
                        }
                        break;
                    case DOWN:
                        if (character.getFixatedY() > 0) {
                            character.stepSequenceRunning = true;
                        }else{
                            new RandomBossDirectioner(character).getRandomDirection();
                        }
                        break;
                    case LEFT:
                        if (character.getFixatedX() > 0) {
                            character.stepSequenceRunning = true;
                        }else{
                            new RandomBossDirectioner(character).getRandomDirection();
                        }
                        break;
                    case RIGHT:
                        if (character.getFixatedX() < map.width - 1) {
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
                bs.executeSkill(character.level, character, map, enemies, projectiles);
            }

        }
    }
}
