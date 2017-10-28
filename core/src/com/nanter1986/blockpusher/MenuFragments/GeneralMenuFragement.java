package com.nanter1986.blockpusher.MenuFragments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 28/10/2017.
 */

public abstract class GeneralMenuFragement {
    public Texture texture;
    public int height;
    public int width;
    public int cellHeight;
    public int cellWidth;
    public float positionX;
    public float positionY;

    public void stealPosition(DisplayToolkit tool) {
        this.positionX = tool.camera.position.x - tool.scW / 2;
        this.positionY = tool.camera.position.y + tool.scH / 2 - this.height;
        Gdx.app.log("info patch position:", this.positionX + "/" + this.positionY);
    }

    public void drawFragment(DisplayToolkit tool) {
        stealPosition(tool);
        tool.batch.draw(texture, positionX, positionY, width, height);
    }

}
