package com.nanter1986.blockpusher.Map;

import com.nanter1986.blockpusher.DisplayToolkit;
import com.nanter1986.blockpusher.Map.TutorialMaps.TutorialOne;
import com.nanter1986.blockpusher.Map.TutorialMaps.TutorialTwo;

/**
 * Created by user on 26/10/2017.
 */

public class MapChooser {

    GameplayTypes type;
    DisplayToolkit tool;

    public MapChooser(GameplayTypes type, DisplayToolkit tool) {
        this.type = type;
        this.tool = tool;
    }

    public GeneralMap giveMap() {
        GeneralMap theMap = null;
        switch (type) {
            case REGULAR:
                theMap = new MapOne(tool);
                break;
            case TUTORIAL1:
                theMap = new TutorialOne(tool);
                break;
            case TUTORIAL2:
                theMap = new TutorialTwo(tool);
                break;
        }

        return theMap;
    }


}
