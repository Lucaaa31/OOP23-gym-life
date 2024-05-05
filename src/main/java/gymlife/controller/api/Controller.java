package gymlife.controller.api;

import gymlife.model.Minigame.Timer;
import gymlife.utility.Directions;
import gymlife.utility.MinigameDifficulty;
import gymlife.utility.Position;

import java.util.List;

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

    void setMinigame(MinigameDifficulty difficulty);

    void notifyKeyPressed(char keyChar);

    List<Integer> getTime();

}
