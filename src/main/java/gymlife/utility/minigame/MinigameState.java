package gymlife.utility.minigame;

/**
 * The state of a minigame.
 */
public enum MinigameState {
    /**
     * The minigame has not started yet.
     */
    NOT_STARTED(
            "The minigame has not started yet."
    ),
    PRESSED_START(
            "Press start to begin the minigame."
    ),
    /**
     * The minigame is running.
     */
    RUNNING(
            "The minigame is running."
    ),
    /**
     * The player has reached the required amount for do a rep.
     */
    REP_REACHED(
            "The player has reached the required amount for do a rep."
    ),
    /**
     * The player has made a mistake.
     */
    MISTAKE_MADE(
            "The player has made a mistake."
    ),
    /**
     * The minigame has ended and the player has won.
     */
    ENDED_WON(
            "The minigame has ended and the player has won."
    ),
    /**
     * The minigame has ended and the player has lost.
     */
    ENDED_LOST(
            "The minigame has ended and the player has lost."
    );


    private final String text;

    /**
     * Constructor.
     *
     * @param text The text of the minigame state.
     */
    MinigameState(final String text) {
        this.text = text;
    }

    /**
     * Returns the text of the minigame state.
     *
     * @return The text of the minigame state.
     */
    public String getText() {
        return text;
    }
}
