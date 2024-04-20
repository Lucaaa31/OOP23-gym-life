package gymlife.model.api;



import gymlife.utility.Directions;
import gymlife.utility.Position;
/**
*  Class responsable for Character model.
*/
public interface CharacterModel {
    /**
    *  Return Character position.
    * @return Position
    */
    Position getCharacterPos();
    /**
    *  Keylistener responsable for managing movement inputs.
    * @param dir
    */
    void move(Directions dir);

}
