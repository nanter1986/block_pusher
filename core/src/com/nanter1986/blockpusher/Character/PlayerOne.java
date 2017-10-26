package com.nanter1986.blockpusher.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.DoubleCoordSystem;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;
import com.nanter1986.blockpusher.PowerUps.Item;

import java.util.ArrayList;

/**
 * Created by user on 29/8/2017.
 */

public class PlayerOne extends MovableCharacter {
    private static final int PLAYER_MOVE_REDUCER_START = 16;
    private static final int LEVELS_PER_SPEED_BOOST = 10;
    private static final int MINIMUM_MOVE_REDUCER = 1;
    public Texture playerOne = new Texture(Gdx.files.internal("hero.png"));
    public DisplayToolkit tool;
    public  boolean stillAlive;
    public ArrayList<Item>collectedItems=new ArrayList<Item>();

    public PlayerOne(DisplayToolkit tool, GeneralMap map) {
        this.tool = tool;
        this.moveReducerLimit = getPlayerMoveReducer();
        this.texture=playerOne;

        this.characterW=tool.universalWidthFactor;
        this.characterH=tool.universalWidthFactor;
        this.dir=Direction.UP;
        stillAlive=true;
        switch (map.type) {
            case TUTORIAL1:
                this.coord = new DoubleCoordSystem(0,
                        2 * this.characterW,
                        0,
                        2,
                        this.characterW);
                break;
            default:
                this.coord = new DoubleCoordSystem(map.width * this.characterW / 2,
                        map.height * this.characterW / 2,
                        map.width / 2,
                        map.height / 2,
                        this.characterW);
        }


    }

    private int getPlayerMoveReducer() {
        int level = tool.data.readLevel();
        int moveReducer = PLAYER_MOVE_REDUCER_START - (level / LEVELS_PER_SPEED_BOOST);
        boolean isMoveReducerTooSmall = moveReducer < MINIMUM_MOVE_REDUCER;
        if (isMoveReducerTooSmall) {
            moveReducer = MINIMUM_MOVE_REDUCER;
        }
        Gdx.app.log("speed of player", "movereducer:" + moveReducer);
        return moveReducer;
    }

    public void collectItems(ArrayList<Item>allItems){
        Gdx.app.log("item collected now", ".......");
        ArrayList<Item>toMoveToInventory=new ArrayList<Item>();
        for(Item item:allItems){
            Gdx.app.log("item x", item.itemX + "");
            Gdx.app.log("player x", getFixatedX() + "");
            Gdx.app.log("item y", item.itemY + "");
            Gdx.app.log("player y", getFixatedY() + "");
            if (getFixatedX() == item.itemX && getFixatedY() == item.itemY) {
                toMoveToInventory.add(item);
            }
        }
        for(Item itemCollected:toMoveToInventory){
            allItems.remove(itemCollected);
            collectedItems.add(itemCollected);
            Gdx.app.log("item collected at:",itemCollected.itemX+"/"+itemCollected.itemY+"/"+itemCollected.getClass().toString());
        }
    }

    public void checkIfAlive(ArrayList<MovableCharacter> eArray) {
        for (MovableCharacter e : eArray) {
            if (e.explodedStarted == false && stillAlive && this.getFixatedX() == e.getFixatedX() && this.getFixatedY() == e.getFixatedY()) {
                stillAlive=false;
                explodedStarted = true;
                Gdx.app.log("player status:","DEAD");
            }
        }
    }

    @Override
    public void updatePosition(SpriteBatch b, GeneralMap map, ArrayList<MovableCharacter> characters) {
        if(stillAlive){
            switch (dir){
                case UP:
                    b.draw(playerOne, this.coord.realX, this.coord.realY, characterW, characterH, 0, 0, 500, 500, false, false);
                    break;
                case DOWN:
                    b.draw(playerOne, this.coord.realX, this.coord.realY, characterW, characterH, 0, 1500, 500, 500, false, false);
                    break;
                case LEFT:
                    b.draw(playerOne, this.coord.realX, this.coord.realY, characterW, characterH, 0, 1000, 500, 500, false, false);

                    break;
                case RIGHT:
                    b.draw(playerOne, this.coord.realX, this.coord.realY, characterW, characterH, 0, 500, 500, 500, false, false);

                    break;

            }
        }

    }

    @Override
    public void bloodAnimation(DisplayToolkit tool) {
        int widthOfBlood = tool.scW;
        int heightOfBlood = tool.scW;
        int whereToExplodeX = getFixatedX() * tool.universalWidthFactor - widthOfBlood / 2;
        int whereToExplodeY = getFixatedY() * tool.universalWidthFactor - widthOfBlood / 2;
        if (bloodDelayNumber > 0) {
            bloodDelayNumber--;
        } else {


            Gdx.app.log("blood animation:", whereToExplodeX + "/" + whereToExplodeY + "/" + widthOfBlood);
            bloodAnimationX++;
            if (bloodAnimationX == 2) {
                bloodAnimationX = 0;
                bloodAnimationY++;
            }
            Gdx.app.log("showing explosion:", bloodAnimationX + " " + bloodAnimationY + " " + blood.toString() +
                    " at " + whereToExplodeX + "/" + whereToExplodeY + " width:" + widthOfBlood);
            if (bloodAnimationY == 3) {
                explodedEnd = true;
                Gdx.app.log("explosion ended: ", explodedEnd + "");
            }
            bloodDelayNumber = 64;
        }
        int sourceX = bloodAnimationX * 500;
        int sourceY = bloodAnimationY * 500;
        Gdx.app.log("blood source at:", sourceX + "/" + sourceY);
        tool.batch.draw(blood, whereToExplodeX, whereToExplodeY, widthOfBlood, heightOfBlood, sourceX, sourceY, 500, 500, false, false);
    }

