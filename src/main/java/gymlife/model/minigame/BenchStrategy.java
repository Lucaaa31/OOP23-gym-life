package gymlife.model.minigame;

import gymlife.model.minigame.api.MinigameStateHandler;
import gymlife.utility.minigame.MinigameState;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Represents a bench minigame that implements the Minigame interface.
 */
public final class BenchStrategy extends Minigame implements MinigameStateHandler {
    private final Map<MinigameState, Consumer<MinigameStateHandler>> actionMap = new EnumMap<>(MinigameState.class);
    private final MinigameStatistics statistics = new MinigameStatistics();


    public BenchStrategy() {
        actionMap.put(MinigameState.NOT_STARTED, MinigameStateHandler::notStarted);
        actionMap.put(MinigameState.PRESSED_START, MinigameStateHandler::pressedStartButton);
        actionMap.put(MinigameState.RUNNING, MinigameStateHandler::running);
        actionMap.put(MinigameState.VALID_PRESS, MinigameStateHandler::validPress);
        actionMap.put(MinigameState.INVALID_PRESS, MinigameStateHandler::invalidPress);
        actionMap.put(MinigameState.REP_REACHED, MinigameStateHandler::repReached);
        actionMap.put(MinigameState.ENDED_WON, MinigameStateHandler::miniGameEndedWon);
        actionMap.put(MinigameState.ENDED_LOST, MinigameStateHandler::miniGameEndedLost);
    }
    /**
     * Notifies the bench minigame that a button has been pressed.
     * Starts the timer if it is the first time the button is pressed.
     *
     * @param buttonCode the button that has been pressed
     */
    @Override
    public void notifyUserAction(final String buttonCode) {
        if (getMinigameState().equals(MinigameState.REP_REACHED) || getMinigameState().equals(MinigameState.INVALID_PRESS)){
            setMinigameState(MinigameState.PRESSED_START);
        } else if (getMinigameState().equals(MinigameState.VALID_PRESS)) {
            setMinigameState(MinigameState.RUNNING);
        }
        actionMap.get(getMinigameState()).accept(this);
        System.out.println(getMinigameState());
    }



    @Override
    public void notStarted() {
        statistics.setStartMinigame();
        setMinigameState(getMinigameState().next());
    }

    @Override
    public void pressedStartButton() {
        statistics.incrementInteractions();
        statistics.resetReactionTime();
        setMinigameState(getMinigameState().next());
    }

    @Override
    public void running() {
        statistics.incrementInteractions();
        final long reactionTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - statistics.getStartReactionTime());
        statistics.resetReactionTime();
        if (conditionOfMinigame(reactionTime)) {
            validPress();
        } else {
            invalidPress();
        }
    }

    private boolean conditionOfMinigame(final long reactionTime) {
        return reactionTime < getDifficulty().getReactionTime();
    }

    @Override
    public void validPress() {
        setMinigameState(MinigameState.VALID_PRESS);
        if (statistics.getInteractions() == getDifficulty().getTouchForLift()) {
            statistics.resetInteractions();
            statistics.incrementNumReps();
            repReached();
        }
    }

    @Override
    public void invalidPress() {
        statistics.incrementMistakes();
        setMinigameState(MinigameState.INVALID_PRESS);
        if (statistics.getMistakes() > getDifficulty().getMaxMistakes()) {
            miniGameEndedLost();
        }
        statistics.resetInteractions();
    }

    @Override
    public void repReached() {
        setMinigameState(MinigameState.REP_REACHED);
        if (statistics.getNumReps() == getDifficulty().getRequiredReps()) {
            statistics.setMinigameTime();
            setMinigameState(MinigameState.ENDED_WON);
        }
    }

    @Override
    public void miniGameEndedWon() {
        setMinigameState(MinigameState.ENDED_WON);
    }

    @Override
    public void miniGameEndedLost() {
        setMinigameState(MinigameState.ENDED_LOST);
    }


    @Override
    public int getTimeMinigame() {
        return statistics.getMinigameTime();
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
