package com.nanter1986.blockpusher.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 19/9/2017.
 */

public class EnemyOne extends MovableCharacter {

    public final Texture playerOne = new Texture(Gdx.files.internal("villain.png"));

    public int moveReducer;


    public EnemyOne(DisplayToolkit tool, MapOne map) {
        this.texture=playerOne;
        boolean freeBlockFound=false;
        while (freeBlockFound==false){
            int theX=new Random().nextInt(50);
            int theY=new Random().nextInt(50);
            if(map.mapArray[theX][theY].type==BlockGeneral.Blocktypes.AIR){
                freeBlockFound=true;
                this.realX = theX;
                this.realY = theY;
            }

        }
        this.characterW=tool.universalWidthFactor;
        this.characterH=tool.universalWidthFactor;
        Gdx.app.log("enemy creation\n", "Enemy created at x:" + this.getFixatedX() +
                "\nat y:" + this.getFixatedY());
    }

    public void moveCharacter(MapOne map, ArrayList<MovableCharacter> enemies) {
        if(moveReducer>0){
            moveReducer--;
            Gdx.app.log("reduce enemy moveReducer",moveReducer+"");
        }else{
            if(checkIfBlockAtTheFront(map,enemies)){
                switch (dir){
                    case UP:
                        if (getFixatedY() < map.MAP_HEIGHT_IN_BLOCKS - 1) {
                            realY += 1;
                        }else{
                            getRandomDirection();
                        }
                        break;
                    case DOWN:
                        if (getFixatedY() > 1) {
                            realY -= 1;
                        }else{
                            getRandomDirection();
                        }
                        break;
                    case LEFT:
                        if (getFixatedX() > 1) {
                            realX -= 1;
                        }else{
                            getRandomDirection();
                        }
                        break;
                    case RIGHT:
                        if (getFixatedX() < map.MAP_WIDTH_IN_BLOCKS - 1) {
                            realX += 1;
                        }else{
                            getRandomDirection();
                        }
                        break;

                }
                Gdx.app.log("enemy walked to:", +getFixatedX() + "/" + getFixatedY());
            }else{
                getRandomDirection();
            }
            moveReducer=64;
        }

    }

    public void checkIfcrushed(MapOne map) {
        if (map.mapArray[getFixatedX()][getFixatedY()].type != BlockGeneral.Blocktypes.AIR) {
            crushed=true;
            explodedStarted=true;
            Gdx.app.log("enemy crushed:",crushed+" is dead");
        }else{
            Gdx.app.log("enemy crushed:",crushed+" still alive");
        }


    }

    private void getRandomDirection() {
        switch (new Random().nextInt(4)){
            case 0:
                dir=Direction.LEFT;
                break;
            case 1:
                dir=Direction.UP;
                break;
            case 2:
                dir=Direction.RIGHT;
                break;
            case 3:
                dir=Direction.DOWN;
                break;
        }
        Gdx.app.log("enemy switched direction to:",dir.toString());
    }

    @Override
    public void updatePosition(SpriteBatch b, MapOne map, ArrayList<MovableCharacter> characters) {
        switch (dir){
            case UP:
                b.draw(playerOne, getFixatedX() * characterW, getFixatedY() * characterW, characterW, characterH, 0, 0, 500, 500, false, false);
                break;
            case DOWN:
                b.draw(playerOne, getFixatedX() * characterW, getFixatedY() * characterW, characterW, characterH, 0, 1500, 500, 500, false, false);
                break;
            case LEFT:
                b.draw(playerOne, getFixatedX() * characterW, getFixatedY() * characterW, characterW, characterH, 0, 1000, 500, 500, false, false);
                break;
            case RIGHT:
                b.draw(playerOne, getFixatedX() * characterW, getFixatedY() * characterW, characterW, characterH, 0, 500, 500, 500, false, false);

                break;

        }
    }

