package com.nanter1986.blockpusher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.DataControl.DataControler;

/**
 * Created by user on 29/8/2017.
 */

public class DisplayToolkit {
    public Preferences prefs;
    public SpriteBatch batch;
    public BitmapFont font;
    public OrthographicCamera camera;
    public int scW;
    public int scH;
    public int universalWidthFactor;
    public DataControler data;

    public DisplayToolkit(int screenWidth,int screenHeight) {
        prefs= Gdx.app.getPreferences("Pusher");
        scW=screenWidth;
        scH=screenHeight;
        universalWidthFactor=screenWidth/20;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(screenWidth, screenHeight);
        camera.position.set(screenWidth / 2, screenHeight / 2, 100);
        data = new DataControler(this);

        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.setColor(0.9f, 0.1f, 0.1f, 1.0f);
        Gdx.app.log("display toolkit created:","scW:"+scW+" scH:"+scH+" universal width factor:"+universalWidthFactor+" camera position:"+camera.position);


    }
}
