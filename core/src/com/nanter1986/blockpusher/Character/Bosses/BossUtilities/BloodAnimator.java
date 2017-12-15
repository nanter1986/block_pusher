package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 2/10/2017.
 */

public class BloodAnimator {

    private MovableCharacter character;

    public BloodAnimator(MovableCharacter character) {
        this.character = character;
    }

    public void bloodAnimation(DisplayToolkit tool) {
        int widthOfBlood = 6 * tool.universalWidthFactor;
        int whereToExplodeX = character.coord.getFixatedX() * tool.universalWidthFactor - widthOfBlood / 2;
        int whereToExplodeY = character.coord.getFixatedY() * tool.universalWidthFactor - widthOfBlood / 2;

        if(character.bloodDelayNumber>0){
            character.bloodDelayNumber--;
        }else{
            character.bloodAnimationX++;
            if(character.bloodAnimationX==2){
                character.bloodAnimationX=0;
                character.bloodAnimationY++;
            }

            if(character.bloodAnimationY==3){
                character.explodedEnd=true;

            }
            character.bloodDelayNumber=64;
        }
        int sourceX=character.bloodAnimationX*500;
        int sourceY=character.bloodAnimationY*500;
        tool.batch.draw(tool.manager.get("blood.png", Texture.class), whereToExplodeX, whereToExplodeY, widthOfBlood, widthOfBlood, sourceX, sourceY, 500, 500, false, false);



    }
}
