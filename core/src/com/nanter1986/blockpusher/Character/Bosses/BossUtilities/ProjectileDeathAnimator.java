package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 11/11/2017.
 */

public class ProjectileDeathAnimator {
    public final Texture blood = new Texture(Gdx.files.internal("blood.png"));
    private final int DIMENSION = 100;
    MovableCharacter character;

    public ProjectileDeathAnimator(MovableCharacter character) {
        this.character = character;
    }

    public void animate(DisplayToolkit tool) {
        int widthOfBlood = character.level * tool.universalWidthFactor;
        int whereToExplodeX = character.coord.getFixatedX() * tool.universalWidthFactor - widthOfBlood / 2;
        int whereToExplodeY = character.coord.getFixatedY() * tool.universalWidthFactor - widthOfBlood / 2;

        if (character.bloodDelayNumber > 0) {
            character.bloodDelayNumber--;
        } else {
            Gdx.app.log("blood animation:", whereToExplodeX + "/" + whereToExplodeY + "/" + widthOfBlood);
            character.bloodAnimationX++;
            if (character.bloodAnimationX == 6) {
                character.bloodAnimationX = 0;
                character.bloodAnimationY++;
            }
            if (character.bloodAnimationY == 6) {
                character.explodedEnd = true;
            }
            character.bloodDelayNumber = 8;
        }
        int sourceX = character.bloodAnimationX * DIMENSION;
        int sourceY = character.bloodAnimationY * DIMENSION;
        tool.batch.draw(tool.manager.get("explosion.png", Texture.class), whereToExplodeX, whereToExplodeY, widthOfBlood, widthOfBlood, sourceX, sourceY, DIMENSION, DIMENSION, false, false);
    }


}