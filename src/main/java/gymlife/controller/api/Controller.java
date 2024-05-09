package gymlife.controller.api;

import gymlife.model.api.GameMap;
import gymlife.utility.Directions;
import gymlife.utility.Position;

/**
 * Interface that handles key inputs from the keyboard to move the character.
 */
public interface Controller {
    /**
     * Moves the character in the specified direction.
     * 
     * @param dir the direction in which to move the character
     */
    void moveCharacter(Directions dir);

    /**
     * Returns the current position of the character.
     * 
     * @return the position of the character
     */
    Position getCharacterPos();

    /**
     * Method to go to a new map.
     * @param newMap new map to go to.
     */
    void goToNewMap(GameMap newMap);

    /**
     * Method to get the current map.
     * @return Returns the current map.
     */
    GameMap getCurrentMap();

    /**
     * Executes whatever action is associated with cell on which the character currently is.
     */
    void cellInteraction();

    int getPlayerLevel();
}
