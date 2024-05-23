package gymlife.model.minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;

/**
 * Represents a Squat Minigame.
 * This class implements the Minigame interface and provides the necessary
 * methods to start and manage the game.
 */
public class SquatMinigame implements Minigame {
    private MinigameDifficulty difficulty;


    /**
     * Constructor for the Squat Minigame.
     */
    public SquatMinigame() {
        this.difficulty = MinigameDifficulty.EASY;
    }

    /**
     * Notifies the Squat Minigame that a button has been pressed.
     * Sets the isPressed variable to true.
     */
    @Override
    public void notifyUserAction() {

    }


    /**
     * Sets the difficulty level of the Squat Minigame.
     *
     * @param selectedDifficulty the difficulty level to set
     */
    @Override
    public void setDifficulty(final MinigameDifficulty selectedDifficulty) {
        this.difficulty = selectedDifficulty;
    }


    /**
     * Return the state of the squat minigame.
     */
    @Override
    public MinigameState getMinigameState() {
        return null;
    }

    /**
     * Returns the difficulty level of the bench minigame.
     *
     * @return the difficulty level of the bench minigame
     */
    @Override
    public MinigameDifficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Returns the time that took the player to complete the minigame.
     *
     * @return the timer of the minigame
     */
    @Override
    public int getTimeMinigame() {
        return 0;
    }

    /**
     * Validates the press of the player.
     */
    @Override
    public void validatePress() {
    }
}
