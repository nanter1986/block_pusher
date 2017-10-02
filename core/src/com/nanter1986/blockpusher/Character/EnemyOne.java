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
                this.characterX=theX;
                this.characterY=theY;
                moveReducer=0;
                bloodAnimationX=0;
                bloodAnimationY=0;
                bloodDelayNumber=0;
                explodedEnd=false;
                explodedStarted=false;
            }

        }
        this.characterW=tool.universalWidthFactor;
        this.characterH=tool.universalWidthFactor;
        this.dir=Direction.UP;
        Gdx.app.log("enemy creation\n","Enemy created at x:"+this.characterX+
        "\nat y:"+this.characterY);
    }

    public void moveEnemy(MapOne map,ArrayList<EnemyOne>enemies){
        if(moveReducer>0){
            moveReducer--;
            Gdx.app.log("reduce enemy moveReducer",moveReducer+"");
        }else{
            if(checkIfBlockAtTheFront(map,enemies)){
                switch (dir){
                    case UP:
                        if(characterY<map.MAP_HEIGHT_IN_BLOCKS-1){
                            characterY+=1;
                        }else{
                            getRandomDirection();
                        }
                        break;
                    case DOWN:
                        if(characterY>1){
                            characterY-=1;
                        }else{
                            getRandomDirection();
                        }
                        break;
                    case LEFT:
                        if(characterX>1){
                            characterX-=1;
                        }else{
                            getRandomDirection();
                        }
                        break;
                    case RIGHT:
                        if(characterX<map.MAP_WIDTH_IN_BLOCKS-1){
                            characterX+=1;
                        }else{
                            getRandomDirection();
                        }
                        break;

                }
                Gdx.app.log("enemy walked to:",+characterX+"/"+characterY);
            }else{
                getRandomDirection();
            }
            moveReducer=64;
        }

    }

    public boolean checkIfcrushed(MapOne map){
        boolean crushed=false;
        if(map.mapArray[characterX][characterY].type!=BlockGeneral.Blocktypes.AIR){
            crushed=true;
            explodedStarted=true;
            Gdx.app.log("enemy crushed:",crushed+" is dead");
        }else{
            Gdx.app.log("enemy crushed:",crushed+" still alive");
        }

        return crushed;
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
    public void updatePosition(SpriteBatch b) {
        switch (dir){
            case UP:
                b.draw(playerOne,characterX*characterW,characterY*characterW,characterW,characterH,0,0,500,500,false,false);
                break;
            case DOWN:
                b.draw(playerOne,characterX*characterW,characterY*characterW,characterW,characterH,0,1500,500,500,false,false);
                break;
            case LEFT:
                b.draw(playerOne,characterX*characterW,characterY*characterW,characterW,characterH,0,1000,500,500,false,false);
                break;
            case RIGHT:
                b.draw(playerOne,characterX*characterW,characterY*characterW,characterW,characterH,0,500,500,500,false,false);

                break;

        }
    }

    @Override
    public void bloodAnimation(DisplayToolkit tool) {
        int widthOfBlood=6*tool.universalWidthFactor;
        int whereToExplodeX=characterX*tool.universalWidthFactor-widthOfBlood/2;
        int whereToExplodeY=characterY*tool.universalWidthFactor-widthOfBlood/2;

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

    public boolean checkIfBlockAtTheFront(MapOne map, ArrayList<EnemyOne>enemies){
        boolean isFreeToPass=true;
        switch (dir){
            case UP:
                int xToCheckUp=(characterX);
                int yToCheckUp=characterY+1;
                if(xToCheckUp<map.MAP_WIDTH_IN_BLOCKS && xToCheckUp>=0 && yToCheckUp<(map.MAP_HEIGHT_IN_BLOCKS-2) && yToCheckUp>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckUp][yToCheckUp].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for(EnemyOne e:enemies){
                            if(e.characterX==this.characterX && e.characterY==this.characterY+1){
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction UP");
                            }
                        }
                    }

                }

                break;
            case DOWN:
                int xToCheckDown=characterX;
                int yToCheckDown=characterY-1;
                if(xToCheckDown<map.MAP_WIDTH_IN_BLOCKS && xToCheckDown>=0 && yToCheckDown<map.MAP_HEIGHT_IN_BLOCKS && yToCheckDown>0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckDown][yToCheckDown].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for(EnemyOne e:enemies){
                            if(e.characterX==this.characterX && e.characterY==this.characterY-1){
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction DOWN");
                            }
                        }
                    }
                }

                break;
            case LEFT:
                int xToCheckLeft=characterX-1;
                int yToCheckLeft=characterY;
                if(xToCheckLeft<map.MAP_WIDTH_IN_BLOCKS && xToCheckLeft>0 && yToCheckLeft<map.MAP_HEIGHT_IN_BLOCKS && yToCheckLeft>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckLeft][yToCheckLeft].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for(EnemyOne e:enemies){
                            if(e.characterX==this.characterX-1 && e.characterY==this.characterY){
                                isFreeToPass=false;
                                Gdx.app.log("enemy in front,","direction LEFT");
                            }
                        }
                    }
                }


                break;

            case RIGHT:
                int xToCheckRight=characterX+1;
                int yToCheckRight=characterY;

                if(xToCheckRight<(map.MAP_WIDTH_IN_BLOCKS-2) && xToCheckRight>=0 && yToCheckRight<map.MAP_HEIGHT_IN_BLOCKS && yToCheckRight>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckRight][yToCheckRight].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }else{
                        for(EnemyOne e:enemies){
                            if(e.characterX==this.characterX+1 && e.characterY==this.characterY){
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
