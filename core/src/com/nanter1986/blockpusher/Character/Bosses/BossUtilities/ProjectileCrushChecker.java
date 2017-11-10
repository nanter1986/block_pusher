package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
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
        int xForArray = 0;
        int yForArray = 0;
        final float LIMIT = 0.5f;
        float xTocheck = (character.coord.realX % character.characterW) / character.characterW;
        float yTocheck = (character.coord.realY % character.characterW) / character.characterW;
        Gdx.app.log("crush check", xTocheck + "/" + yTocheck);
        if (character.coord.realX < 0 || character.coord.realX >= map.width || character.coord.realY < 0 || character.coord.realY >= map.height) {
            character.crushed = true;
            character.explodedStarted = true;
        } else {
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


    }

    private void whatToDoBasedOnBlockType(BlockGeneral.Blocktypes type) {
        if (type == BlockGeneral.Blocktypes.AIR) {

        } else if (type == BlockGeneral.Blocktypes.STONE) {
            character.crushed = true;
            character.explodedStarted = true;
        } else if (type == BlockGeneral.Blocktypes.ICE) {

        } else if (type == BlockGeneral.Blocktypes.WATER) {
            character.crushed = true;
            character.explodedStarted = true;
        }
    }
}
