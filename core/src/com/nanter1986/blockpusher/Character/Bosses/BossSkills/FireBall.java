package com.nanter1986.blockpusher.Character.Bosses.BossSkills;

import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.GeneralMap;
import com.nanter1986.blockpusher.projectiles.FirePr;
import com.nanter1986.blockpusher.projectiles.Projectile;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 2/10/2017.
 */

public class FireBall implements BossSkill {
    private final int MAX_LEVEL_THAT_AFFECTS = 50;

    @Override
    public void executeSkill(int level, MovableCharacter character, GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<Projectile> pr) {
        int chance = MAX_LEVEL_THAT_AFFECTS - level;
        if (chance < 2) {
            chance = 2;
        }
        int willSpawn = new Random().nextInt(chance);
        if (willSpawn == 0) {
            spawnFireball(pr);
        }

    }

    private void spawnFireball(ArrayList<Projectile> pr) {
        pr.add(new FirePr());
    }
}