    @Override
    public void bloodAnimation(DisplayToolkit tool) {
        int widthOfBlood=6*tool.universalWidthFactor;
        int whereToExplodeX = getFixatedX() * tool.universalWidthFactor - widthOfBlood / 2;
        int whereToExplodeY = getFixatedY() * tool.universalWidthFactor - widthOfBlood / 2;

        if(bloodDelayNumber>0){
            bloodDelayNumber--;
        }else{


            Gdx.app.log("blood animation:",whereToExplodeX+"/"+whereToExplodeY+"/"+widthOfBlood);
            bloodAnimationX++;
            if(bloodAnimationX==2){
                bloodAnimationX=0;
                bloodAnimationY++;
            }
            Gdx.app.log("showing explosion:",bloodAnimationX+" "+bloodAnimationY+" "+blood.toString()+
                    " at "+whereToExplodeX+"/"+whereToExplodeY+" width:"+widthOfBlood);
            if(bloodAnimationY==3){
                explodedEnd=true;
                Gdx.app.log("explosion ended: ",explodedEnd+"");
            }
            bloodDelayNumber=64;
        }
        int sourceX=bloodAnimationX*500;
        int sourceY=bloodAnimationY*500;
        Gdx.app.log("blood source at:",sourceX+"/"+sourceY);
        tool.batch.draw(blood,whereToExplodeX,whereToExplodeY,widthOfBlood,widthOfBlood,sourceX,sourceY,500,500,false,false);



    }

    public boolean checkIfBlockAtTheFront(MapOne map, ArrayList<MovableCharacter> enemies) {
        boolean isFreeToPass=true;
        switch (dir){
            case UP:
                int xToCheckUp = (getFixatedX());
                int yToCheckUp = getFixatedY() + 1;
                if(xToCheckUp<map.MAP_WIDTH_IN_BLOCKS && xToCheckUp>=0 && yToCheckUp<(map.MAP_HEIGHT_IN_BLOCKS-2) && yToCheckUp>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckUp][yToCheckUp].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if (e.getFixatedX() == this.getFixatedX() && e.getFixatedY() == this.getFixatedY() + 1) {
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction UP");
                            }
                        }
                    }

                }

                break;
            case DOWN:
                int xToCheckDown = getFixatedX();
                int yToCheckDown = getFixatedY() - 1;
                if(xToCheckDown<map.MAP_WIDTH_IN_BLOCKS && xToCheckDown>=0 && yToCheckDown<map.MAP_HEIGHT_IN_BLOCKS && yToCheckDown>0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckDown][yToCheckDown].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if (e.getFixatedX() == this.getFixatedX() && e.getFixatedY() == this.getFixatedY() - 1) {
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction DOWN");
                            }
                        }
                    }
                }

                break;
            case LEFT:
                int xToCheckLeft = getFixatedX() - 1;
                int yToCheckLeft = getFixatedY();
                if(xToCheckLeft<map.MAP_WIDTH_IN_BLOCKS && xToCheckLeft>0 && yToCheckLeft<map.MAP_HEIGHT_IN_BLOCKS && yToCheckLeft>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckLeft][yToCheckLeft].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if (e.getFixatedX() == this.getFixatedX() - 1 && e.getFixatedY() == this.getFixatedY()) {
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction LEFT");
                            }
                        }
                    }
                }


                break;

            case RIGHT:
                int xToCheckRight = getFixatedX() + 1;
                int yToCheckRight = getFixatedY();

                if(xToCheckRight<(map.MAP_WIDTH_IN_BLOCKS-2) && xToCheckRight>=0 && yToCheckRight<map.MAP_HEIGHT_IN_BLOCKS && yToCheckRight>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckRight][yToCheckRight].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for (MovableCharacter e : enemies) {
                            if (e.getFixatedX() == this.getFixatedX() + 1 && e.getFixatedY() == this.getFixatedY()) {
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction RIGHT");
                            }
                        }
                    }
                }

                break;

        }
        return isFreeToPass;
    }
}
