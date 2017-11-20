package com.nanter1986.blockpusher.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.blockpusher.DisplayToolkit;

import java.util.ArrayList;

/**
 * Created by user on 4/10/2017.
 */

public abstract class TouchableButton {
    public final Texture texture = new Texture(Gdx.files.internal("dirbuttons.png"));
    public DisplayToolkit tool;
    public int buttonX;
    public int buttonY;
    public int buttonW;
    public int buttonH;
    public float screenW;
    public float screenH;
    public Texture specificTexture;

    public int srcX;
    public int srcY;


    public TouchableButton(DisplayToolkit tool) {
        this.tool = tool;
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
        int buttonOnscreenXleft = buttonX;
        int buttonOnscreenXright = buttonX + buttonW;
        int buttonOnscreenYtop = (int) (screenH - buttonY);
        int buttonOnscreenYbottom = (int) (screenH - buttonY - buttonH);
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        Gdx.app.log("touch", x + "/" + y);
        Gdx.app.log("buttonX", buttonOnscreenXleft + "/" + buttonOnscreenXright);
        Gdx.app.log("buttonY", buttonOnscreenYtop + "/" + buttonOnscreenYbottom);
        if (Gdx.input.isTouched() && x > buttonOnscreenXleft && x < buttonOnscreenXright && y < buttonOnscreenYtop && y > buttonOnscreenYbottom) {
            t = true;
        }
        return t;

    }

    public void drawSelf(DisplayToolkit tool) {
        int toDrawX = (int) (tool.camera.position.x - screenW / 2 + this.buttonX);
        int toDrawY = (int) (tool.camera.position.y - screenW / 2 + this.buttonY);
        tool.batch.draw(specificTexture, toDrawX, toDrawY, this.buttonW, this.buttonW, this.srcX, this.srcY, 500, 500, false, false);
    }


}
