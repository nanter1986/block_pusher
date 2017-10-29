package com.nanter1986.blockpusher.MenuFragments;

import com.nanter1986.blockpusher.Map.GeneralMap;

/**
 * Created by user on 29/10/2017.
 */

public class DialogChooser {
    public String giveDialog(GeneralMap map, int stage, int senction) {
        String dialog = "";
        switch (map.type) {
            case REGULAR:
                regularGameplay();
                break;
            case TUTORIAL1:
                tutorialOne();
                break;
            case TUTORIAL2:
                tutorialTwo();
                break;
        }
        return dialog;
    }

    private String tutorialTwo() {
        return null;
    }

    private String tutorialOne() {
        return null;
    }

    private String regularGameplay() {
        return null;
    }
}
