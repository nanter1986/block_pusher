package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.LineOblideration;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;

/**
 * Created by user on 22/11/2017.
 */

public class Nuker extends Nitar {
    public Nuker(DisplayToolkit tool, GeneralMap map, MovableCharacter targetPlayer) {
        super(tool, map, targetPlayer);
        this.texture = tool.manager.get("nukerSheet.png", Texture.class);
        this.level = 6;
        moveReducerLimit = 22;
    }

    @Override
    public ArrayList<BossSkill> skillSet(DisplayToolkit tool, MovableCharacter targetPlayer) {
        ArrayList<BossSkill> skills = new ArrayList<BossSkill>();
        skills.add(new LineOblideration());
        return skills;
    }
}