    @Override
    public void moveCharacter(GeneralMap map, ArrayList<MovableCharacter> enemies) {

    }

    @Override
    public void checkIfcrushed(GeneralMap map) {

    }

    public boolean reachedTopWall(int mapH){
        boolean reached=false;
        if (getFixatedY() >= mapH * characterH - characterH) {
            this.coord.realY = mapH * characterH - characterH;
            reached=true;
        }
        return reached;
    }

    public boolean reachedBottomWall(){
        boolean reached=false;
        if (getFixatedY() <= 0) {
            this.coord.realY = 0;
            reached=true;
        }
        return reached;
    }

    public boolean reachedLeftWall(){
        boolean reached=false;
        if (getFixatedX() <= 0) {
            this.coord.realX = 0;
            reached=true;
        }
        return reached;
    }

    public boolean reachedRightWall(int mapW){
        boolean reached=false;
        if (getFixatedX() >= mapW * characterW - characterW) {
            this.coord.realX = 50 * characterW - characterW;
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

    public boolean checkIfBlockAtTheFront(GeneralMap map) {
        boolean isFreeToPass=true;
        switch (dir){
            case UP:
                int xToCheckUp = (getFixatedX());
                int yToCheckUp = getFixatedY() + 1;
                if (xToCheckUp < map.width && xToCheckUp >= 0 && yToCheckUp < map.height && xToCheckUp >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckUp][yToCheckUp].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;
            case DOWN:
                int xToCheckDown = getFixatedX();
                int yToCheckDown = getFixatedY() - 1;
                if (xToCheckDown < map.width && xToCheckDown >= 0 && yToCheckDown < map.height && xToCheckDown >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckDown][yToCheckDown].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;
            case LEFT:
                int xToCheckLeft = getFixatedX() - 1;
                int yToCheckLeft = getFixatedY();
                if (xToCheckLeft < map.width && xToCheckLeft >= 0 && yToCheckLeft < map.height && xToCheckLeft >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckLeft][yToCheckLeft].type;
                    Gdx.app.log("type to check",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }


                break;

            case RIGHT:
                int xToCheckRight = getFixatedX() + 1;
                int yToCheckRight = getFixatedY();

                if (xToCheckRight < map.width && xToCheckRight >= 0 && yToCheckRight < map.height && xToCheckRight >= 0) {
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

    public boolean checkIfWater(GeneralMap map) {
        boolean notWater=true;
        switch (dir) {
            case UP:
                if (getFixatedY() < map.height - 2) {
                    if (map.mapArray[getFixatedX()][getFixatedY() + 1].type == BlockGeneral.Blocktypes.WATER ||
                            map.mapArray[getFixatedX()][getFixatedY() + 2].type == BlockGeneral.Blocktypes.WATER) {
                        notWater=false;
                    }
                }


                break;
            case DOWN:
                if (getFixatedY() > 1) {
                    if (map.mapArray[getFixatedX()][getFixatedY() - 1].type == BlockGeneral.Blocktypes.WATER ||
                            map.mapArray[getFixatedX()][getFixatedY() - 2].type == BlockGeneral.Blocktypes.WATER) {
                        notWater=false;
                    }
                }


                break;
            case LEFT:
                if (getFixatedX() > 1) {
                    if (map.mapArray[getFixatedX() - 1][getFixatedY()].type == BlockGeneral.Blocktypes.WATER ||
                            map.mapArray[getFixatedX() - 2][getFixatedY()].type == BlockGeneral.Blocktypes.WATER) {
                        notWater=false;
                    }
                }

                break;
            case RIGHT:
                if (getFixatedX() < map.width - 2) {
                    if (map.mapArray[getFixatedX() + 1][getFixatedY()].type == BlockGeneral.Blocktypes.WATER ||
                            map.mapArray[getFixatedX() + 2][getFixatedY()].type == BlockGeneral.Blocktypes.WATER) {
                        notWater=false;
                    }
                }


                break;
        }
        return notWater;
    }

    public boolean checkIfAirBehindBlock(GeneralMap map) {
        boolean isFreeToPass=true;
        switch (dir){
            case UP:
                int xToCheckUp = (getFixatedX());
                int yToCheckUp = getFixatedY() + 2;
                if (xToCheckUp < map.width && xToCheckUp >= 0 && yToCheckUp < map.height && yToCheckUp >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckUp][yToCheckUp].type;
                    Gdx.app.log("block behind block",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;
            case DOWN:
                int xToCheckDown = getFixatedX();
                int yToCheckDown = getFixatedY() - 2;
                if (xToCheckDown < map.width && xToCheckDown >= 0 && yToCheckDown < map.height && yToCheckDown >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckDown][yToCheckDown].type;
                    Gdx.app.log("block behind block",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;
            case LEFT:
                int xToCheckLeft = getFixatedX() - 2;
                int yToCheckLeft = getFixatedY();
                if (xToCheckLeft < map.width && xToCheckLeft >= 0 && yToCheckLeft < map.height && yToCheckLeft >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckLeft][yToCheckLeft].type;
                    Gdx.app.log("block behind block",bt.toString()+"");
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }


                break;

            case RIGHT:
                int xToCheckRight = getFixatedX() + 2;
                int yToCheckRight = getFixatedY();

                if (xToCheckRight < map.width && xToCheckRight >= 0 && yToCheckRight < map.height && yToCheckRight >= 0) {
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
