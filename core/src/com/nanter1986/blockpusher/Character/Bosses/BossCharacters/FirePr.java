package com.nanter1986.blockpusher.Character.Bosses.BossCharacters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    ParticleEffect effect;

    public FirePr(DisplayToolkit tool, GeneralMap map, MovableCharacter shooter) {
        this.texture = tool.manager.get("villain.png", Texture.class);
        this.characterW = tool.universalWidthFactor;
        this.characterH = tool.universalWidthFactor;
        this.level = 1;
        moveReducerLimit = 16;
        deathAnimator = new ProjectileDeathAnimator(this);
        crushChecker = new ProjectileCrushChecker(this);
        stepIncreaser = new ProjectileStepIncreaser(this);
        projectileMover = new ProjectileMover(this);
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("fireball.parti"), Gdx.files.internal(""));
        effect.scaleEffect(0.4f);

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
        effect.getEmitters().first().setPosition(coord.realX, coord.realY);
        effect.start();


    }



    @Override
    public void updatePosition(SpriteBatch b, GeneralMap map, ArrayList<MovableCharacter> characters) {
        effect.getEmitters().first().setPosition(coord.realX + characterW / 2, coord.realY + characterW / 2);
        effect.update(Gdx.graphics.getDeltaTime());
        effect.draw(b);


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
