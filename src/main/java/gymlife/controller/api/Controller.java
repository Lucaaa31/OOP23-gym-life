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

    /**
     * Starts the multiplier with the specified money value.
     * 
     * @param money the money value.
     */
    void startMultiplier(float money);

    /**
     * Returns the value of the new threshold.
     * 
     * @return The new threshold.
     */
    float randomizeNewThreshold();

    /**
     * Returns the threshold of the multiplier.
     * 
     * @return The value of the multiplier's threshold.
     */
    float getTreshold();

    /**
     * Return the current value of the multiplier.
     * 
     * @return the current value of the multiplier.
     */
    float getMultiplier();

    /**
     * Stops the multiplier controlled by the controller.
     */
    void controllerStopMultiplier();

    /**
     * Returns the value of the money controlled by the controller.
     * 
     * @return the current value of the money.
     */
    float controllerGetMoney();

}
