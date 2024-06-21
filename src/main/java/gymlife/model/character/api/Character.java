package gymlife.model.character.api;



import gymlife.utility.Direction;
import gymlife.utility.Position;
/**
*  Class responsable for Character model.
*/
public interface Character {
    /**
    *  Return Character position.
    * @return Position
    */
    Position getCharacterPos();
    /**
    *  Keylistener responsable for managing movement inputs.
    * @param dir
    */
    void move(Direction dir);

    /**
     * Moves the character to a specific location.
     * @param pos new position to move the player to.
     */
    void setPosition(Position pos);
}
