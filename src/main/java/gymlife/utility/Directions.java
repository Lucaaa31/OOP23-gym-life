package gymlife.utility;

import gymlife.utility.Position;

public enum Directions {
    UP("w");


    private final String key;

    private Directions(String key){
        this.key = key;
    }
}
