package com.nanter1986.blockpusher.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.MenuFragments.InfoPatch;

import java.util.ArrayList;

/**
 * Created by user on 4/10/2017.
 */

public abstract class TouchableButton {
    public final Texture texture = new Texture(Gdx.files.internal("dirbuttons.png"));
    public int buttonX;
    public int buttonY;
    public int buttonW;
    public int buttonH;
    public boolean touchedOnce;
    public float screenW;
    public float screenH;
    public Texture specificTexture;

    public int srcX;
    public int srcY;


    public TouchableButton(DisplayToolkit tool) {
        this.screenH = tool.scH;
        this.screenW = tool.scW;
        this.specificTexture = texture;

    }

    public static ArrayList<TouchableButton> dirPad(DisplayToolkit tool) {
        ArrayList<TouchableButton> dirpad = new ArrayList<TouchableButton>();
        dirpad.add(new UpButton(tool));
        dirpad.add(new DownButton(tool));
        dirpad.add(new LeftButton(tool));
        dirpad.add(new RightButton(tool));
        dirpad.add(new BombButton(tool));

        return dirpad;
    }

    public boolean isButtonTouched() {
        boolean t = false;
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isTouched() && x > buttonX && x < buttonX + buttonW && y < screenH - buttonY && y > screenH - buttonY - buttonH) {
            t = true;
        }
        return t;

    }

    public void drawSelf(DisplayToolkit tool, InfoPatch info) {
        float xToDraw = tool.camera.position.x - tool.scW / 2 + this.buttonX;
        float yToDraw = tool.camera.position.y - tool.scH / 2 + this.buttonY;
        /*Gdx.app.log("draw self", "drawing button:" + this.getClass().toString() +
                "\n" + xToDraw + "/" + yToDraw +
                "\n" + buttonW +
                "\n" + srcX + "/" + srcY);*/
        tool.batch.draw(specificTexture, xToDraw, yToDraw, this.buttonW, this.buttonW, this.srcX, this.srcY, 500, 500, false, false);
    }

    public void drawSelf(DisplayToolkit tool) {
        float xToDraw = this.buttonX;
        float yToDraw = this.buttonY;
        /*Gdx.app.log("draw self", "drawing button:" + this.getClass().toString() +
                "\n" + xToDraw + "/" + yToDraw +
                "\n" + buttonW +
                "\n" + srcX + "/" + srcY);*/
        tool.batch.draw(specificTexture, xToDraw, yToDraw, this.buttonW, this.buttonW, this.srcX, this.srcY, 500, 500, false, false);
    }
}
