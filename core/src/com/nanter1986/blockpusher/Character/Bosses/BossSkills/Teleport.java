package com.nanter1986.blockpusher.Character.Bosses.BossSkills;

import com.badlogic.gdx.Gdx;
import com.nanter1986.blockpusher.Blocks.BlockGeneral;
import com.nanter1986.blockpusher.Character.MovableCharacter;
import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.GeneralMap;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 2/10/2017.
 */

public class Teleport implements BossSkill {
    public static final Random RANDOM = new Random();
    public static final Random CHANCE = new Random();

    MovableCharacter targetPlayer;

    public Teleport(MovableCharacter targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    @Override
    public void executeSkill(DisplayToolkit tool, int level, MovableCharacter character, GeneralMap map, ArrayList<MovableCharacter> enemies, ArrayList<MovableCharacter> pr) {
        if (CHANCE.nextInt(5) == 0) {
            boolean freeBlockFound = false;
            while (freeBlockFound == false) {
                int theX = RANDOM.nextInt(map.width);
                int theY = RANDOM.nextInt(map.height);
                boolean fallsOnTargetPlayer = theX == targetPlayer.coord.fixatedX && theY == targetPlayer.coord.fixatedY;
                if (map.mapArray[theX][theY].type == BlockGeneral.Blocktypes.AIR && !fallsOnTargetPlayer) {
                    Gdx.app.log("teleport test", "in" +
                            "/" + character.coord.realX +
                            "/" + character.coord.realY +
                            "/" + character.coord.fixatedX +
                            "/" + character.coord.fixatedY +
                            "/" + map.mapArray[character.coord.fixatedX][character.coord.fixatedY].type
                    );
                    freeBlockFound = true;
                    Gdx.app.log("teleport test", "in" + theX + "/" + theY + "/" + map.mapArray[theX][theY].type);
                    character.coord.realX = theX * character.characterW;
                    character.coord.realY = theY * character.characterW;
                    character.coord.fixatedX = theX;
                    character.coord.fixatedY = theY;
                    Gdx.app.log("teleport test", "in" +
                            "/" + character.coord.realX +
                            "/" + character.coord.realY +
                            "/" + character.coord.fixatedX +
                            "/" + character.coord.fixatedY +
                            "/" + map.mapArray[character.coord.fixatedX][character.coord.fixatedY].type
                    );


                }

            }
        }

    }
}
