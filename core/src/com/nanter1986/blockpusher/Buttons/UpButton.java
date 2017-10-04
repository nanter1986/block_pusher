package com.nanter1986.blockpusher.Buttons;

import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 4/10/2017.
 */

public class UpButton extends TouchableButton {
    public UpButton(DisplayToolkit tool) {
        super(tool);

        this.buttonW = (int) screenW / 9;
        this.buttonX = (int) screenW - 2 * this.buttonW;
        this.buttonH = (int) screenW / 9;
        this.buttonY = this.buttonW * 2;
        this.srcX = 0;
        this.srcY = 0;
    }
}
