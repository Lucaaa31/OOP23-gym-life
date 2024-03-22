package gymlife.model;

import gymlife.utility.Position;
import gymlife.utility.Directions;

public class CharacterModel {
    private static Position pos;

    public CharacterModel (Position initialPosition){
        pos = new Position(0, 0);
    }

    public Position getCharacterPos(){
        return pos;
    }

    public static Position getNewPos(Directions dir){
        return pos;
    }
}
