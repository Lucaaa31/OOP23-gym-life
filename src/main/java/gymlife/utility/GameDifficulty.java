package gymlife.utility;

public enum GameDifficulty {
    /** 
     * Direction UP.
    */
    EASY(40),
    /** 
     * Direction RIGHT.
    */
    MEDIUM(30),
    /** 
     * Direction Left.
    */
    HARD(20);

    private final int count;

    /**
    *  Private constructor.
    * @param key
    * @param pos
    */
    GameDifficulty(final int count) {
        this.count = count;
    }
    /**
    * From GameDifficulty return Optional<Integer>.
    * @param difficulty
    * @return Optional<Directions>
    */
    public int getDays() {
        return count;
    }
}
