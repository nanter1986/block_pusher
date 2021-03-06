package com.nanter1986.blockpusher.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 19/10/2017.
 */

public class MainMenuPlayButton extends TouchableButton {

    public MainMenuPlayButton(DisplayToolkit tool) {
        super(tool);
        this.specificTexture = tool.manager.get("play2.png", Texture.class);
        this.buttonW = (int) screenW;
        this.buttonX = 0;
        this.buttonH = (int) screenW / 5;
        this.buttonY = 0;
        this.srcX = 0;
        this.srcY = 0;
    }

    public void drawSelf(DisplayToolkit tool) {
        float xToDraw = this.buttonX;
        float yToDraw = this.buttonY;

        tool.batch.draw(specificTexture, xToDraw, yToDraw, this.buttonW, this.buttonH);

    }
}
