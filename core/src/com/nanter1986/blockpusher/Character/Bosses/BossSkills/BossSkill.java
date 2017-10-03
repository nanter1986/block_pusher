package com.nanter1986.blockpusher.Character.Bosses.BossSkills;

import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public interface BossSkill {
    void executeSkill(int level, MovableCharacter character, MapOne map, ArrayList<MovableCharacter> enemies);
}
