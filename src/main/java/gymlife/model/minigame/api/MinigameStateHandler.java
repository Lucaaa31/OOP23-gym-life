package gymlife.model.minigame.api;

/**
 * The MinigameStateHandler interface that handles the state of the minigame.
 */
public interface MinigameStateHandler {

    /**
     * Handle the NOT_STARTED state.
     */
    void notStarted();

    /**
     * Handle the PRESSED_START state.
     */
    void pressedStartButton();

    /**
     * Handle the RUNNING state.
     */
    void running();

    /**
     * Handle the VALID_PRESS state.
     */
    void validPress();

    /**
     * Handle the INVALID_PRESS state.
     */
    void invalidPress();

    /**
     * Handle the REP_REACHED state.
     */
    void repReached();

    /**
     * Handle the MINIGAME_ENDED_WON state.
     */
    void miniGameEndedWon();

    /**
     * Handle the MINIGAME_ENDED_LOST state.
     */
    void miniGameEndedLost();

}
