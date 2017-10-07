package com.nanter1986.blockpusher.InScreenDrawer;

import com.nanter1986.blockpusher.DisplayToolkit;

/**
 * Created by user on 7/10/2017.
 */

public class CheckIfInScreenToDraw {
    public static boolean checkIfInScreen(DisplayToolkit tool, float x, float y) {
        boolean inScreen = false;
        float camLeftLimit = tool.camera.position.x - tool.scW / 2 - 2 * tool.universalWidthFactor;
        float camRightLimit = tool.camera.position.x + tool.scW / 2 + 2 * tool.universalWidthFactor;
        float camTopLimit = tool.camera.position.y + tool.scH / 2 + 2 * tool.universalWidthFactor;
        float camBottomLimit = tool.camera.position.y - tool.scH / 2 - 2 * tool.universalWidthFactor;

        if (x > camLeftLimit
                && x < camRightLimit
                && y > camBottomLimit
                && y < camTopLimit) {
            inScreen = true;
        }


        return inScreen;

    }
}
