package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.blockpusher.Character.MovableCharacter;

/**
 * Created by user on 2/10/2017.
 */

public class PositionUpdater {

    private MovableCharacter character;

    public PositionUpdater( MovableCharacter character) {
        this.character = character;
    }

    public void updatePosition(SpriteBatch b) {
        switch (character.dir) {
            case UP:
                b.draw(character.texture, character.getFixatedX() * character.characterW, character.getFixatedY() * character.characterW, character.characterW, character.characterH, 0, 0, 500, 500, false, false);
                break;
            case DOWN:
                b.draw(character.texture, character.getFixatedX() * character.characterW, character.getFixatedY() * character.characterW, character.characterW, character.characterH, 0, 1500, 500, 500, false, false);
                break;
            case LEFT:
                b.draw(character.texture, character.getFixatedX() * character.characterW, character.getFixatedY() * character.characterW, character.characterW, character.characterH, 0, 1000, 500, 500, false, false);

                break;
            case RIGHT:
                b.draw(character.texture, character.getFixatedX() * character.characterW, character.getFixatedY() * character.characterW, character.characterW, character.characterH, 0, 500, 500, 500, false, false);

                break;
        }

        }
}
