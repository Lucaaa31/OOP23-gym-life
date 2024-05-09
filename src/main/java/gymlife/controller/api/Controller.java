package gymlife.controller.api;

import gymlife.model.Minigame.MinigameManager;
import gymlife.model.Minigame.Timer;
import gymlife.utility.Directions;
import gymlife.utility.MinigameDifficulty;
import gymlife.utility.MinigameType;
import gymlife.utility.Position;

import java.awt.*;
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

    void setDifficulty(MinigameDifficulty difficulty);

    void notifyButtonPressed();

    int getTime();

    int getState();

    MinigameType getMinigameType();

    void setMinigameManager(MinigameManager minigameManager);

    boolean getVisibilityTimer();

    void notifyKeyPressed(char keyChar);
}
