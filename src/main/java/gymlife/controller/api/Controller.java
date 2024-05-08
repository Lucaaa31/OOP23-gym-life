package gymlife.controller.api;

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
    
    void startMultiplier(float money);

    float getTreshold();

    float getMultiplier();

    void controllerStopMultiplier();

    float controllerGetMoney();

    

}
