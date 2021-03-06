package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.Flooding;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;

/**
 * Created by user on 20/11/2017.
 */

public class WaterKing extends Nitar {
    public WaterKing(DisplayToolkit tool, GeneralMap map, MovableCharacter targetPlayer) {
        super(tool, map, targetPlayer);
        this.texture = tool.manager.get("waterkingSheet.png", Texture.class);
        this.level = 3;
        moveReducerLimit = 28;
    }

    @Override
    public ArrayList<BossSkill> skillSet(DisplayToolkit tool, MovableCharacter targetPlayer) {
        ArrayList<BossSkill> skills = new ArrayList<BossSkill>();
        skills.add(new Flooding(targetPlayer));
        return skills;
    }
}
