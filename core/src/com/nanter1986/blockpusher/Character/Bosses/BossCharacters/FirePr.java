package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.DoubleCoordSystem;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.ProjectileCrushChecker;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.ProjectileDeathAnimator;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.ProjectileMover;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.ProjectileStepIncreaser;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;

/**
 * Created by user on 10/11/2017.
 */

public class FirePr extends MovableCharacter {
    ProjectileDeathAnimator deathAnimator;
    ProjectileCrushChecker crushChecker;
    ProjectileStepIncreaser stepIncreaser;
    ProjectileMover projectileMover;
    int frameCounter;
    int timeOfEachFrame;
    Sprite sprite;
    DisplayToolkit tool;
    TextureAtlas atlas;
    float xToDraw;
    float yToDraw;


    public FirePr(DisplayToolkit tool, GeneralMap map, MovableCharacter shooter) {
        this.tool = tool;
        this.texture = tool.manager.get("villain.png", Texture.class);
        this.characterW = tool.universalWidthFactor;
        this.characterH = tool.universalWidthFactor;
        this.atlas = tool.manager.get("flameSheet/pack.atlas", TextureAtlas.class);
        this.level = 1;
        this.timeOfEachFrame = 5;
        moveReducerLimit = 16;
        frameCounter = 0;
        deathAnimator = new ProjectileDeathAnimator(this);
        crushChecker = new ProjectileCrushChecker(this);
        stepIncreaser = new ProjectileStepIncreaser(this);
        projectileMover = new ProjectileMover(this);


        switch (shooter.dir) {
            case UP:
                this.coord = new DoubleCoordSystem(shooter.coord.fixatedX * this.characterW,
                        (shooter.coord.fixatedY + 1) * this.characterW,
                        shooter.coord.fixatedX,
                        (shooter.coord.fixatedY + 1),
                        this.characterW);
                this.dir = Direction.UP;
                sprite = new Sprite(tool.manager.get("flameSheet/pack.atlas", TextureAtlas.class).findRegion("flameUp0"));
                break;
            case DOWN:
                this.coord = new DoubleCoordSystem(shooter.coord.fixatedX * this.characterW,
                        (shooter.coord.fixatedY - 1) * this.characterW,
                        shooter.coord.fixatedX,
                        (shooter.coord.fixatedY - 1),
                        this.characterW);
                this.dir = Direction.DOWN;
                sprite = new Sprite(tool.manager.get("flameSheet/pack.atlas", TextureAtlas.class).findRegion("flameDown0"));
                break;
            case LEFT:
                this.coord = new DoubleCoordSystem((shooter.coord.fixatedX - 1) * this.characterW,
                        shooter.coord.fixatedY * this.characterW,
                        (shooter.coord.fixatedX - 1),
                        shooter.coord.fixatedY,
                        this.characterW);
                this.dir = Direction.LEFT;
                sprite = new Sprite(tool.manager.get("flameSheet/pack.atlas", TextureAtlas.class).findRegion("flameLeft0"));
                break;
            case RIGHT:
                this.coord = new DoubleCoordSystem((shooter.coord.fixatedX + 1) * this.characterW,
                        shooter.coord.fixatedY * this.characterW,
                        (shooter.coord.fixatedX + 1),
                        shooter.coord.fixatedY,
                        this.characterW);
                this.dir = Direction.RIGHT;
                sprite = new Sprite(tool.manager.get("flameSheet/pack.atlas", TextureAtlas.class).findRegion("flameRight0"));
                break;

        }


    }



    @Override
    public void updatePosition(SpriteBatch b, GeneralMap map, ArrayList<MovableCharacter> characters) {
        frameSetter();
        Gdx.app.log("frame", characterW + " ");
        xToDraw = this.coord.realX + (tool.universalWidthFactor - characterW) / 2;
        yToDraw = this.coord.realY + (tool.universalWidthFactor - characterW) / 2;
        b.draw(sprite, xToDraw, yToDraw, characterW, characterW);


    }

