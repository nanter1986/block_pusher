package com.nanter1986.blockpusher.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.MapOne;

import java.util.Random;

/**
 * Created by user on 19/9/2017.
 */

public class EnemyOne extends MovableCharacter {

    public Texture playerOne = new Texture(Gdx.files.internal("playerone.png"));
    public int moveReducer;

    public EnemyOne(DisplayToolkit tool, MapOne map) {
        boolean freeBlockFound=false;
        while (freeBlockFound==false){
            int theX=new Random().nextInt(50);
            int theY=new Random().nextInt(50);
            if(map.mapArray[theX][theY].type==BlockGeneral.Blocktypes.AIR){
                freeBlockFound=true;
                this.characterX=theX;
                this.characterY=theY;
                moveReducer=0;
            }

        }
        this.characterW=tool.universalWidthFactor;
        this.characterH=tool.universalWidthFactor;
        this.dir=Direction.UP;
        Gdx.app.log("enemy creation\n","Enemy created at x:"+this.characterX+
        "\nat y:"+this.characterY);
    }

    public void moveEnemy(MapOne map){
        if(moveReducer>0){
            moveReducer--;
        }else{
            if(checkIfBlockAtTheFront(map)){
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
            }else{
                getRandomDirection();
            }
            moveReducer=8;
            Gdx.app.log("new enemy direction:",dir.toString()+
                    "\nnew enemy x:"+characterX+
                    "\nnew enemy y:"+characterY);
        }

    }

    public boolean checkIfcrushed(MapOne map){
        boolean crushed=false;
        if(map.mapArray[characterX][characterY].type!=BlockGeneral.Blocktypes.AIR){
            crushed=true;
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
                b.draw(playerOne,characterX*characterW,characterY*characterW,characterW,characterH,0,0,64,64,false,false);
                break;
            case DOWN:
                b.draw(playerOne,characterX*characterW,characterY*characterW,characterW,characterH,0,192,64,64,false,false);
                break;
            case LEFT:
                b.draw(playerOne,characterX*characterW,characterY*characterW,characterW,characterH,0,128,64,64,false,false);

                break;
            case RIGHT:
                b.draw(playerOne,characterX*characterW,characterY*characterW,characterW,characterH,0,64,64,64,false,false);

                break;

        }
    }

    public boolean checkIfBlockAtTheFront(MapOne map){
        boolean isFreeToPass=true;
        switch (dir){
            case UP:
                int xToCheckUp=(characterX);
                int yToCheckUp=characterY+1;
                if(xToCheckUp<map.MAP_WIDTH_IN_BLOCKS && xToCheckUp>=0 && yToCheckUp<map.MAP_HEIGHT_IN_BLOCKS && xToCheckUp>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckUp][yToCheckUp].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;
            case DOWN:
                int xToCheckDown=characterX;
                int yToCheckDown=characterY-1;
                if(xToCheckDown<map.MAP_WIDTH_IN_BLOCKS && xToCheckDown>=0 && yToCheckDown<map.MAP_HEIGHT_IN_BLOCKS && xToCheckDown>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckDown][yToCheckDown].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;
            case LEFT:
                int xToCheckLeft=characterX-1;
                int yToCheckLeft=characterY;
                if(xToCheckLeft<map.MAP_WIDTH_IN_BLOCKS && xToCheckLeft>=0 && yToCheckLeft<map.MAP_HEIGHT_IN_BLOCKS && xToCheckLeft>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckLeft][yToCheckLeft].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }


                break;

            case RIGHT:
                int xToCheckRight=characterX+1;
                int yToCheckRight=characterY;

                if(xToCheckRight<map.MAP_WIDTH_IN_BLOCKS && xToCheckRight>=0 && yToCheckRight<map.MAP_HEIGHT_IN_BLOCKS && xToCheckRight>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckRight][yToCheckRight].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;

        }
        return isFreeToPass;
    }
}
