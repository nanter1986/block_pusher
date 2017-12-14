package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 11/11/2017.
 */

public class ProjectileDeathAnimator {
    private final int DIMENSION = 100;
    MovableCharacter character;

    public ProjectileDeathAnimator(MovableCharacter character) {
        this.character = character;
    }

    public void animate(DisplayToolkit tool) {
        int widthOfBlood = character.level * tool.universalWidthFactor;
        float whereToExplodeX = character.coord.realX;
        float whereToExplodeY = character.coord.realY;
        if (character.dir == MovableCharacter.Direction.UP) {
            whereToExplodeY = character.coord.realY - character.coord.characterW;
        } else if (character.dir == MovableCharacter.Direction.DOWN) {
            whereToExplodeY = character.coord.realY + character.coord.characterW;
        } else if (character.dir == MovableCharacter.Direction.LEFT) {
            whereToExplodeX = character.coord.realX + character.coord.characterW;
        } else if (character.dir == MovableCharacter.Direction.RIGHT) {
            whereToExplodeX = character.coord.realX - character.coord.characterW;
        }



        character.bloodAnimationX++;
        if (character.bloodAnimationX == 6) {
            character.bloodAnimationX = 0;
            character.bloodAnimationY++;
        }
        if (character.bloodAnimationY == 6) {
            character.explodedEnd = true;
        }
        character.bloodDelayNumber = 0;

        int sourceX = character.bloodAnimationX * DIMENSION;
        int sourceY = character.bloodAnimationY * DIMENSION;
        tool.batch.draw(tool.manager.get("explosion.png", Texture.class), whereToExplodeX, whereToExplodeY, widthOfBlood, widthOfBlood, sourceX, sourceY, DIMENSION, DIMENSION, false, false);
    }


}