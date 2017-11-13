package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.DoubleCoordSystem;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.ProjectileCrushChecker;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.ProjectileDeathAnimator;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.ProjectileMover;
import com.nanter1986.blockpusher.Character.Bosses.BossUtilities.StepIncreaser;
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
    StepIncreaser stepIncreaser;
    ProjectileMover projectileMover;

    public FirePr(DisplayToolkit tool, GeneralMap map, MovableCharacter shooter) {
        this.texture = tool.manager.get("villain.png", Texture.class);
        this.characterW = tool.universalWidthFactor;
        this.characterH = tool.universalWidthFactor;
        this.level = 1;
        moveReducerLimit = 16;
        deathAnimator = new ProjectileDeathAnimator(this);
        crushChecker = new ProjectileCrushChecker(this);
        stepIncreaser = new StepIncreaser(this);
        projectileMover = new ProjectileMover(this);
        switch (shooter.dir) {
            case UP:
                this.coord = new DoubleCoordSystem(shooter.coord.fixatedX * this.characterW,
                        (shooter.coord.fixatedY + 1) * this.characterW,
                        shooter.coord.fixatedX,
                        (shooter.coord.fixatedY + 1),
                        this.characterW);
                this.dir = Direction.UP;
                break;
            case DOWN:
                this.coord = new DoubleCoordSystem(shooter.coord.fixatedX * this.characterW,
                        (shooter.coord.fixatedY - 1) * this.characterW,
                        shooter.coord.fixatedX,
                        (shooter.coord.fixatedY - 1),
                        this.characterW);
                this.dir = Direction.DOWN;
                break;
            case LEFT:
                this.coord = new DoubleCoordSystem((shooter.coord.fixatedX - 1) * this.characterW,
                        shooter.coord.fixatedY * this.characterW,
                        (shooter.coord.fixatedX - 1),
                        shooter.coord.fixatedY,
                        this.characterW);
                this.dir = Direction.LEFT;
                break;
            case RIGHT:
                this.coord = new DoubleCoordSystem((shooter.coord.fixatedX + 1) * this.characterW,
                        shooter.coord.fixatedY * this.characterW,
                        (shooter.coord.fixatedX + 1),
                        shooter.coord.fixatedY,
                        this.characterW);
                this.dir = Direction.RIGHT;
                break;

        }


    }



    @Override
    public void updatePosition(SpriteBatch b, GeneralMap map, ArrayList<MovableCharacter> characters) {
        switch (dir) {
            case UP:
                b.draw(texture, this.coord.realX, this.coord.realY, characterW, characterH, 0, 0, 500, 500, false, false);
                break;
            case DOWN:
                b.draw(texture, this.coord.realX, this.coord.realY, characterW, characterH, 0, 1500, 500, 500, false, false);
                break;
            case LEFT:
                b.draw(texture, this.coord.realX, this.coord.realY, characterW, characterH, 0, 1000, 500, 500, false, false);
                break;
            case RIGHT:
                b.draw(texture, this.coord.realX, this.coord.realY, characterW, characterH, 0, 500, 500, 500, false, false);

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
