package com.nanter1986.blockpusher.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.DoubleCoordSystem;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.StepIncreaser;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;
import com.nanter1986.blockpusher.PowerUps.Item;

import java.util.ArrayList;

/**
 * Created by user on 29/8/2017.
 */

public class PlayerOne extends MovableCharacter {
    private static final int PLAYER_MOVE_REDUCER_START = 16;
    private static final int LEVELS_PER_SPEED_BOOST = 100;
    private static final int MINIMUM_MOVE_REDUCER = 2;
    public DisplayToolkit tool;
    public  boolean stillAlive;
    public ArrayList<Item>collectedItems=new ArrayList<Item>();

    public PlayerOne(DisplayToolkit tool, GeneralMap map) {
        this.tool = tool;
        this.moveReducerLimit = getPlayerMoveReducer();
        this.texture = tool.manager.get("hero.png", Texture.class);

        this.characterW=tool.universalWidthFactor;
        this.characterH=tool.universalWidthFactor;
        this.dir=Direction.UP;
        stillAlive=true;
        switch (map.type) {
            case DEBUG_NITAR:
                this.coord = new DoubleCoordSystem(0,
                        2 * this.characterW,
                        0,
                        2,
                        this.characterW);
                break;
            case DEBUG_ENEMY:
                this.coord = new DoubleCoordSystem(0,
                        2 * this.characterW,
                        0,
                        2,
                        this.characterW);
                break;
            case TUTORIAL1:
                this.coord = new DoubleCoordSystem(0,
                        2 * this.characterW,
                        0,
                        2,
                        this.characterW);
                break;
            case TUTORIAL2:
                this.coord = new DoubleCoordSystem(0,
                        2 * this.characterW,
                        0,
                        2,
                        this.characterW);
                break;
            default:
                whereToSpawnPlayerInRegularGame(map);

        }


    }

    private void whereToSpawnPlayerInRegularGame(GeneralMap map) {
        if (map.width % 2 == 0 && map.height % 2 == 0) {
            this.coord = new DoubleCoordSystem(map.width * this.characterW / 2,
                    map.height * this.characterW / 2,
                    map.width / 2,
                    map.height / 2,
                    this.characterW);
        } else if (map.width % 2 == 0) {
            this.coord = new DoubleCoordSystem(map.width * this.characterW / 2,
                    (map.height - 1) * this.characterW / 2,
                    map.width / 2,
                    (map.height - 1) / 2,
                    this.characterW);
        } else if (map.height % 2 == 0) {
            this.coord = new DoubleCoordSystem((map.width - 1) * this.characterW / 2,
                    map.height * this.characterW / 2,
                    (map.width - 1) / 2,
                    map.height / 2,
                    this.characterW);
        } else {
            this.coord = new DoubleCoordSystem((map.width - 1) * this.characterW / 2,
                    (map.height - 1) * this.characterW / 2,
                    (map.width - 1) / 2,
                    (map.height - 1) / 2,
                    this.characterW);
        }
        map.mapArray[coord.fixatedX][coord.fixatedY].type = BlockGeneral.Blocktypes.AIR;
        map.mapArray[coord.fixatedX][coord.fixatedY].setTile(tool);
    }

    private int getPlayerMoveReducer() {
        int level = tool.data.readLevel();
        int moveReducer = PLAYER_MOVE_REDUCER_START - (level / LEVELS_PER_SPEED_BOOST);
        boolean isMoveReducerTooSmall = moveReducer < MINIMUM_MOVE_REDUCER;
        if (isMoveReducerTooSmall) {
            moveReducer = MINIMUM_MOVE_REDUCER;
        }
        return moveReducer;
    }

    public void collectItems(ArrayList<Item>allItems){
        ArrayList<Item>toMoveToInventory=new ArrayList<Item>();
        for(Item item:allItems){
            if (coord.getFixatedX() == item.itemX && coord.getFixatedY() == item.itemY) {
                toMoveToInventory.add(item);
            }
        }
        for(Item itemCollected:toMoveToInventory){
            allItems.remove(itemCollected);
            collectedItems.add(itemCollected);
        }
    }

