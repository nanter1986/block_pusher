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
        if (map.mapArray[character.getFixatedX()][character.getFixatedY()].type != BlockGeneral.Blocktypes.AIR) {
            character.crushed = true;
            character.explodedStarted=true;
            Gdx.app.log("enemy crushed:", character.crushed + " is dead");
        }else{
            Gdx.app.log("enemy crushed:", character.crushed + " still alive");
        }


    }
}
