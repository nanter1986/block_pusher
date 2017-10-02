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

    public boolean checkIfcrushed(MapOne map){
        boolean crushed=false;
        if(map.mapArray[character.characterX][character.characterY].type!= BlockGeneral.Blocktypes.AIR){
            crushed=true;
            character.explodedStarted=true;
            Gdx.app.log("enemy crushed:",crushed+" is dead");
        }else{
            Gdx.app.log("enemy crushed:",crushed+" still alive");
        }

        return crushed;
    }
}
