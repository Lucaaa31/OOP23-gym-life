package gymlife.model.minigame;

import gymlife.utility.minigame.MinigameState;

import java.util.concurrent.TimeUnit;

/**
 * Represents a bench minigame that implements the Minigame interface.
 */
public final class BenchMinigame extends AbstractMinigame {
    private int nTimesPressed = 0;
    private int numReps = 0;
    private long startReactionTime = 0;
    private int nMistakes = 0;
    private long startMinigame = 0;
    private boolean isReactionTimeSet = false;
    private boolean isFirstTimePressed = true;


    /**
     * Constructs a new BenchMinigame object and initializes the instance variables.
     */
    public BenchMinigame() {
        super();
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
            setMinigameState(MinigameState.RUNNING);
            nTimesPressed++;
            if (!isReactionTimeSet) {
                resetStartReactionTime();
                isReactionTimeSet = true;
            } else {
                validatePress();
            }
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
        return reactionTime < getDifficulty().getReactionTime();
    }

    /**
     * Handles the case when the player's press is valid.
     */
    private void handleValidPress() {
        if (nTimesPressed == getDifficulty().getTouchForLift()) {
            nTimesPressed = 0;
            numReps++;
            setMinigameState(MinigameState.REP_REACHED);
            isReactionTimeSet = false;
            checkIfMinigameHasEnded();
        }
    }

    /**
     * Handles the case when the player's press is invalid.
     */
    private void handleInvalidPress() {
        nMistakes++;
        setMinigameState(MinigameState.MISTAKE_MADE);
        isReactionTimeSet = false;
        if (nMistakes > getDifficulty().getMaxMistakes()) {
            setMinigameState(MinigameState.ENDED_LOST);
        }
        nTimesPressed = 0;
    }

    /**
     * Checks if the required number of reps has been completed.
     */
    private void checkIfMinigameHasEnded() {
        if (numReps == getDifficulty().getRequiredReps()) {
            setEndMinigame(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startMinigame));
            setMinigameState(MinigameState.ENDED_WON);
        }
    }

}
