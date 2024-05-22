package gymlife.model.api;

import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;

/**
 * The Minigame interface represents a game that can be played within the gym.
 * It provides methods for notifying button presses, setting a timer, getting
 * the current state,
 * and setting the difficulty level of the minigame.
 */
public abstract class Minigame {
    private MinigameDifficulty difficulty;
    private MinigameState minigameState;
    private long endMinigame;



    /**
     * Constructs a new Minigame object and initializes the instance variables.
     */
    public Minigame() {
        this.difficulty = MinigameDifficulty.EASY;
        this.minigameState = MinigameState.NOT_STARTED;
    }

    /**
     * Notifies the minigame that a button has been pressed.
     */
    public abstract void notifyUserAction();

    /**
     * Sets the difficulty level of the minigame.
     *
     * @param selectedDifficulty the selected difficulty level
     */
    public void setDifficulty(final MinigameDifficulty selectedDifficulty) {
        this.difficulty = selectedDifficulty;
    }

    /**
     * Returns the state of the minigame.
     *
     * @return 0 if the minigame isn't started, 1 if the minigame is running, 2 if the minigame is ended
     */
    public MinigameState getMinigameState() {
        return minigameState;
    }

    /**
     * Returns the difficulty level of the minigame.
     *
     * @return the difficulty level of the minigame
     */
    public MinigameDifficulty getDifficulty() {
        return difficulty;
    }

    public void setMinigameState(final MinigameState state) {
        this.minigameState = state;
    }

    /**
     * Returns the time to complete the minigame.
     *
     * @return the time to complete the minigame
     */
    public int getTimeMinigame() {
        return Math.toIntExact(endMinigame);
    }


    /**
     * Checks the validity of the press.
     */
    public abstract void validatePress();

    public void setEndMinigame(long endMinigame) {
        this.endMinigame = endMinigame;
    }
}
