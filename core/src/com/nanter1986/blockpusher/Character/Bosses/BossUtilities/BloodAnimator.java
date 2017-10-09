package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.Gdx;
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
        int widthOfBlood = character.level * 6 * tool.universalWidthFactor;
        int whereToExplodeX = character.getFixatedX() * tool.universalWidthFactor - widthOfBlood / 2;
        int whereToExplodeY = character.getFixatedY() * tool.universalWidthFactor - widthOfBlood / 2;

        if(character.bloodDelayNumber>0){
            character.bloodDelayNumber--;
        }else{


            Gdx.app.log("blood animation:",whereToExplodeX+"/"+whereToExplodeY+"/"+widthOfBlood);
            character.bloodAnimationX++;
            if(character.bloodAnimationX==2){
                character.bloodAnimationX=0;
                character.bloodAnimationY++;
            }
            Gdx.app.log("showing explosion:",character.bloodAnimationX+" "+character.bloodAnimationY+" "+character.blood.toString()+
                    " at "+whereToExplodeX+"/"+whereToExplodeY+" width:"+widthOfBlood);
            if(character.bloodAnimationY==3){
                character.explodedEnd=true;
                Gdx.app.log("explosion ended: ",character.explodedEnd+"");
            }
            character.bloodDelayNumber=64;
        }
        int sourceX=character.bloodAnimationX*500;
        int sourceY=character.bloodAnimationY*500;
        Gdx.app.log("blood source at:",sourceX+"/"+sourceY);
        tool.batch.draw(character.blood,whereToExplodeX,whereToExplodeY,widthOfBlood,widthOfBlood,sourceX,sourceY,500,500,false,false);



    }
}
