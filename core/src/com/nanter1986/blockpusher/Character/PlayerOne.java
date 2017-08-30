package com.nanter1986.blockpusher.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 29/8/2017.
 */

public class PlayerOne extends MovableCharacter {
    public Texture playerOne = new Texture(Gdx.files.internal("playerone.png"));

    public PlayerOne(DisplayToolkit tool) {
        this.characterX=tool.scW/2;
        this.characterY=tool.scH/2;
        this.characterW=tool.universalWidthFactor;
        this.characterH=tool.universalWidthFactor;
        this.dir=Direction.UP;
    }

    @Override
    public void updatePosition(SpriteBatch b) {
        Gdx.app.log("drwdir",dir.toString());
        switch (dir){
            case UP:
                b.draw(playerOne,characterX,characterY,characterW,characterH,0,0,64,64,false,false);
                //b.draw(playerOne,characterX,characterY,characterW,characterH);
                break;
            case DOWN:
                b.draw(playerOne,characterX,characterY,characterW,characterH,0,192,64,64,false,false);
                break;
            case LEFT:
                b.draw(playerOne,characterX,characterY,characterW,characterH,0,128,64,64,false,false);

                break;

            case RIGHT:
                b.draw(playerOne,characterX,characterY,characterW,characterH,0,64,64,64,false,false);

                break;

        }
    }

    public boolean reachedTopWall(){
        boolean reached=false;
        if(characterX<=0){
            reached=true;
        }
        return reached;
    }

    public boolean reachedBottomWall(){
        boolean reached=false;
        if(characterX>=50*characterW){
            reached=true;
        }
        return reached;
    }

    public boolean reachedLeftWall(){
        boolean reached=false;
        if(characterY<=0){
            reached=true;
        }
        return reached;
    }

    public boolean reachedRightWall(){
        boolean reached=false;
        if(characterX>=50*characterW){
            reached=true;
        }
        return reached;
    }
}