    private void frameSetter() {
        switch (dir) {
            case UP:
                sprite.setRegion(atlas.findRegion("flameUp0"));
                if (frameCounter >= 0 && frameCounter <= timeOfEachFrame) {
                    characterW = tool.universalWidthFactor;
                    frameCounter++;
                } else if (frameCounter > timeOfEachFrame && frameCounter <= 2 * timeOfEachFrame) {
                    characterW = (int) (tool.universalWidthFactor * 0.75);
                    frameCounter++;
                } else if (frameCounter > timeOfEachFrame && frameCounter <= 3 * timeOfEachFrame) {
                    characterW = (int) (tool.universalWidthFactor * 0.5);
                    frameCounter++;
                } else if (frameCounter > timeOfEachFrame && frameCounter <= timeOfEachFrame) {
                    characterW = (int) (tool.universalWidthFactor * 0.75);
                    frameCounter++;
                } else {
                    frameCounter = 0;
                }
                break;
            case DOWN:
                sprite.setRegion(atlas.findRegion("flameDown0"));
                if (frameCounter >= 0 && frameCounter <= timeOfEachFrame) {
                    characterW = tool.universalWidthFactor;
                    frameCounter++;
                } else if (frameCounter > timeOfEachFrame && frameCounter <= 2 * timeOfEachFrame) {
                    characterW = (int) (tool.universalWidthFactor * 0.75);
                    frameCounter++;
                } else if (frameCounter > timeOfEachFrame && frameCounter <= 3 * timeOfEachFrame) {
                    characterW = (int) (tool.universalWidthFactor * 0.5);
                    frameCounter++;
                } else if (frameCounter > timeOfEachFrame && frameCounter <= timeOfEachFrame) {
                    characterW = (int) (tool.universalWidthFactor * 0.75);
                    frameCounter++;
                } else {
                    frameCounter = 0;
                }
                break;
            case LEFT:
                sprite.setRegion(atlas.findRegion("flameLeft0"));
                if (frameCounter >= 0 && frameCounter <= timeOfEachFrame) {
                    characterW = tool.universalWidthFactor;
                    frameCounter++;
                } else if (frameCounter > timeOfEachFrame && frameCounter <= 2 * timeOfEachFrame) {
                    characterW = (int) (tool.universalWidthFactor * 0.75);
                    frameCounter++;
                } else if (frameCounter > timeOfEachFrame && frameCounter <= 3 * timeOfEachFrame) {
                    characterW = (int) (tool.universalWidthFactor * 0.5);
                    frameCounter++;
                } else if (frameCounter > timeOfEachFrame && frameCounter <= timeOfEachFrame) {
                    characterW = (int) (tool.universalWidthFactor * 0.75);
                    frameCounter++;
                } else {
                    frameCounter = 0;
                }
                break;
            case RIGHT:
                sprite.setRegion(atlas.findRegion("flameRight0"));
                if (frameCounter >= 0 && frameCounter <= timeOfEachFrame) {
                    characterW = tool.universalWidthFactor;
                    frameCounter++;
                } else if (frameCounter > timeOfEachFrame && frameCounter <= 2 * timeOfEachFrame) {
                    characterW = (int) (tool.universalWidthFactor * 0.75);
                    frameCounter++;
                } else if (frameCounter > timeOfEachFrame && frameCounter <= 3 * timeOfEachFrame) {
                    characterW = (int) (tool.universalWidthFactor * 0.5);
                    frameCounter++;
                } else if (frameCounter > timeOfEachFrame && frameCounter <= timeOfEachFrame) {
                    characterW = (int) (tool.universalWidthFactor * 0.75);
                    frameCounter++;
                } else {
                    frameCounter = 0;
                }
                break;

        }
    }

    @Override
    public void bloodAnimation(DisplayToolkit tool) {
        deathAnimator.animate(tool);
    }

    @Override
    public void checkIfcrushed(GeneralMap map) {
        crushChecker.checkIfcrushed(map);
    }

    @Override
    public void increaseByStep(GeneralMap map) {
        stepIncreaser.increaseByStep(map);
    }

    @Override
    public void moveCharacter(GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<MovableCharacter> projectiles) {
        projectileMover.moveProjectile(map, enemies, projectiles);
    }
}
