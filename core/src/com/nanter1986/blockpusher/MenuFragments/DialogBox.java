package com.nanter1986.blockpusher.MenuFragments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 28/10/2017.
 */

public class DialogBox extends GeneralMenuFragement {
    public final Texture blackInfoPatch = new Texture(Gdx.files.internal("blackinfopatch.png"));
    InfoPatch info;

    public DialogBox(DisplayToolkit tool, InfoPatch infoPatch) {
        this.texture = blackInfoPatch;
        this.height = infoPatch.height;
        this.width = infoPatch.width;
        this.cellHeight = height / 10;
        this.cellWidth = width / 10;
        this.info = infoPatch;

    }

    public void stealPosition(DisplayToolkit tool) {
        this.positionX = info.positionX;
        this.positionY = info.positionY - info.height;
        Gdx.app.log("info patch position:", this.positionX + "/" + this.positionY);
    }

    public void drawText(DisplayToolkit tool, String text) {
        drawFragment(tool);
        tool.font.draw(tool.batch, text, positionX, positionY + 10 * cellHeight);

    }
}
