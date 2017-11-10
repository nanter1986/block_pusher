package com.nanter1986.blockpusher.Character.Bosses.BossSkills;

import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public interface BossSkill {
    void executeSkill(int level, MovableCharacter character, GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<MovableCharacter> pr);
}
