package gymlife.model.minigame;

import gymlife.utility.minigame.MinigameState;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Represents a bench minigame that extends Minigame Abstract class.
 */
public final class BenchStrategy extends Minigame {
    private MinigameStatistics statistics = new MinigameStatistics();


    /**
     * Notifies the bench minigame that a button has been pressed.
     *
     * @param buttonCode the button that has been pressed
     */
    @Override
    public void notifyUserAction(final String buttonCode) {
        if (getMinigameState().equals(MinigameState.REP_REACHED) || getMinigameState().equals(MinigameState.INVALID_PRESS)) {
            setMinigameState(MinigameState.PRESSED_START);
        } else if (getMinigameState().equals(MinigameState.VALID_PRESS)) {
            setMinigameState(MinigameState.RUNNING);
        }
        super.notifyUserAction(buttonCode);
    }

    /**
     * Returns the statistics of the bench minigame.
     *
     * @return the statistics of the bench minigame
     */
    @Override
    public MinigameStatistics getStatistics() {
        return statistics;
    }

    /**
     * Sets the statistics of the bench minigame.
     *
     * @param statistics the statistics of the bench minigame
     */
    @Override
    public void setStatistics(final MinigameStatistics statistics) {
        this.statistics = statistics;
    }


    /**
     * Handle the PRESSED_START state.
     */
    @Override
    public void pressedStartButton() {
        super.pressedStartButton();
        statistics = statistics.incrementInteractions();
    }

    /**
     * Handle the RUNNING state.
     */
    @Override
    public void running() {
        statistics = statistics.incrementInteractions();
        final long reactionTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - statistics.startReactionTime());
        statistics = statistics.resetReactionTime();
        if (conditionOfMinigame(reactionTime)) {
            validPress();
        } else {
            invalidPress();
        }
    }


    /**
     * The condition of the bench minigame.
     *
     * @param reactionTime the reaction time of the bench minigame
     * @return true if the reaction time is less than the difficulty reaction time, false otherwise
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
    public List<String> getSequence() {
        return List.of();
    }

}
