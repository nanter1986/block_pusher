package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.GeneralMap;

/**
 * Created by user on 2/10/2017.
 */

public class BossCrushChecker {

    private MovableCharacter character;

    public BossCrushChecker(MovableCharacter character) {
        this.character = character;
    }

    public void checkIfcrushed(GeneralMap map) {
        int xForArray = 0;
        int yForArray = 0;
        final float LIMIT = 0.5f;
        float xTocheck = (character.coord.realX % character.characterW) / character.characterW;
        float yTocheck = (character.coord.realY % character.characterW) / character.characterW;
        Gdx.app.log("crush check", xTocheck + "/" + yTocheck);
        switch (character.dir) {
            case UP:
                if (yTocheck < LIMIT) {
                    yForArray = character.coord.fixatedY;
                    xForArray = character.coord.fixatedX;
                } else {
                    yForArray = character.coord.fixatedY + 1;
                    xForArray = character.coord.fixatedX;
                }
                break;
            case DOWN:
                if (yTocheck > LIMIT) {
                    yForArray = character.coord.fixatedY - 1;
                    xForArray = character.coord.fixatedX;
                } else {
                    yForArray = character.coord.fixatedY;
                    xForArray = character.coord.fixatedX;
                }
                break;
            case LEFT:
                if (xTocheck > LIMIT) {
                    yForArray = character.coord.fixatedY;
                    xForArray = character.coord.fixatedX - 1;
                } else {
                    yForArray = character.coord.fixatedY;
                    xForArray = character.coord.fixatedX;
                }
                break;
            case RIGHT:
                if (xTocheck < LIMIT) {
                    yForArray = character.coord.fixatedY;
                    xForArray = character.coord.fixatedX;
                } else {
                    yForArray = character.coord.fixatedY;
                    xForArray = character.coord.fixatedX + 1;
                }

                break;

        }
        BlockGeneral.Blocktypes type = map.mapArray[xForArray][yForArray].type;
        whatToDoBasedOnBlockType(type);



    }

    private void whatToDoBasedOnBlockType(BlockGeneral.Blocktypes bt) {
        if (bt == BlockGeneral.Blocktypes.AIR) {

        } else if (bt == BlockGeneral.Blocktypes.STONE) {
            character.crushed = true;
            character.explodedStarted = true;
        } else if (bt == BlockGeneral.Blocktypes.ICE) {

        } else if (bt == BlockGeneral.Blocktypes.WATER) {
            character.crushed = true;
            character.explodedStarted = true;
        }
    }
}
