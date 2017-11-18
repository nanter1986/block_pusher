package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;

/**
 * Created by user on 10/11/2017.
 */

public class ProjectileMover {
    private MovableCharacter character;

    public ProjectileMover(MovableCharacter character) {
        this.character = character;
    }

    public void moveProjectile(GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<MovableCharacter> projectiles) {

        if (character.moveReducer > 0) {
            Gdx.app.log("projectile", "in/" + character.moveReducer);
            character.moveReducer--;
            character.increaseByStep(map);
        } else {
            character.coord.fixatePosition();
            character.stepSequenceRunning = false;
            switch (character.dir) {
                case UP:
                    if (character.coord.getFixatedY() < map.height) {
                        character.stepSequenceRunning = true;
                    }
                    break;
                case DOWN:
                    if (character.coord.getFixatedY() >= 0) {
                        character.stepSequenceRunning = true;
                    }
                    break;
                case LEFT:
                    if (character.coord.getFixatedX() >= 0) {
                        character.stepSequenceRunning = true;
                    }
                    break;
                case RIGHT:
                    if (character.coord.getFixatedX() < map.width) {
                        character.stepSequenceRunning = true;
                    }
                    break;

            }
            character.moveReducer = character.moveReducerLimit;


        }
    }
}
