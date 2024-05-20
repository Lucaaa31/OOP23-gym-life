package gymlife.model.minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;

import java.util.concurrent.TimeUnit;

/**
 * Represents a bench minigame that implements the Minigame interface.
 */
public class BenchMinigame implements Minigame {
    private MinigameDifficulty difficulty;
    private int nTimesPressed;
    private int numReps;
    private MinigameState minigameState;
    private long startReactionTime;
    private int nMistakes;
    private long startMinigame;
    private long endMinigame;
    private boolean isFirstTimePressed = true;
    private boolean isReactionTimeSet;

    /**
     * Constructs a new BenchMinigame object and initializes the instance variables.
     */
    public BenchMinigame() {
        this.minigameState = MinigameState.NOT_STARTED;
        this.nTimesPressed = 0;
        this.numReps = 0;
        this.difficulty = null;
        this.nMistakes = 0;
    }

    /**
     * Notifies the bench minigame that a button has been pressed.
     * Starts the timer if it is the first time the button is pressed.
     */
    @Override
    public void notifyUserAction() {

        if (isFirstTimePressed) {
            isFirstTimePressed = false;
            startMinigame = System.nanoTime();
        } else {
            minigameState = MinigameState.RUNNING;
        }
        nTimesPressed++;
        if (!isReactionTimeSet) {
            resetStartReactionTime();
            isReactionTimeSet = true;
        } else {
            validatePress();
        }
    }


    /**
     * Validates the user's press timing and updates the game state accordingly.
     */
    @Override
    public void validatePress() {
        final long reactionTime = calculateReactionTime();
        resetStartReactionTime();
        if (isReactionTimeValid(reactionTime)) {
            handleValidPress();
        } else {
            handleInvalidPress();
        }
    }

    /**
     * Calculates the reaction time of the player.
     *
     * @return the reaction time of the player in milliseconds
     */
    private long calculateReactionTime() {
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startReactionTime);
    }

    /**
     * Resets the start reaction time to the current time.
     */
    private void resetStartReactionTime() {
        startReactionTime = System.nanoTime();
    }

    /**
     * Checks if the reaction time of the player is valid.
     *
     * @param reactionTime the reaction time of the player
     * @return true if the reaction time is valid, false otherwise
     */
    private boolean isReactionTimeValid(final long reactionTime) {
        return reactionTime < difficulty.getReactionTime();
    }

    /**
     * Handles the case when the player's press is valid.
     */
    private void handleValidPress() {
        if (nTimesPressed == difficulty.getTouchForLift()) {
            nTimesPressed = 0;
            numReps++;
            minigameState = MinigameState.REP_REACHED;
            isReactionTimeSet = false;
            checkIfRepsCompleted();
        }
    }

    /**
     * Handles the case when the player's press is invalid.
     */
    private void handleInvalidPress() {
        nMistakes++;
        minigameState = MinigameState.MISTAKE_MADE;
        isReactionTimeSet = false;
        if (nMistakes > difficulty.getMaxMistakes()) {
            minigameState = MinigameState.ENDED_LOST;
        }
        nTimesPressed = 0;
    }

    /**
     * Checks if the required number of reps has been completed.
     */
    private void checkIfRepsCompleted() {
        if (numReps == difficulty.getRequiredReps()) {
            endMinigame = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startMinigame);
            minigameState = MinigameState.ENDED_WON;
        }
    }

    /**
     * Sets the difficulty level for the bench minigame.
     *
     * @param selectedDifficulty the selected difficulty level for the minigame
     */
    @Override
    public void setDifficulty(final MinigameDifficulty selectedDifficulty) {
        this.difficulty = selectedDifficulty;
    }

    /**
     * Returns the current state of the bench minigame.
     *
     * @return the current state of the bench minigame
     */
    @Override
    public MinigameState getMinigameState() {
        return minigameState;
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
     * Returns the time taken by the player to complete the minigame.
     *
     * @return the time taken to complete the minigame in milliseconds
     */
    @Override
    public int getTimeMinigame() {
        return Math.toIntExact(endMinigame);
    }

}
