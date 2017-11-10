package com.nanter1986.blockpusher.Character.Bosses.BossUtilities;

/**
 * Created by user on 11/10/2017.
 */

public class DoubleCoordSystem {
    public float realX;
    public float realY;
    public int fixatedX;
    public int fixatedY;
    public int characterW;

    public DoubleCoordSystem(float realX, float realY, int fixatedX, int fixatedY, int characterW) {
        this.realX = realX;
        this.realY = realY;
        this.fixatedX = fixatedX;
        this.fixatedY = fixatedY;
        this.characterW = characterW;
    }

    public void fixateX() {
        if (realX > fixatedX * characterW) {
            fixatedX++;
            realX = fixatedX * characterW;
        } else if (realX < fixatedX * characterW) {
            fixatedX--;
            realX = fixatedX * characterW;
        } else {
            realX = fixatedX * characterW;
        }
    }

    public void fixateY() {
        if (realY > fixatedY * characterW) {
            fixatedY++;
            realY = fixatedY * characterW;
        } else if (realY < fixatedY * characterW) {
            fixatedY--;
            realY = fixatedY * characterW;
        } else {
            realY = fixatedY * characterW;
        }
    }

    public int getFixatedX() {
        return fixatedX;

    }

    public int getFixatedY() {
        return fixatedY;
    }


    public void fixatePosition() {
        fixateX();
        fixateY();
    }
}