    public void checkIfAlive(ArrayList<MovableCharacter> eArray) {
        for (MovableCharacter e : eArray) {
            if (e.explodedStarted == false && stillAlive && this.coord.getFixatedX() == e.coord.getFixatedX() && this.coord.getFixatedY() == e.coord.getFixatedY()) {
                stillAlive=false;
                explodedStarted = true;
            }
        }
    }

    @Override
    public void updatePosition(SpriteBatch b, GeneralMap map, ArrayList<MovableCharacter> characters) {
        if(stillAlive){
            switch (dir){
                case UP:
                    b.draw(texture, this.coord.realX, this.coord.realY, characterW, characterH, 0, 0, 500, 500, false, false);
                    break;
                case DOWN:
                    b.draw(texture, this.coord.realX, this.coord.realY, characterW, characterH, 0, 1500, 500, 500, false, false);
                    break;
                case LEFT:
                    b.draw(texture, this.coord.realX, this.coord.realY, characterW, characterH, 0, 1000, 500, 500, false, false);

                    break;
                case RIGHT:
                    b.draw(texture, this.coord.realX, this.coord.realY, characterW, characterH, 0, 500, 500, 500, false, false);

                    break;

            }
        }

    }

    @Override
    public void bloodAnimation(DisplayToolkit tool) {
        int widthOfBlood = tool.scW;
        int heightOfBlood = tool.scW;
        int whereToExplodeX = coord.getFixatedX() * tool.universalWidthFactor - widthOfBlood / 2;
        int whereToExplodeY = coord.getFixatedY() * tool.universalWidthFactor - widthOfBlood / 2;
        if (bloodDelayNumber > 0) {
            bloodDelayNumber--;
        } else {


            bloodAnimationX++;
            if (bloodAnimationX == 2) {
                bloodAnimationX = 0;
                bloodAnimationY++;
            }
            if (bloodAnimationY == 3) {
                explodedEnd = true;
                Gdx.app.log("explosion ended: ", explodedEnd + "");
            }
            bloodDelayNumber = 64;
        }
        int sourceX = bloodAnimationX * 500;
        int sourceY = bloodAnimationY * 500;
        Gdx.app.log("blood source at:", sourceX + "/" + sourceY);
        tool.batch.draw(tool.manager.get("blood.png", Texture.class), whereToExplodeX, whereToExplodeY, widthOfBlood, heightOfBlood, sourceX, sourceY, 500, 500, false, false);
    }

    @Override
    public void moveCharacter(GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<MovableCharacter> projectiles) {

    }


    @Override
    public void checkIfcrushed(GeneralMap map) {

    }

    @Override
    public void increaseByStep(GeneralMap map) {
        new StepIncreaser(this).increaseByStep(map);
    }

    public boolean reachedTopWall(int mapH){
        boolean reached=false;
        if (coord.getFixatedY() >= mapH * characterH - characterH) {
            this.coord.realY = mapH * characterH - characterH;
            reached=true;
        }
        return reached;
    }

    public boolean reachedBottomWall(){
        boolean reached=false;
        if (coord.getFixatedY() <= 0) {
            this.coord.realY = 0;
            reached=true;
        }
        return reached;
    }

    public boolean reachedLeftWall(){
        boolean reached=false;
        if (coord.getFixatedX() <= 0) {
            this.coord.realX = 0;
            reached=true;
        }
        return reached;
    }

