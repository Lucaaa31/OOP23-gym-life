package gymlife.model.statistics;

/**
 * The GameDifficulty enum represents the difficulty levels of a game.
 */
public enum GameDifficulty {
    /** 
     * Easy difficulty level.
     * The player has 40 days to complete the game.
    */
    EASY(40),
    /** 
     * Medium difficulty level.
     * The player has 30 days to complete the game.
    */
    MEDIUM(30),
    /** 
     * Hard difficulty level.
     * The player has 20 days to complete the game.
    */
    HARD(5);

    private final int count;

    /**
    * Constructs a GameDifficulty with the specified count of days.
    * @param count the number of days for the difficulty level
    */
    GameDifficulty(final int count) {
        this.count = count;
    }
    /**
    * Returns the number of days for the difficulty level.
    * @return the number of days
    */
    public int getDays() {
        return count;
    }
}
