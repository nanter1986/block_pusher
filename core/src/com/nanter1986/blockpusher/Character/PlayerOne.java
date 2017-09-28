package com.nanter1986.blockpusher.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.MapOne;
import com.nanter1986.blockpusher.PowerUps.Item;

import java.util.ArrayList;

/**
 * Created by user on 29/8/2017.
 */

public class PlayerOne extends MovableCharacter {
    public Texture playerOne = new Texture(Gdx.files.internal("playerone.png"));
    public  boolean stillAlive;
    public ArrayList<Item>collectedItems=new ArrayList<Item>();

    public PlayerOne(DisplayToolkit tool,MapOne map) {
        this.characterX=map.MAP_WIDTH_IN_BLOCKS/2;
        this.characterY=map.MAP_HEIGHT_IN_BLOCKS/2;
        this.characterW=tool.universalWidthFactor;
        this.characterH=tool.universalWidthFactor;
        this.dir=Direction.UP;
        stillAlive=true;
    }

    public void collectItems(ArrayList<Item>allItems){
        ArrayList<Item>toMoveToInventory=new ArrayList<Item>();
        for(Item item:allItems){
            if(characterX==item.itemX && characterY==item.itemY){
                toMoveToInventory.add(item);
            }
        }
        for(Item itemCollected:toMoveToInventory){
            allItems.remove(itemCollected);
            collectedItems.add(itemCollected);
            Gdx.app.log("item collected at:",itemCollected.itemX+"/"+itemCollected.itemY+"/"+itemCollected.getClass().toString());
        }
    }

    public void checkIfAlive(ArrayList<EnemyOne>eArray){
        for(EnemyOne e:eArray){
            if(e.explodedStarted==false && stillAlive && this.characterX==e.characterX && this.characterY==e.characterY){
                stillAlive=false;
                Gdx.app.log("player status:","DEAD");
            }
        }
    }

    @Override
    public void updatePosition(SpriteBatch b) {
        if(stillAlive){
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

    }

    @Override
    public void bloodAnimation(DisplayToolkit tool) {

    }

    public boolean reachedTopWall(int mapH){
        boolean reached=false;
        if(characterY>=mapH*characterH-characterH){
            characterY=mapH*characterH-characterH;
            reached=true;
        }
        return reached;
    }

    public boolean reachedBottomWall(){
        boolean reached=false;
        if(characterY<=0){
            characterY=0;
            reached=true;
        }
        return reached;
    }

    public boolean reachedLeftWall(){
        boolean reached=false;
        if(characterX<=0){
            characterX=0;
            reached=true;
        }
        return reached;
    }

    public boolean reachedRightWall(int mapW){
        boolean reached=false;
        if(characterX>= mapW*characterW-characterW){
            characterX=50*characterW-characterW;
            reached=true;
        }
        return reached;
    }

    public void keepPlayerInBounds(int mapW,int mapH){
        reachedLeftWall();
        reachedBottomWall();
        reachedRightWall(mapW);
        reachedTopWall(mapH);
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

    public boolean checkIfWater(MapOne map){
        boolean notWater=true;
        switch (dir) {
            case UP:
                if(characterY<map.MAP_HEIGHT_IN_BLOCKS-2){
                    if(map.mapArray[characterX][characterY+1].type== BlockGeneral.Blocktypes.WATER ||
                            map.mapArray[characterX][characterY+2].type== BlockGeneral.Blocktypes.WATER){
                        notWater=false;
                    }
                }


                break;
            case DOWN:
                if(characterY>1){
                    if(map.mapArray[characterX][characterY-1].type== BlockGeneral.Blocktypes.WATER ||
                            map.mapArray[characterX][characterY-2].type== BlockGeneral.Blocktypes.WATER){
                        notWater=false;
                    }
                }


                break;
            case LEFT:
                if(characterX>1){
                    if(map.mapArray[characterX-1][characterY].type== BlockGeneral.Blocktypes.WATER ||
                            map.mapArray[characterX-2][characterY].type== BlockGeneral.Blocktypes.WATER){
                        notWater=false;
                    }
                }

                break;
            case RIGHT:
                if(characterX<map.MAP_WIDTH_IN_BLOCKS-2){
                    if(map.mapArray[characterX+1][characterY].type== BlockGeneral.Blocktypes.WATER ||
                            map.mapArray[characterX+2][characterY].type== BlockGeneral.Blocktypes.WATER){
                        notWater=false;
                    }
                }


                break;
        }
        return notWater;
    }

    public boolean checkIfAirBehindBlock(MapOne map){
        boolean isFreeToPass=true;
        switch (dir){
            case UP:
                int xToCheckUp=(characterX);
                int yToCheckUp=characterY+2;
                if(xToCheckUp<map.MAP_WIDTH_IN_BLOCKS && xToCheckUp>=0 && yToCheckUp<map.MAP_HEIGHT_IN_BLOCKS && xToCheckUp>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckUp][yToCheckUp].type;
                    Gdx.app.log("block behind block",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;
            case DOWN:
                int xToCheckDown=characterX;
                int yToCheckDown=characterY-2;
                if(xToCheckDown<map.MAP_WIDTH_IN_BLOCKS && xToCheckDown>=0 && yToCheckDown<map.MAP_HEIGHT_IN_BLOCKS && xToCheckDown>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckDown][yToCheckDown].type;
                    Gdx.app.log("block behind block",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;
            case LEFT:
                int xToCheckLeft=characterX-2;
                int yToCheckLeft=characterY;
                if(xToCheckLeft<map.MAP_WIDTH_IN_BLOCKS && xToCheckLeft>=0 && yToCheckLeft<map.MAP_HEIGHT_IN_BLOCKS && xToCheckLeft>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckLeft][yToCheckLeft].type;
                    Gdx.app.log("block behind block",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }


                break;

            case RIGHT:
                int xToCheckRight=characterX+2;
                int yToCheckRight=characterY;

                if(xToCheckRight<map.MAP_WIDTH_IN_BLOCKS && xToCheckRight>=0 && yToCheckRight<map.MAP_HEIGHT_IN_BLOCKS && xToCheckRight>=0){
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckRight][yToCheckRight].type;
                    Gdx.app.log("block behind block",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;

        }
        return isFreeToPass;
    }
}
