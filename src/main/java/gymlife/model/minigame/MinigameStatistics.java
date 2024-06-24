package gymlife.model.minigame;

import java.util.concurrent.TimeUnit;

/**
 * Class that represents the statistics of a minigame.
 *
 * @param interactions      the number of interactions
 * @param mistakes          the number of mistakes
 * @param numReps           the number of repetitions
 * @param startMinigame     the start time of the minigame
 * @param minigameTime      the time taken to complete the minigame
 * @param startReactionTime the start time of the reaction time
 */
public record MinigameStatistics(int interactions,
                                 int mistakes,
                                 int numReps,
                                 long startMinigame,
                                 long minigameTime,
                                 long startReactionTime) {

    /**
     * Constructor for the MinigameStatistics class.
     */
    public MinigameStatistics() {
        this(0, 0, 0, 0, 0, 0);
    }

    /**
     * Increments the number of interactions.
     *
     * @return the updated MinigameStatistics
     */
    public MinigameStatistics incrementInteractions() {
        return new MinigameStatistics(interactions + 1, mistakes, numReps, startMinigame, minigameTime, startReactionTime);
    }

    /**
     * Increments the number of mistakes.
     *
     * @return the updated MinigameStatistics
     */
    public MinigameStatistics incrementMistakes() {
        return new MinigameStatistics(interactions, mistakes + 1, numReps, startMinigame, minigameTime, startReactionTime);
    }

    /**
     * Increments the number of repetitions.
     *
     * @return the updated MinigameStatistics
     */
    public MinigameStatistics incrementNumReps() {
        return new MinigameStatistics(interactions, mistakes, numReps + 1, startMinigame, minigameTime, startReactionTime);
    }

    /**
     * Sets the start time of the minigame.
     *
     * @return the updated MinigameStatistics
     */
    public MinigameStatistics startMinigameTime() {
        return new MinigameStatistics(interactions, mistakes, numReps, System.nanoTime(), minigameTime, startReactionTime);
    }

    /**
     * Sets the minigame time.
     *
     * @return the updated MinigameStatistics
     */
    public MinigameStatistics calculateMinigameTime() {
        return new MinigameStatistics(interactions, mistakes, numReps, startMinigame,
                TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startMinigame), startReactionTime);
    }

    /**
     * Resets the reaction time.
     *
     * @return the updated MinigameStatistics
     */
    public MinigameStatistics resetReactionTime() {
        return new MinigameStatistics(interactions, mistakes, numReps, startMinigame, minigameTime, System.nanoTime());
    }

    /**
     * Resets the number of interactions.
     *
     * @return the updated MinigameStatistics
     */
    public MinigameStatistics resetInteractions() {
        return new MinigameStatistics(0, mistakes, numReps, startMinigame, minigameTime, startReactionTime);
    }

}
