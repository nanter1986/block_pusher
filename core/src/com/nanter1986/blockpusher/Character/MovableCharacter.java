package com.nanter1986.blockpusher.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Character.Bosses.BossCharacters.Projectile;
import com.nanter1986.blockpusher.Character.Bosses.BossSkills.BossSkill;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.DoubleCoordSystem;
import com.nanter1986.blockpusher.DataControl.DataControler;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;

/**
 * Created by user on 29/8/2017.
 */

public abstract class MovableCharacter {
    public final Texture blood = new Texture(Gdx.files.internal("blood.png"));
    public Texture texture;
    public DataControler data;
    public DoubleCoordSystem coord;
    public int characterW;
    public int characterH;
    public int level;
    public int moveReducer = 0;
    public int moveReducerLimit;
    public boolean stepSequenceRunning = false;


    public Direction dir = Direction.UP;

    public int bloodAnimationX = 0;
    public int bloodAnimationY = 0;
    public boolean explodedStarted = false;
    public boolean explodedEnd = false;
    public boolean crushed = false;
    public int bloodDelayNumber = 0;

    public ArrayList<BossSkill> skills = new ArrayList<BossSkill>();


    public abstract void updatePosition(SpriteBatch b, GeneralMap map, ArrayList<MovableCharacter> characters);

    public abstract void bloodAnimation(DisplayToolkit tool);

    public abstract void moveCharacter(GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<Projectile> projectiles);

    public abstract void checkIfcrushed(GeneralMap map);

    public abstract void increaseByStep(GeneralMap map);



    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