    public boolean reachedRightWall(int mapW){
        boolean reached=false;
        if (coord.getFixatedX() >= mapW * characterW - characterW) {
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
                int xToCheckUp = (coord.getFixatedX());
                int yToCheckUp = coord.getFixatedY() + 1;
                if (xToCheckUp < map.width && xToCheckUp >= 0 && yToCheckUp < map.height && xToCheckUp >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckUp][yToCheckUp].type;
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;
            case DOWN:
                int xToCheckDown = coord.getFixatedX();
                int yToCheckDown = coord.getFixatedY() - 1;
                if (xToCheckDown < map.width && xToCheckDown >= 0 && yToCheckDown < map.height && xToCheckDown >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckDown][yToCheckDown].type;
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;
            case LEFT:
                int xToCheckLeft = coord.getFixatedX() - 1;
                int yToCheckLeft = coord.getFixatedY();
                if (xToCheckLeft < map.width && xToCheckLeft >= 0 && yToCheckLeft < map.height && xToCheckLeft >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckLeft][yToCheckLeft].type;
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }


                break;

            case RIGHT:
                int xToCheckRight = coord.getFixatedX() + 1;
                int yToCheckRight = coord.getFixatedY();

                if (xToCheckRight < map.width && xToCheckRight >= 0 && yToCheckRight < map.height && xToCheckRight >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckRight][yToCheckRight].type;
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
                if (coord.getFixatedY() < map.height - 2) {
                    if (map.mapArray[coord.getFixatedX()][coord.getFixatedY() + 1].type == BlockGeneral.Blocktypes.WATER ||
                            map.mapArray[coord.getFixatedX()][coord.getFixatedY() + 2].type == BlockGeneral.Blocktypes.WATER) {
                        notWater=false;
                    }
                }


                break;
            case DOWN:
                if (coord.getFixatedY() > 1) {
                    if (map.mapArray[coord.getFixatedX()][coord.getFixatedY() - 1].type == BlockGeneral.Blocktypes.WATER ||
                            map.mapArray[coord.getFixatedX()][coord.getFixatedY() - 2].type == BlockGeneral.Blocktypes.WATER) {
                        notWater=false;
                    }
                }


                break;
            case LEFT:
                if (coord.getFixatedX() > 1) {
                    if (map.mapArray[coord.getFixatedX() - 1][coord.getFixatedY()].type == BlockGeneral.Blocktypes.WATER ||
                            map.mapArray[coord.getFixatedX() - 2][coord.getFixatedY()].type == BlockGeneral.Blocktypes.WATER) {
                        notWater=false;
                    }
                }

                break;
            case RIGHT:
                if (coord.getFixatedX() < map.width - 2) {
                    if (map.mapArray[coord.getFixatedX() + 1][coord.getFixatedY()].type == BlockGeneral.Blocktypes.WATER ||
                            map.mapArray[coord.getFixatedX() + 2][coord.getFixatedY()].type == BlockGeneral.Blocktypes.WATER) {
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
                int xToCheckUp = (coord.getFixatedX());
                int yToCheckUp = coord.getFixatedY() + 2;
                if (xToCheckUp < map.width && xToCheckUp >= 0 && yToCheckUp < map.height && yToCheckUp >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckUp][yToCheckUp].type;
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;
            case DOWN:
                int xToCheckDown = coord.getFixatedX();
                int yToCheckDown = coord.getFixatedY() - 2;
                if (xToCheckDown < map.width && xToCheckDown >= 0 && yToCheckDown < map.height && yToCheckDown >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckDown][yToCheckDown].type;
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;
            case LEFT:
                int xToCheckLeft = coord.getFixatedX() - 2;
                int yToCheckLeft = coord.getFixatedY();
                if (xToCheckLeft < map.width && xToCheckLeft >= 0 && yToCheckLeft < map.height && yToCheckLeft >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckLeft][yToCheckLeft].type;
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }


                break;

            case RIGHT:
                int xToCheckRight = coord.getFixatedX() + 2;
                int yToCheckRight = coord.getFixatedY();

                if (xToCheckRight < map.width && xToCheckRight >= 0 && yToCheckRight < map.height && yToCheckRight >= 0) {
                    BlockGeneral.Blocktypes bt=map.mapArray[xToCheckRight][yToCheckRight].type;
                    if(bt!= BlockGeneral.Blocktypes.AIR){
                        isFreeToPass=false;
                    }
                }

                break;

        }
        return isFreeToPass;
    }
}
