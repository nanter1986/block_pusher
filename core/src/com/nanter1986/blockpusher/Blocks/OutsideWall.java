package com.nanter1986.blockpusher.Blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

/**
 * Created by user on 28/9/2017.
 */

public class OutsideWall {
    public final Texture wallRock = new Texture(Gdx.files.internal("rock.png"));
    public DisplayToolkit tool;

    public OutsideWall(DisplayToolkit tool) {
        this.tool=tool;
        Gdx.app.log("outside wall texture:",wallRock.toString());
    }

    public void drawSelf(GeneralMap map) {
        for(int xLeft=-1;xLeft>=-3;xLeft--){
            for(int yLeft=-3;yLeft<53;yLeft++){
                int xLeftWithWidth=xLeft*tool.universalWidthFactor;
                int yLeftWithWidth=yLeft*tool.universalWidthFactor;
                //Gdx.app.log("xLeft/yLeft:",xLeftWithWidth+"/"+yLeftWithWidth);
                tool.batch.draw(wallRock,xLeftWithWidth,yLeft*tool.universalWidthFactor,tool.universalWidthFactor,tool.universalWidthFactor);
                tool.batch.draw(wallRock,xLeftWithWidth,yLeft*tool.universalWidthFactor,tool.universalWidthFactor,tool.universalWidthFactor);
                tool.batch.draw(wallRock,xLeftWithWidth,yLeft*tool.universalWidthFactor,tool.universalWidthFactor,tool.universalWidthFactor);
            }
        }

        for(int xRight=50;xRight<=52;xRight++){
            for(int yRight=-3;yRight<53;yRight++){
                int xRightWithWidth=xRight*tool.universalWidthFactor;
                int yRightWithWidth=yRight*tool.universalWidthFactor;
                //Gdx.app.log("xRight/yRight:",xRightWithWidth+"/"+yRightWithWidth);
                tool.batch.draw(wallRock,xRightWithWidth,yRight*tool.universalWidthFactor,tool.universalWidthFactor,tool.universalWidthFactor);
                tool.batch.draw(wallRock,xRightWithWidth,yRight*tool.universalWidthFactor,tool.universalWidthFactor,tool.universalWidthFactor);
                tool.batch.draw(wallRock,xRightWithWidth,yRight*tool.universalWidthFactor,tool.universalWidthFactor,tool.universalWidthFactor);
            }
        }

        for(int xTop=0;xTop<=49;xTop++){
            for(int yTop=50;yTop<=52;yTop++){
                int xTopWithWidth=xTop*tool.universalWidthFactor;
                int yTopWithWidth=yTop*tool.universalWidthFactor;
                //Gdx.app.log("xTop/yTop:",xTopWithWidth+"/"+yTopWithWidth);
                tool.batch.draw(wallRock,xTopWithWidth,yTop*tool.universalWidthFactor,tool.universalWidthFactor,tool.universalWidthFactor);
                tool.batch.draw(wallRock,xTopWithWidth,yTop*tool.universalWidthFactor,tool.universalWidthFactor,tool.universalWidthFactor);
                tool.batch.draw(wallRock,xTopWithWidth,yTop*tool.universalWidthFactor,tool.universalWidthFactor,tool.universalWidthFactor);
            }
        }

        for(int xBottom=0;xBottom<=49;xBottom++){
            for(int yBottom=-1;yBottom>=-3;yBottom--){
                int xBottomWithWidth=xBottom*tool.universalWidthFactor;
                int yBottomWithWidth=yBottom*tool.universalWidthFactor;
                //Gdx.app.log("xTop/yTop:",xBottomWithWidth+"/"+yBottomWithWidth);
                tool.batch.draw(wallRock,xBottomWithWidth,yBottom*tool.universalWidthFactor,tool.universalWidthFactor,tool.universalWidthFactor);
                tool.batch.draw(wallRock,xBottomWithWidth,yBottom*tool.universalWidthFactor,tool.universalWidthFactor,tool.universalWidthFactor);
                tool.batch.draw(wallRock,xBottomWithWidth,yBottom*tool.universalWidthFactor,tool.universalWidthFactor,tool.universalWidthFactor);
            }
        }


    }
}
