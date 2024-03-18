package gymlife.model;

import gymlife.utility.Position;
import gymlife.utility.Directions;

public class Character {
    private static Position pos;

    public Character (Position initialPosition){
        pos = initialPosition;
    }

    public Position getCharacterPos(){
        return pos;
    }

    public static Position getNewPos(Directions dir){
        return pos;
    }
    

}
