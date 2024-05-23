package gymlife.model.api;

import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;

/**
 * The Minigame interface represents a game that can be played within the gym.
 * It provides methods for notifying button presses, setting a timer, getting
 * the current state,
 * and setting the difficulty level of the minigame.
 */
public interface Minigame {
    /**
     * Notifies the minigame that a button has been pressed.
     */
    void notifyUserAction();

    /**
     * Sets the difficulty level of the minigame.
     *
     * @param selectedDifficulty the selected difficulty level
     */
    void setDifficulty(MinigameDifficulty selectedDifficulty);

    /**
     * Returns the state of the minigame.
     *
     * @return 0 if the minigame isn't started, 1 if the minigame is running, 2 if the minigame is ended
     */
    MinigameState getMinigameState();

    /**
     * Returns the difficulty level of the minigame.
     *
     * @return the difficulty level of the minigame
     */
    MinigameDifficulty getDifficulty();

    /**
     * Returns the time to complete the minigame.
     *
     * @return the time to complete the minigame
     */
    int getTimeMinigame();


    /**
     * Checks the validity of the press.
     */
    void validatePress();

}
