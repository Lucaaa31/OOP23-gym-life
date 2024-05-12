package gymlife.utility;

/**
 * The MinigameDifficulty enum represents the difficulty levels for a minigame.
 */
public enum MinigameDifficulty {
    /**
     * Easy difficulty level.
     */
    EASY(
            2,
            3,
            20000,
            1),
    /**
     * Medium difficulty level.
     */
    MEDIUM(
            3,
            1,
            20000,
            5),
    /**
     * Hard difficulty level.
     */
    HARD(
            5,
            0,
            20000,
            10);

    private final int nRepsForSwitchState;
    private final int requiredReps;
    private final int maxMistakes;
    private final int reactionTime;

    /**
     * Constructs a MinigameDifficulty enum constant with the specified parameters.
     *
     * @param requiredReps        the number of required repetitions
     * @param maxMistakes         the maximum number of mistakes allowed
     * @param reactionTime        the reaction time in milliseconds
     * @param nRepsForSwitchState the number of repetitions required to switch state
     */
    MinigameDifficulty(final int requiredReps, final int maxMistakes, final int reactionTime,
            final int nRepsForSwitchState) {
        this.nRepsForSwitchState = nRepsForSwitchState;
        this.requiredReps = requiredReps;
        this.maxMistakes = maxMistakes;
        this.reactionTime = reactionTime;
    }

    /**
     * Returns the number of required repetitions for the minigame difficulty.
     *
     * @return the number of required repetitions
     */
    public int getRequiredReps() {
        return requiredReps;
    }

    /**
     * Returns the maximum number of mistakes allowed for the minigame difficulty.
     *
     * @return the maximum number of mistakes allowed
     */
    public int getMaxMistakes() {
        return maxMistakes;
    }

    /**
     * Returns the reaction time in milliseconds for the minigame difficulty.
     *
     * @return the reaction time in milliseconds
     */
    public int getReactionTime() {
        return reactionTime;
    }

    /**
     * Returns the number of repetitions required to switch state for the minigame
     * difficulty.
     *
     * @return the number of repetitions required to switch state
     */
    public int getnRepsForSwitchState() {
        return nRepsForSwitchState;
    }
}
