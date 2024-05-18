package gymlife.utility.minigame;

public enum MinigameState {
    NOT_STARTED(
            "The minigame has not started yet."
    ),
    RUNNING(
            "The minigame is running."
    ),
    ENDED_WON(
            "The minigame has ended and the player has won."
    ),
    ENDED_LOST(
            "The minigame has ended and the player has lost."
    );


    private final String text;

    MinigameState (final String text) {
        this.text = text;
    }


    public String getText() {
        return text;
    }
}
