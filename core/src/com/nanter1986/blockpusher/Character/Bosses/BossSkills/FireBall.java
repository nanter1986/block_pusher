package com.nanter1986.blockpusher.Character.Bosses.BossSkills;

import com.nanter1986.blockpusher.Character.Bosses.BossCharacters.FirePr;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 2/10/2017.
 */

public class FireBall implements BossSkill {
    private final int MAX_LEVEL_THAT_AFFECTS = 50;
    DisplayToolkit tool;

    public FireBall(DisplayToolkit tool) {
        this.tool = tool;
    }

    @Override
    public void executeSkill(DisplayToolkit tool, int level, MovableCharacter character, GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<MovableCharacter> pr) {
        int chance = MAX_LEVEL_THAT_AFFECTS - level;
        if (chance < 2) {
            chance = 2;
        }
        chance = 2;
        int willSpawn = new Random().nextInt(chance);
        if (willSpawn == 0) {
            spawnFireball(pr, map, character);
        }

    }

    private void spawnFireball(ArrayList<MovableCharacter> pr, GeneralMap map, MovableCharacter character) {
        pr.add(new FirePr(tool, map, character));


    }
}
