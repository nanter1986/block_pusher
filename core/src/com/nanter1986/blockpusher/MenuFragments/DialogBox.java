package com.nanter1986.blockpusher.MenuFragments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 28/10/2017.
 */

public class DialogBox extends GeneralMenuFragement {
    public final Texture blackInfoPatch = new Texture(Gdx.files.internal("blackinfopatch.png"));


    public DialogBox(DisplayToolkit tool) {
        this.texture = blackInfoPatch;
        this.height = tool.scH / 2;
        this.width = tool.scW;
        this.cellHeight = height / 10;
        this.cellWidth = width / 10;

    }

    public void drawText(DisplayToolkit tool, String text) {
        drawFragment(tool);
        tool.font.draw(tool.batch, text, positionX, positionY + 10 * cellHeight);

    }
}
