package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public class Nitar extends MovableCharacter implements GeneralBoss {
    @Override
    public int levelOfBoss() {
        return 1;
    }

    @Override
    public String nameOfBoss() {
        return "Nitar";
    }

    @Override
    public ArrayList<BossSkill> skillSet() {
        return null;
    }

    @Override
    public String firstDialog() {
        return null;
    }

    @Override
    public String PainDialog() {
        return null;
    }

    @Override
    public String BragDialog() {
        return null;
    }

    @Override
    public String WhenBossWinsDialog() {
        return null;
    }

    @Override
    public String WhenPlayerWinsDialog() {
        return null;
    }

    @Override
    public void updatePosition(SpriteBatch b) {

    }

    @Override
    public void bloodAnimation(DisplayToolkit tool) {

    }
}
