package gymlife.model.Minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.MinigameDifficulty;

/**
 * Represents a Squat Minigame.
 * This class implements the Minigame interface and provides the necessary
 * methods to start and manage the game.
 */
public class SquatMinigame implements Minigame {

    /**
     * Starts the Squat Minigame.
     */
    public void start() {

    }

    /**
     * Notifies the Squat Minigame that a button has been pressed.
     * Sets the isPressed variable to true.
     */
    @Override
    public void notifyButtonPressed() {
    }

    /**
     * Sets the timer for the Squat Minigame.
     * Creates a new thread for the timer and sets the running time according to the
     * difficulty level.
     */
    @Override
    public void setTimer(final TimerImpl timer) {

    }

    /**
     * Gets the current state of the Squat Minigame.
     *
     * @return the current state of the Squat Minigame
     */
    @Override
    public int getState() {
        return 0;
    }

    /**
     * Sets the difficulty level of the Squat Minigame.
     * 
     * @param selectedDifficulty the difficulty level to set
     */
    @Override
    public void setDifficulty(final MinigameDifficulty selectedDifficulty) {

    }

    /**
     * Gets if the Timer is running.
     * 
     * @return true if the Timer is running, false otherwise
     */
    @Override
    public boolean isAlive() {
        return false;
    }
}
