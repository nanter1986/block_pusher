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
                b.draw(new TextureRegion(playerOne),characterX,characterY,playerOne.getWidth()/2.0f,playerOne.getHeight()/2.0f,characterW,characterH,1f,1f,90f,true);
                //b.draw(playerOne,characterX,characterY,characterW,characterH);
                break;
            case DOWN:
                b.draw(new TextureRegion(playerOne),characterX,characterY,playerOne.getWidth()/2.0f,playerOne.getHeight()/2.0f,characterW,characterH,1f,1f,-90f,true);
                break;
            case LEFT:
                b.draw(new TextureRegion(playerOne),characterX,characterY,playerOne.getWidth()/2.0f,playerOne.getHeight()/2.0f,characterW,characterH,1f,1f,0f,false);

                break;

            case RIGHT:
                b.draw(new TextureRegion(playerOne),characterX,characterY,playerOne.getWidth()/2.0f,playerOne.getHeight()/2.0f,characterW,characterH,1f,1f,0f,true);

                break;

        }
    }
}
