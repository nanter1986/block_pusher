package com.nanter1986.blockpusher.Character.Bosses.BossSkills;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BossIsMovableCharacterInFront;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.BossWhatIsTheFrontBlock;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.ArrayList;

/**
 * Created by user on 2/10/2017.
 */

public class BlockSmash implements BossSkill{

    @Override
    public void executeSkill(int level, MovableCharacter character, MapOne map, ArrayList<MovableCharacter> enemies) {
        BlockGeneral block = new BossWhatIsTheFrontBlock().whatIsTheFrontBlock(level, character, map);
        if (block == null) {

        } else {
            for (int i = 0; i < level; i++) {
                if (block.type == BlockGeneral.Blocktypes.STONE) {
                    block.type = BlockGeneral.Blocktypes.AIR;
                    Gdx.app.log("skill execution: ", "stone to air");
                } else if (block.type == BlockGeneral.Blocktypes.WATER) {
                    block.type = BlockGeneral.Blocktypes.ICE;
                    Gdx.app.log("skill execution: ", "water to ice");
                } else if (block.type == BlockGeneral.Blocktypes.AIR) {
                    MovableCharacter c = new BossIsMovableCharacterInFront(character).isMovableCharacterInFront(i, enemies, map);
                    if (c == null) {

                    } else {
                        c.crushed = true;
                        c.explodedStarted = true;
                    }
                    Gdx.app.log("skill execution: ", "kill movable");
                }
            }
        }

    }
}
