package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.DisplayToolkit;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public interface GeneralBoss {
    String nameOfBoss();

    ArrayList<BossSkill> skillSet(DisplayToolkit tool);

    String firstDialog();

    String PainDialog();

    String BragDialog();

    String WhenBossWinsDialog();

    String WhenPlayerWinsDialog();

}
