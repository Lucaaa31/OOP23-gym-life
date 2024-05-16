package gymlife.model.api;

import gymlife.utility.minigame.MinigameDifficulty;

/**
 * The Minigame interface represents a game that can be played within the gym.
 * It provides methods for notifying button presses, setting a timer, getting
 * the current state,
 * and setting the difficulty level of the minigame.
 */
public interface Minigame {

    /**
     * Notifies the minigame that a button has been pressed.
     *
     */
    void notifyUserAction();

    boolean isRepsCompleted();

    /**
     * Sets the difficulty level of the minigame.
     *
     * @param selectedDifficulty the selected difficulty level
     */
    void setDifficulty(MinigameDifficulty selectedDifficulty);


    int minigameResult();

    boolean isMinigameEnded();

    MinigameDifficulty getDifficulty();

    long getEndMinigame();


}
