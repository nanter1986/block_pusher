package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.FireBall;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;

/**
 * Created by user on 22/11/2017.
 */

public class ShooterOne extends Nitar {
    public ShooterOne(DisplayToolkit tool, GeneralMap map, MovableCharacter targetPlayer) {
        super(tool, map, targetPlayer);
        this.texture = tool.manager.get("shooterSheet.png", Texture.class);
        this.level = 5;
        moveReducerLimit = 24;
    }

    @Override
    public ArrayList<BossSkill> skillSet(DisplayToolkit tool, MovableCharacter targetPlayer) {
        ArrayList<BossSkill> skills = new ArrayList<BossSkill>();
        skills.add(new FireBall(tool));
        return skills;
    }
}
