package gymlife.model.minigame;

import gymlife.utility.minigame.DimensionMinigame;
import gymlife.utility.minigame.MinigameState;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Represents a bench minigame that implements the Minigame interface.
 */
public final class BenchMinigame extends AbstractMinigame {
    private long startReactionTime;
    private long startMinigame;
    private boolean isReactionTimeSet;
    private boolean isFirstTimePressed = true;


    /**
     * Notifies the bench minigame that a button has been pressed.
     * Starts the timer if it is the first time the button is pressed.
     *
     * @param buttonCode the button that has been pressed
     */
    @Override
    public void notifyUserAction(final int buttonCode) {
        notifyUserAction();
    }


    @Override
    public void notifyUserAction() {
        if (isFirstTimePressed) {
            isFirstTimePressed = false;
            startMinigame = System.nanoTime();
        } else {
            setMinigameState(MinigameState.RUNNING);
            incrementNTimePressed();
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
        if (conditionOfMinigame(reactionTime)) {
            if (super.handleValidPress()) {
                isReactionTimeSet = false;
                super.checkIfMinigameHasEnded(startMinigame);
            }
        } else {
            isReactionTimeSet = false;
            super.handleInvalidPress();
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
    @Override
    public boolean conditionOfMinigame(final long reactionTime) {
        return reactionTime < getDifficulty().getReactionTime();
    }

    /**
     * Returns the sequence of the bench minigame.
     *
     * @return the sequence of the bench minigame
     */
    @Override
    public List<Integer> getSequence() {
        return List.of();
    }

    @Override
    public Point getRandomPositionButton(final DimensionMinigame dimensionMinigame) {
        int x, y;
        do {
            x = (int) (Math.random() * (dimensionMinigame.widthMinigameScenario()
                    - dimensionMinigame.buttonMinigameWidth()));
            y = (int) (Math.random() * (dimensionMinigame.heightMinigameScenario()
                    - dimensionMinigame.buttonMinigameHeight()));
        } while (limits(x, y, dimensionMinigame));
        return new Point(x, y);
    }

    private boolean limits(final int x, final int y, final DimensionMinigame dimensionMinigame) {
        final int xMin = dimensionMinigame.widthMinigameScenario() / 2 - dimensionMinigame.buttonMinigameWidth();
        final int xMax = dimensionMinigame.widthMinigameScenario() / 2 + dimensionMinigame.buttonMinigameWidth();
        final int yMin = dimensionMinigame.heightMinigameScenario() / 2 - dimensionMinigame.buttonMinigameHeight();
        final int yMax = dimensionMinigame.heightMinigameScenario() / 2 + dimensionMinigame.buttonMinigameHeight();

        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }



}
