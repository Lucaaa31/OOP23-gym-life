package gymlife.utility.minigame;

/**
 * The MinigameDifficulty enum represents the difficulty levels for a minigame.
 */
public enum MinigameDifficulty {
    /**
     * Easy difficulty level.
     */
    EASY(
            1,
            1,
            5000,
            3,
            3,
            34),
    /**
     * Medium difficulty level.
     */
    MEDIUM(
            3,
            3,
            3000,
            5,
            1,
            20),
    /**
     * Hard difficulty level.
     */
    HARD(
            7,
            5,
            1500,
            10,
            0,
            10);

    private final int touchForLift;
    private final int requiredReps;
    private final int experienceGained;
    private final int reactionTime;
    private final int maxMistakes;
    private final int progress;

    /**
     * Constructs a MinigameDifficulty enum constant with the specified parameters.
     *
     * @param requiredReps     the number of required repetitions
     * @param experienceGained the statistics gained from the minigame
     * @param reactionTime     the time within the has to react
     * @param touchForLift     the number of interactions required to lift the weight
     * @param maxMistakes      the maximum number of mistakes allowed
     * @param progress         the progress of the progressBar
     */
    MinigameDifficulty(final int requiredReps, final int experienceGained, final int reactionTime,
                       final int touchForLift, final int maxMistakes, final int progress) {
        this.touchForLift = touchForLift;
        this.requiredReps = requiredReps;
        this.experienceGained = experienceGained;
        this.reactionTime = reactionTime;
        this.maxMistakes = maxMistakes;
        this.progress = progress;
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
    public int getExperienceGained() {
        return experienceGained;
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
    public int getTouchForLift() {
        return touchForLift;
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
     * Returns the progress for the minigame difficulty.
     *
     * @return the progress
     */
    public int getProgress() {
        return progress;
    }
}
