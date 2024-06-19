package gymlife.model.minigame;

import gymlife.utility.Position;
import gymlife.utility.minigame.DimensionMinigame;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * The Minigame interface represents a game that can be played within the gym.
 * It provides methods for notifying button presses, setting a timer, getting
 * the current state,
 * and setting the difficulty level of the minigame.
 */
public abstract class AbstractMinigame {
    private MinigameDifficulty difficulty;
    private MinigameState minigameState;
    private long endMinigame;
    private int nTimesPressed;
    private int nMistakes;
    private int numReps;


    /**
     * Constructs a new Minigame object and initializes the instance variables.
     */
    public AbstractMinigame() {
        this.difficulty = null;
        this.minigameState = MinigameState.NOT_STARTED;
    }

    /**
     * Increments the number of times the button has been pressed.
     */
    public void incrementNTimePressed() {
        nTimesPressed++;
    }


    /**
     * Notify the model of an action of the player.
     *
     * @param buttonCode the parameters of the action
     */
    public abstract void notifyUserAction(String buttonCode);

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

    /**
     * Sets the state of the minigame.
     *
     * @param state the state of the minigame
     */
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


    /**
     * Set the time to put in the scoringTable.
     *
     * @param endMinigame the time to put in the scoringTable
     */
    public void setEndMinigame(final long endMinigame) {
        this.endMinigame = endMinigame;
    }


    /**
     * Handles the case when the player's press is valid.
     *
     * @return true if the reps has been completed, false otherwise
     */
    public boolean handleValidPress() {
        if (nTimesPressed == getDifficulty().getTouchForLift()) {
            nTimesPressed = 0;
            numReps++;
            setMinigameState(MinigameState.REP_REACHED);
            return true;
        }
        return false;
    }

    /**
     * Handles the case when the player's press is invalid.
     */
    public void handleInvalidPress() {
        nMistakes++;
        setMinigameState(MinigameState.MISTAKE_MADE);
        if (nMistakes > getDifficulty().getMaxMistakes()) {
            setMinigameState(MinigameState.ENDED_LOST);
        }
        nTimesPressed = 0;
    }

    /**
     * Checks if the required number of reps has been completed.
     *
     * @param startMinigame the start time of the minigame
     */
    public void checkIfMinigameHasEnded(final long startMinigame) {
        if (numReps == getDifficulty().getRequiredReps()) {
            setEndMinigame(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startMinigame));
            setMinigameState(MinigameState.ENDED_WON);
        }
    }


    /**
     * Abstract method to get the sequence of the minigame.
     *
     * @return the sequence of the minigame
     */
    public abstract List<String> getSequence();


    public Position getRandomPositionButton(final DimensionMinigame dimensionMinigame){
        int x, y;
        do {
            x = (int) (Math.random() * (dimensionMinigame.widthMinigameScenario()
                    - dimensionMinigame.buttonMinigameWidth()));
            y = (int) (Math.random() * (dimensionMinigame.heightMinigameScenario()
                    - dimensionMinigame.buttonMinigameHeight()));
        } while (limits(x, y, dimensionMinigame));
        return new Position(x, y);
    }

    private boolean limits(final int x, final int y, final DimensionMinigame dimensionMinigame) {
        final int xMin = dimensionMinigame.widthMinigameScenario() / 2 - dimensionMinigame.buttonMinigameWidth();
        final int xMax = dimensionMinigame.widthMinigameScenario() / 2 + dimensionMinigame.buttonMinigameWidth();
        final int yMin = dimensionMinigame.heightMinigameScenario() / 2 - dimensionMinigame.buttonMinigameHeight();
        final int yMax = dimensionMinigame.heightMinigameScenario() / 2 + dimensionMinigame.buttonMinigameHeight();

        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }
}
