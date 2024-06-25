package gymlife.model.minigame;

import gymlife.model.minigame.api.ScoringTable;
import gymlife.model.minigame.api.ScoringTableManager;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * The ScoringTableManager class represents a manager for scoring tables.
 * It contains a map of scoring tables for each minigame type.
 */
public class ScoringTableManagerImpl implements ScoringTableManager {
    private final Map<MinigameType, ScoringTable> scoringTables;

    /**
     * Constructs a ScoringTableManager object.
     * Initializes the scoring tables map with a scoring table for each minigame type.
     */
    public ScoringTableManagerImpl() {
        scoringTables = new EnumMap<>(MinigameType.class);
        scoringTables.put(MinigameType.SQUAT, new ScoringTableImpl());
        scoringTables.put(MinigameType.BENCH_PRESS, new ScoringTableImpl());
        scoringTables.put(MinigameType.LAT_MACHINE, new ScoringTableImpl());
    }

    /**
     * Updates the score of a minigame in the scoring table.
     *
     * @param minigameType the type of the minigame
     * @param difficulty   the difficulty of the minigame
     * @param score        the score to update
     */
    @Override
    public void updateMinigameScore(final MinigameType minigameType,
                                    final MinigameDifficulty difficulty,
                                    final long score) {
        scoringTables.get(minigameType).updateScore(difficulty, (int) score);
    }

    /**
     * Returns the scores of a minigame.
     *
     * @param minigameType the type of the minigame
     * @param difficulty   the difficulty of the minigame
     * @return the scores of the minigame
     */
    @Override
    public List<Integer> getMinigameScore(final MinigameType minigameType, final MinigameDifficulty difficulty) {
        if (!scoringTables.containsKey(minigameType)) {
            return List.of();
        }
        return scoringTables.get(minigameType).getScore(difficulty);
    }
}
