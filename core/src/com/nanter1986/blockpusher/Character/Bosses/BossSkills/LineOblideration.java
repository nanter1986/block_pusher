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

public class LineOblideration implements BossSkill {
    public static final Random CHANCE = new Random();
    private final int CHANCE_OF_NUKE = 50;

    @Override
    public void executeSkill(DisplayToolkit tool, int level, MovableCharacter character, GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<MovableCharacter> pr) {
        if (CHANCE.nextInt(CHANCE_OF_NUKE) == 0) {
            switch (character.dir) {
                case UP:
                    for (int i = character.coord.fixatedY; i < map.height; i++) {
                        map.mapArray[character.coord.fixatedX][i].type = BlockGeneral.Blocktypes.AIR;
                        map.mapArray[character.coord.fixatedX][i].setTile(tool);
                        map.mapArray[character.coord.fixatedX][i].explodedStart = true;
                    }
                    break;
                case DOWN:
                    for (int i = 0; i < character.coord.fixatedY; i++) {
                        map.mapArray[character.coord.fixatedX][i].type = BlockGeneral.Blocktypes.AIR;
                        map.mapArray[character.coord.fixatedX][i].setTile(tool);
                        map.mapArray[character.coord.fixatedX][i].explodedStart = true;
                    }
                    break;
                case LEFT:
                    for (int i = 0; i < character.coord.fixatedX; i++) {
                        map.mapArray[i][character.coord.fixatedY].type = BlockGeneral.Blocktypes.AIR;
                        map.mapArray[i][character.coord.fixatedY].setTile(tool);
                        map.mapArray[i][character.coord.fixatedY].explodedStart = true;
                    }
                    break;
                case RIGHT:
                    for (int i = character.coord.fixatedX; i < map.width; i++) {
                        map.mapArray[i][character.coord.fixatedY].type = BlockGeneral.Blocktypes.AIR;
                        map.mapArray[i][character.coord.fixatedY].setTile(tool);
                        map.mapArray[i][character.coord.fixatedY].explodedStart = true;
                    }
                    break;
            }
        }
    }
}
