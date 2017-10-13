package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.MapOne;

/**
 * Created by user on 2/10/2017.
 */

public class BossCrushChecker {

    private MovableCharacter character;

    public BossCrushChecker(MovableCharacter character) {
        this.character = character;
    }

    public void checkIfcrushed(MapOne map) {
        int xForArray = 0;
        int yForArray = 0;
        final float LIMIT = 0.2f;
        float xTocheck = (character.coord.realX % character.characterW) / character.characterW;
        float yTocheck = (character.coord.realY % character.characterW) / character.characterW;
        Gdx.app.log("crush check", xTocheck + "/" + yTocheck);
        switch (character.dir) {
            case UP:
                if (yTocheck + character.characterW / 2 < LIMIT) {
                    yForArray = character.coord.fixatedY;
                    xForArray = character.coord.fixatedX;
                } else {
                    yForArray = character.coord.fixatedY + 1;
                    xForArray = character.coord.fixatedX;
                }
                break;
            case DOWN:
                if (yTocheck - character.characterW < LIMIT) {
                    yForArray = character.coord.fixatedY - 1;
                    xForArray = character.coord.fixatedX;
                } else {
                    yForArray = character.coord.fixatedY;
                    xForArray = character.coord.fixatedX;
                }
                break;
            case LEFT:
                if (xTocheck - character.characterW < LIMIT) {
                    yForArray = character.coord.fixatedY;
                    xForArray = character.coord.fixatedX - 1;
                } else {
                    yForArray = character.coord.fixatedY;
                    xForArray = character.coord.fixatedX;
                }
                break;
            case RIGHT:
                if (xTocheck + character.characterW < LIMIT) {
                    yForArray = character.coord.fixatedY;
                    xForArray = character.coord.fixatedX;
                } else {
                    yForArray = character.coord.fixatedY;
                    xForArray = character.coord.fixatedX + 1;
                }

                break;

        }
        Gdx.app.log("crush check int", xForArray + "/" + yForArray);
        if (map.mapArray[xForArray][yForArray].type != BlockGeneral.Blocktypes.AIR) {
            character.crushed = true;
            character.explodedStarted=true;
            Gdx.app.log("enemy crushed:", character.crushed + " is dead");
        }else{
            Gdx.app.log("enemy crushed:", character.crushed + " still alive");
        }


    }
}
