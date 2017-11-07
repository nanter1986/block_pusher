package com.nanter1986.blockpusher.Character.Bosses.BossSkills;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BossIsMovableCharacterInFront;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BossWhatIsTheFrontBlock;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public class BlockSmash implements BossSkill {

    @Override
    public void executeSkill(int level, MovableCharacter character, GeneralMap map, ArrayList<MovableCharacter> enemies) {

        for (int i = 0; i < level; i++) {
            BlockGeneral block = new BossWhatIsTheFrontBlock().whatIsTheFrontBlock(i + 1, character, map);

            if (block == null) {

            } else if (block.type == BlockGeneral.Blocktypes.STONE) {
                map.mapArray[block.blockX][block.blockY].type = BlockGeneral.Blocktypes.AIR;
                map.mapArray[block.blockX][block.blockY].setTile();
                Gdx.app.log("skill execution: ", "stone to air");
            } else if (block.type == BlockGeneral.Blocktypes.WATER) {
                map.mapArray[block.blockX][block.blockY].type = BlockGeneral.Blocktypes.ICE;
                map.mapArray[block.blockX][block.blockY].setTile();
                Gdx.app.log("skill execution: ", "water to ice");
            } else if (block.type == BlockGeneral.Blocktypes.AIR) {
                MovableCharacter c = new BossIsMovableCharacterInFront(character).isMovableCharacterInFront(i, enemies, map);
                if (c == null) {

                } else {
                    c.crushed = true;
                    c.explodedStarted = true;
                    Gdx.app.log("skill execution: ", "kill movable");
                }

            }

        }


    }
}
