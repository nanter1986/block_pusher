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

public class Flooding implements BossSkill {
    public static final Random RANDOM = new Random();
    public static final Random CHANCE = new Random();
    private final int CHANCE_OF_FLOODING = 20;

    MovableCharacter targetPlayer;

    public Flooding(MovableCharacter targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    @Override
    public void executeSkill(DisplayToolkit tool, int level, MovableCharacter character, GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<MovableCharacter> pr) {
        if (CHANCE.nextInt(CHANCE_OF_FLOODING) == 0) {
            boolean freeBlockFound = false;
            while (freeBlockFound == false) {
                int theX = RANDOM.nextInt(map.width);
                int theY = RANDOM.nextInt(map.height);
                boolean fallsOnTargetPlayer = theX == targetPlayer.coord.fixatedX && theY == targetPlayer.coord.fixatedY;
                boolean fallsOnSelf = theX == character.coord.fixatedX && theY == character.coord.fixatedY;
                boolean fallsOnEnemy = checkIfItFallsOnEnemy(theX, theY, enemies);
                if (map.mapArray[theX][theY].type == BlockGeneral.Blocktypes.AIR && !fallsOnTargetPlayer && !fallsOnSelf && !fallsOnEnemy) {
                    freeBlockFound = true;
                    map.mapArray[theX][theY].type = BlockGeneral.Blocktypes.WATER;
                    map.mapArray[theX][theY].setTile(tool);

                }

            }
        }
    }

    private boolean checkIfItFallsOnEnemy(int theX, int theY, ArrayList<MovableCharacter> enemies) {
        boolean fallsOn = false;
        for (MovableCharacter m : enemies) {
            if (theX == m.coord.fixatedX && theY == m.coord.fixatedY) {
                fallsOn = true;
            }
        }
        return fallsOn;
    }
}
