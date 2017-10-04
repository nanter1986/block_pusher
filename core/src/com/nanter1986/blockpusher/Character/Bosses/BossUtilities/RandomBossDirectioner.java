package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Character.MovableCharacter;

import java.util.Random;

/**
 * Created by user on 2/10/2017.
 */

public class RandomBossDirectioner {

    private MovableCharacter character;

    public RandomBossDirectioner(MovableCharacter character) {
        this.character = character;
    }

    public void getRandomDirection() {
        switch (new Random().nextInt(4)){
            case 0:
                character.dir= MovableCharacter.Direction.LEFT;
                break;
            case 1:
                character.dir= MovableCharacter.Direction.UP;
                break;
            case 2:
                character.dir= MovableCharacter.Direction.RIGHT;
                break;
            case 3:
                character.dir= MovableCharacter.Direction.DOWN;
                break;
        }
        Gdx.app.log("enemy switched direction to:",character.dir.toString());
    }
}
