package com.nanter1986.blockpusher.Character.Bosses.BossSkills;

import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 2/10/2017.
 */

public class Teleport implements BossSkill {
    public static final Random RANDOM = new Random();
    public static final Random CHANCE = new Random();
    private final int CHANCE_OF_TELEPORTING = 50;

    MovableCharacter targetPlayer;

    public Teleport(MovableCharacter targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    @Override
    public void executeSkill(DisplayToolkit tool, int level, MovableCharacter character, GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<MovableCharacter> pr) {
        if (CHANCE.nextInt(CHANCE_OF_TELEPORTING) == 0) {
            boolean freeBlockFound = false;
            while (freeBlockFound == false) {
                int theX = RANDOM.nextInt(map.width);
                int theY = RANDOM.nextInt(map.height);
                boolean fallsOnTargetPlayer = theX == targetPlayer.coord.fixatedX && theY == targetPlayer.coord.fixatedY;
                boolean targetAreaIsAir = map.mapArray[theX][theY].type == BlockGeneral.Blocktypes.AIR;
                if (targetAreaIsAir && !fallsOnTargetPlayer) {
                    freeBlockFound = true;
                    character.coord.realX = theX * character.characterW;
                    character.coord.realY = theY * character.characterW;
                    character.coord.fixatedX = theX;
                    character.coord.fixatedY = theY;
                    character.moveReducer = character.moveReducerLimit;
                    character.stepSequenceRunning = false;


                }

            }
        }

    }
}
