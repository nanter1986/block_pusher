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
                dialog = regularGameplay(stage, senction);
                break;
            case TUTORIAL1:
                dialog = tutorialOne(senction);
                break;
            case TUTORIAL2:
                dialog = tutorialTwo(senction);
                break;
        }
        return dialog;
    }

    private String tutorialTwo(int senction) {
        String dialog = "";
        switch (senction) {
            case 0:
                dialog = "Use the bomb to blow up one of the blocks\nso that you can push the others.";
                break;
            case 1:
                dialog = "";
                break;
            case 2:
                dialog = "";
                break;
        }
        return dialog;
    }

    private String tutorialOne(int senction) {
        String dialog = "";
        switch (senction) {
            case 0:
                dialog = "hello there player,welcome!";
                break;
            case 1:
                dialog = "";
                break;
            case 2:
                dialog = "";
                break;
        }
        return dialog;
    }

    private String regularGameplay(int stage, int senction) {
        String dialog = "";
        switch (stage) {
            case 0:
                dialog = regularGameplaySenctionDialogsOne(senction);
                break;
            case 1:
                dialog = regularGameplaySenctionDialogsTwo(senction);
                break;
            case 2:
                dialog = regularGameplaySenctionDialogsThree(senction);
                break;
        }
        return dialog;
    }

    private String regularGameplaySenctionDialogsTwo(int senction) {
        String dialog = "";
        switch (senction) {
            case 0:
                dialog = "";
                break;
            case 1:
                dialog = "";
                break;
            case 2:
                dialog = "";
                break;
        }
        return dialog;
    }

    private String regularGameplaySenctionDialogsThree(int senction) {
        String dialog = "";
        switch (senction) {
            case 0:
                dialog = "";
                break;
            case 1:
                dialog = "";
                break;
            case 2:
                dialog = "";
                break;
        }
        return dialog;
    }

    private String regularGameplaySenctionDialogsOne(int senction) {
        String dialog = "";
        switch (senction) {
            case 0:
                dialog = "";
                break;
            case 1:
                dialog = "";
                break;
            case 2:
                dialog = "";
                break;
        }
        return dialog;
    }
}
