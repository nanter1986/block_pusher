package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.MovableCharacter;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public interface GeneralBoss {
    public abstract int levelOfBoss();
    public abstract String nameOfBoss();
    public abstract ArrayList<BossSkill> skillSet();

    public abstract String firstDialog();
    public abstract String PainDialog();
    public abstract String BragDialog();
    public abstract String WhenBossWinsDialog();
    public abstract String WhenPlayerWinsDialog();

}
