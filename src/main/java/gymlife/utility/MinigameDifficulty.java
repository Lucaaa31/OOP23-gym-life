package gymlife.utility;

public enum MinigameDifficulty {
    EASY(
            2,
            3,
            7000,
            1
    ),
    MEDIUM(
            3,
            1,
            5000,
            5
    ),
    HARD(
            5,
            0,
            3000,
            10
    );

    private final int nRepsForSwitchState;
    private final int requiredReps;
    private final int maxMistakes;
    private final int reactionTime;

    MinigameDifficulty(final int requiredReps, final int maxMistakes, final int reactionTime, final int nRepsForSwitchState) {
        this.nRepsForSwitchState = nRepsForSwitchState;
        this.requiredReps = requiredReps;
        this.maxMistakes = maxMistakes;
        this.reactionTime = reactionTime;
    }



    public int getRequiredReps() {
        return requiredReps;
    }

    public int getMaxMistakes() {
        return maxMistakes;
    }

    public int getReactionTime() {
        return reactionTime;
    }

    public int getnRepsForSwitchState() {
        return nRepsForSwitchState;
    }
}
