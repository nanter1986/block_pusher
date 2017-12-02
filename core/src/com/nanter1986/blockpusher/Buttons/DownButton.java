package com.nanter1986.blockpusher.Buttons;

import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 4/10/2017.
 */

public class DownButton extends TouchableButton {

    public DownButton(DisplayToolkit tool) {
        super(tool);

        this.buttonW = (int) screenW / 8;
        this.buttonX = this.buttonW;
        this.buttonH = (int) screenW / 8;
        this.buttonY = 0;
        this.srcX = 0;
        this.srcY = 500;
    }
}
