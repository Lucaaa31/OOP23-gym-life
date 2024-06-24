package gymlife.model.minigame;

import gymlife.model.minigame.api.MinigameStateHandler;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


/**
 * The Minigame interface represents a game that can be played within the gym.
 * It provides methods for notifying button presses, setting a timer, getting
 * the current state and setting the difficulty level of the minigame.
 */
public abstract class Minigame implements MinigameStateHandler {
    private MinigameDifficulty difficulty;
    private MinigameState minigameState = MinigameState.NOT_STARTED;
    private MinigameStatistics statistics;
    private final Map<MinigameState, Consumer<MinigameStateHandler>> actionMap = new EnumMap<>(MinigameState.class);

    /**
     * Constructs a new Minigame object and initializes the instance variables.
     */
    public Minigame() {
        this.difficulty = null;
        statistics = new MinigameStatistics();
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
     * Returns the statistics of the minigame.
     *
     * @return the statistics of the minigame
     */
    public abstract MinigameStatistics getStatistics();


    /**
     * Notify the model of an action of the player in the lat and squat mini-games.
     *
     * @param buttonCode the parameters of the action
     */
    public void notifyUserAction(final String buttonCode) {
        actionMap.get(getMinigameState()).accept(this);
    }


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
     * Returns the time to complete the minigame.
     *
     * @return the time to complete the minigame
     */
    public int getTimeMinigame() {
        return Math.toIntExact(getStatistics().minigameTime());
    }

    /**
     * Sets the statistics of the minigame.
     *
     * @param statistics the statistics of the minigame
     */
    public abstract void setStatistics(MinigameStatistics statistics);

    /**
     * Handle the NOT_STARTED state.
     */
    @Override
    public void notStarted() {
        statistics = getStatistics();
        createRandomSequence();
        statistics = statistics.startMinigameTime();
        setMinigameState(getMinigameState().next());
        setStatistics(statistics);
    }

    /**
     * Handle the PRESSED_START state.
     */
    @Override
    public void pressedStartButton() {
        statistics = getStatistics();
        statistics = statistics.resetReactionTime();
        setMinigameState(getMinigameState().next());
        setStatistics(statistics);
    }


    /**
     * Handle the VALID_PRESS state.
     */
    @Override
    public void validPress() {
        statistics = getStatistics();
        setMinigameState(MinigameState.VALID_PRESS);
        if (statistics.interactions() == getDifficulty().getTouchForLift()) {
            statistics = statistics.incrementNumReps();
            statistics = statistics.resetInteractions();
            repReached();
        }
        setStatistics(statistics);
    }

    /**
     * Handle the INVALID_PRESS state.
     */
    @Override
    public void invalidPress() {
        statistics = getStatistics();
        statistics = statistics.incrementMistakes();
        setMinigameState(MinigameState.INVALID_PRESS);
        if (statistics.mistakes() > getDifficulty().getMaxMistakes()) {
            miniGameEndedLost();
        }
        statistics = statistics.resetInteractions();
        setStatistics(statistics);
    }

    /**
     * Handle the REP_REACHED state.
     */
    @Override
    public void repReached() {
        setMinigameState(MinigameState.REP_REACHED);
        if (statistics.numReps() == getDifficulty().getRequiredReps()) {
            statistics = statistics.calculateMinigameTime();
            setMinigameState(MinigameState.ENDED_WON);
        }
    }

    /**
     * Handle the ENDED_WON state.
     */
    @Override
    public void miniGameEndedWon() {
        setMinigameState(MinigameState.ENDED_WON);
    }

    /**
     * Handle the ENDED_LOST state.
     */
    @Override
    public void miniGameEndedLost() {
        setMinigameState(MinigameState.ENDED_LOST);
    }

    /**
     * Abstract method to get the sequence of the minigame.
     *
     * @return the sequence of the minigame
     */
    public abstract List<String> getSequence();

    /**
     * Creates a random sequence depending on the minigame.
     */
    public void createRandomSequence() {
    }

    /**
     * The condition of the minigame.
     *
     * @param reactionTime the reaction time of the user
     * @return true if the condition is met, false otherwise
     */
    public abstract boolean conditionOfMinigame(long reactionTime);

    /**
     * Sets the state of the minigame.
     *
     * @param minigameState the state of the minigame
     */
    public void setMinigameState(final MinigameState minigameState) {
        this.minigameState = minigameState;
    }
}
