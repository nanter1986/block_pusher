package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BlockSmash;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BloodAnimator;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BossMover;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public class Nitar extends MovableCharacter implements GeneralBoss {


    @Override
    public String nameOfBoss() {
        return "Nitar";
    }

    @Override
    public ArrayList<BossSkill> skillSet() {
        ArrayList<BossSkill> skills = new ArrayList<BossSkill>();
        skills.add(new BlockSmash());
        return skills;
    }

    @Override
    public String firstDialog() {
        return "Hello";
    }

    @Override
    public String PainDialog() {
        return "Ouch";
    }

    @Override
    public String BragDialog() {
        return "HAHAHA";
    }

    @Override
    public String WhenBossWinsDialog() {
        return "You are worthless";
    }

    @Override
    public String WhenPlayerWinsDialog() {
        return "It can't beeee...";
    }


    @Override
    public void updatePosition(SpriteBatch b, MapOne map, ArrayList<MovableCharacter> characters) {

    }

    @Override
    public void bloodAnimation(DisplayToolkit tool) {
        new BloodAnimator(this);
    }

    @Override
    public void moveCharacter(MapOne map, ArrayList<MovableCharacter> enemies) {
        new BossMover(this).moveBoss(map, enemies);
    }

    @Override
    public void checkIfcrushed(MapOne map) {

    }
}
