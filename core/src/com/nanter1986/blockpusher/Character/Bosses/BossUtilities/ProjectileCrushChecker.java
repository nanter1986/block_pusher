package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.GeneralMap;

/**
 * Created by user on 10/11/2017.
 */

public class ProjectileCrushChecker {
    private MovableCharacter character;

    public ProjectileCrushChecker(MovableCharacter character) {
        this.character = character;
    }

    public void checkIfcrushed(GeneralMap map) {
        if (character.crushed == true) {

        } else if (character.coord.realX < 0 || character.coord.realX >= ((map.width) * character.coord.characterW) || character.coord.realY < 0 || character.coord.realY >= ((map.height) * character.coord.characterW)) {
            character.crushed = true;
            character.explodedStarted = true;
            character.coord.fixatePosition();
        } else {
            BlockGeneral.Blocktypes type = map.mapArray[character.coord.fixatedX][character.coord.fixatedY].type;
            whatToDoBasedOnBlockType(type);
        }


    }

    private void whatToDoBasedOnBlockType(BlockGeneral.Blocktypes type) {
        if (type == BlockGeneral.Blocktypes.AIR) {

        } else if (type == BlockGeneral.Blocktypes.STONE) {
            character.crushed = true;
            character.explodedStarted = true;
        } else if (type == BlockGeneral.Blocktypes.ICE) {

        } else if (type == BlockGeneral.Blocktypes.WATER) {

        }
    }
}
