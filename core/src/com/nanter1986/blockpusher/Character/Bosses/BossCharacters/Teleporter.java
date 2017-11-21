package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.Teleport;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;

/**
 * Created by user on 21/11/2017.
 */

public class Teleporter extends Nitar {
    public Teleporter(DisplayToolkit tool, GeneralMap map, MovableCharacter targetPlayer) {
        super(tool, map, targetPlayer);
        this.texture = tool.manager.get("villain.png", Texture.class);
        this.level = 4;
        moveReducerLimit = 26;
    }

    @Override
    public ArrayList<BossSkill> skillSet(DisplayToolkit tool, MovableCharacter targetPlayer) {
        ArrayList<BossSkill> skills = new ArrayList<BossSkill>();
        skills.add(new Teleport(targetPlayer));
        return skills;
    }
}
