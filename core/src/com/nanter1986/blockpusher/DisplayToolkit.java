package com.nanter1986.blockpusher;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.nanter1986.blockpusher.Buttons.TouchableButton;
import com.nanter1986.blockpusher.DataControl.DataControler;

import java.util.ArrayList;

/**
 * Created by user on 29/8/2017.
 */

public class DisplayToolkit implements Disposable {
    public AssetManager manager;
    public Preferences prefs;
    public SpriteBatch batch;
    public BitmapFont font;
    public OrthographicCamera camera;
    public int scW;
    public int scH;
    public int universalWidthFactor;
    public DataControler data;
    public ArrayList<TouchableButton> dirpad = new ArrayList<TouchableButton>();
    private boolean android;

    public DisplayToolkit(int screenWidth,int screenHeight) {
        manager = new AssetManager();
        prefs= Gdx.app.getPreferences("Pusher");
        android = Gdx.app.getType() == Application.ApplicationType.Android;
        scW=screenWidth;
        scH=screenHeight;
        universalWidthFactor=screenWidth/20;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(screenWidth, screenHeight);
        camera.position.set(screenWidth / 2, screenHeight / 2, 100);
        data = new DataControler(this);
        loadAssets();


        font = new BitmapFont(Gdx.files.internal("weird.fnt"));
        font.setColor(0.9f, 0.1f, 0.1f, 1.0f);
        if (android) {
            dirpad = TouchableButton.dirPad(this);
        }
        Gdx.app.log("display toolkit created:","scW:"+scW+" scH:"+scH+" universal width factor:"+universalWidthFactor+" camera position:"+camera.position);
    }

    private void loadAssets() {
        manager.load("weird.fnt", BitmapFont.class);
        manager.load("air.png", Texture.class);
        manager.load("water.png", Texture.class);
        manager.load("food.png", Texture.class);
        manager.load("stone.png", Texture.class);
        manager.load("rock.png", Texture.class);
        manager.load("hero.png", Texture.class);
        manager.load("villain.png", Texture.class);
        manager.load("blood.png", Texture.class);
        manager.load("explosion.png", Texture.class);
        manager.load("waterkingSheet.png", Texture.class);
        //manager.load("pack.atlas", TextureAtlas.class);
        manager.finishLoading();


    }

    @Override
    public void dispose() {
        manager.dispose();
        batch.dispose();
        font.dispose();
    }
}
