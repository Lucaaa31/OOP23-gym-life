package gymlife.model.api;

import gymlife.model.Minigame.Timer;
import gymlife.utility.MinigameDifficulty;

/**
 * The Minigame interface represents a game that can be played within the gym.
 * It provides methods for notifying button presses, setting a timer, getting the current state,
 * and setting the difficulty level of the minigame.
 */
public interface Minigame {

    /**
     * Notifies the minigame that a button has been pressed.
     */
    void notifyButtonPressed();

    /**
     * Sets the timer for the minigame.
     *
     * @param timer the timer to be set
     */
    void setTimer(Timer timer);

    /**
     * Gets the current state of the minigame.
     *
     * @return the current state of the minigame
     */
    int getState();

    /**
     * Sets the difficulty level of the minigame.
     *
     * @param selectedDifficulty the selected difficulty level
     */
    void setDifficulty(MinigameDifficulty selectedDifficulty);
}
