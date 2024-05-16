package gymlife.model.minigame;

import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * The ScoringTableManager class represents a manager for scoring tables.
 * It contains a map of scoring tables for each minigame type.
 */
public class ScoringTableManager {
    private final Map<MinigameType, ScoringTable> scoringTables;

    /**
     * Constructs a ScoringTableManager object.
     * Initializes the scoring tables map with a scoring table for each minigame type.
     */
    public ScoringTableManager() {
        scoringTables = new EnumMap<>(MinigameType.class);
        scoringTables.put(MinigameType.SQUAT, new ScoringTable());
        scoringTables.put(MinigameType.BENCH_PRESS, new ScoringTable());
        scoringTables.put(MinigameType.LAT_MACHINE, new ScoringTable());
    }

    /**
     * Updates the score of a minigame in the scoring table.
     *
     * @param minigameType the type of the minigame
     * @param difficulty   the difficulty of the minigame
     * @param score        the score to update
     */
    public void updateMinigameScore(final MinigameType minigameType, final MinigameDifficulty difficulty, final long score) {
        System.out.println("Updating score for " + minigameType + " with difficulty " + difficulty + " and score " + score);
        scoringTables.get(minigameType).updateScore(difficulty, (int)score);
    }

    public List<Integer> getScores(final MinigameType minigameType, final MinigameDifficulty difficulty) {
        return scoringTables.get(minigameType).getScores(difficulty);
    }
}
