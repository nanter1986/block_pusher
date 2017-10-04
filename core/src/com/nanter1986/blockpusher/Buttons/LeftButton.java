package com.nanter1986.blockpusher.Buttons;

import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 4/10/2017.
 */

public class LeftButton extends TouchableButton {
    public LeftButton(DisplayToolkit tool) {
        super(tool);

        this.buttonW = (int) screenW / 9;
        this.buttonX = (int) screenW - 3 * this.buttonW;
        this.buttonH = (int) screenW / 9;
        this.buttonY = this.buttonW;
        this.srcX = 0;
        this.srcY = 1000;
    }
}
