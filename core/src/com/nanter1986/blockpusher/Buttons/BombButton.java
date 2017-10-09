package com.nanter1986.blockpusher.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 7/10/2017.
 */

public class BombButton extends TouchableButton {
    public BombButton(DisplayToolkit tool) {
        super(tool);
        this.specificTexture = new Texture(Gdx.files.internal("bombbutton.png"));
        this.buttonW = (int) screenW / 3;
        this.buttonX = 0;
        this.buttonH = (int) screenW / 3;
        this.buttonY = 0;
        this.srcX = 0;
        this.srcY = 0;
    }
}
