package com.nanter1986.blockpusher.Blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

/**
 * Created by user on 28/9/2017.
 */

public class OutsideWall implements Disposable {
    public DisplayToolkit tool;

    public OutsideWall(DisplayToolkit tool) {
        this.tool=tool;
    }

    public void drawSelf(GeneralMap map) {
        for(int xLeft=-1;xLeft>=-3;xLeft--){
            for (int yLeft = -3; yLeft < map.height + 3; yLeft++) {
                int xLeftWithWidth=xLeft*tool.universalWidthFactor;
                int yLeftWithWidth=yLeft*tool.universalWidthFactor;
                //Gdx.app.log("xLeft/yLeft:",xLeftWithWidth+"/"+yLeftWithWidth);
                tool.batch.draw(tool.manager.get("rock.png", Texture.class), xLeftWithWidth, yLeft * tool.universalWidthFactor, tool.universalWidthFactor, tool.universalWidthFactor);
                tool.batch.draw(tool.manager.get("rock.png", Texture.class), xLeftWithWidth, yLeft * tool.universalWidthFactor, tool.universalWidthFactor, tool.universalWidthFactor);
                tool.batch.draw(tool.manager.get("rock.png", Texture.class), xLeftWithWidth, yLeft * tool.universalWidthFactor, tool.universalWidthFactor, tool.universalWidthFactor);
            }
        }

        for (int xRight = map.width; xRight < map.width + 3; xRight++) {
            for (int yRight = -3; yRight < map.height + 3; yRight++) {
                int xRightWithWidth=xRight*tool.universalWidthFactor;
                int yRightWithWidth=yRight*tool.universalWidthFactor;
                //Gdx.app.log("xRight/yRight:",xRightWithWidth+"/"+yRightWithWidth);
                tool.batch.draw(tool.manager.get("rock.png", Texture.class), xRightWithWidth, yRight * tool.universalWidthFactor, tool.universalWidthFactor, tool.universalWidthFactor);
                tool.batch.draw(tool.manager.get("rock.png", Texture.class), xRightWithWidth, yRight * tool.universalWidthFactor, tool.universalWidthFactor, tool.universalWidthFactor);
                tool.batch.draw(tool.manager.get("rock.png", Texture.class), xRightWithWidth, yRight * tool.universalWidthFactor, tool.universalWidthFactor, tool.universalWidthFactor);
            }
        }

        for (int xTop = 0; xTop < map.width; xTop++) {
            for (int yTop = map.height; yTop < map.height + 3; yTop++) {
                int xTopWithWidth=xTop*tool.universalWidthFactor;
                int yTopWithWidth=yTop*tool.universalWidthFactor;
                //Gdx.app.log("xTop/yTop:",xTopWithWidth+"/"+yTopWithWidth);
                tool.batch.draw(tool.manager.get("rock.png", Texture.class), xTopWithWidth, yTop * tool.universalWidthFactor, tool.universalWidthFactor, tool.universalWidthFactor);
                tool.batch.draw(tool.manager.get("rock.png", Texture.class), xTopWithWidth, yTop * tool.universalWidthFactor, tool.universalWidthFactor, tool.universalWidthFactor);
                tool.batch.draw(tool.manager.get("rock.png", Texture.class), xTopWithWidth, yTop * tool.universalWidthFactor, tool.universalWidthFactor, tool.universalWidthFactor);
            }
        }

        for (int xBottom = 0; xBottom < map.width; xBottom++) {
            for(int yBottom=-1;yBottom>=-3;yBottom--){
                int xBottomWithWidth=xBottom*tool.universalWidthFactor;
                int yBottomWithWidth=yBottom*tool.universalWidthFactor;
                //Gdx.app.log("xTop/yTop:",xBottomWithWidth+"/"+yBottomWithWidth);
                tool.batch.draw(tool.manager.get("rock.png", Texture.class), xBottomWithWidth, yBottom * tool.universalWidthFactor, tool.universalWidthFactor, tool.universalWidthFactor);
                tool.batch.draw(tool.manager.get("rock.png", Texture.class), xBottomWithWidth, yBottom * tool.universalWidthFactor, tool.universalWidthFactor, tool.universalWidthFactor);
                tool.batch.draw(tool.manager.get("rock.png", Texture.class), xBottomWithWidth, yBottom * tool.universalWidthFactor, tool.universalWidthFactor, tool.universalWidthFactor);
            }
        }


    }

    @Override
    public void dispose() {
        this.tool = null;
    }
}
