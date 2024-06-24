package gymlife.utility.minigame;

/**
 * The state of a minigame.
 */
public enum MinigameState {
    /**
     * The minigame has not started yet.
     */
    NOT_STARTED(
            "Press a button to start!"
    ),
    /**
     * The player has pressed the start button.
     */
    PRESSED_START(
            ""
    ),
    /**
     * The minigame is running.
     */
    RUNNING(
            ""
    ),
    VALID_PRESS(
            ""
    ),
    /**
     * The player has made a mistake.
     */
    INVALID_PRESS(
            "Ah! You made a mistake!"
    ),
    /**
     * The player has reached the required amount for do a rep.
     */
    REP_REACHED(
            "You made a rep!"
    ),
    /**
     * The minigame has ended and the player has won.
     */
    ENDED_WON(
            "YOU WON!"
    ),
    /**
     * The minigame has ended and the player has lost.
     */
    ENDED_LOST(
            "YOU LOST!"
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

    public MinigameState next(){
        return values()[(this.ordinal() + 1) % values().length];
    }
}