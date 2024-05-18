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
    private int nTimesPressed;
    private int state;
    //private int numReps;


    /**
     * Notifies the Squat Minigame that a button has been pressed.
     * Sets the isPressed variable to true.
     */
    @Override
    public void notifyUserAction() {
        nTimesPressed++;
    }


    /**
     * The view check if the reps has been completed.
     *
     * @return true if the reps has been completed, false otherwise
     */
    @Override
    public boolean isRepsCompleted() {
        return false;
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
     * Return the state of the bench minigame.
     *
     * @return return true if the minigame is ended, false otherwise
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
    public int getEndMinigame() {
        return 0;
    }

    @Override
    public boolean getValidity() {
        return false;
    }
}
