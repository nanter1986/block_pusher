package com.nanter1986.blockpusher.Buttons;

import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 16/10/2017.
 */

public class NextWinScreenButton extends TouchableButton {
    public NextWinScreenButton(DisplayToolkit tool) {
        super(tool);
        this.buttonW = (int) screenW / 3;
        this.buttonX = tool.scW - this.buttonW;
        this.buttonH = (int) screenW / 3;
        this.buttonY = 0;
        this.srcX = 0;
        this.srcY = 1500;
    }
}
