package gymlife.model.minigame;

import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameType;

import java.util.EnumMap;
import java.util.Map;

public class ScoringTableManager {
    private final Map<MinigameType, ScoringTable> scoringTables;

    public ScoringTableManager() {
        scoringTables = new EnumMap<>(MinigameType.class);
        scoringTables.put(MinigameType.SQUAT, new ScoringTable());
        scoringTables.put(MinigameType.BENCH_PRESS, new ScoringTable());
        scoringTables.put(MinigameType.LAT_MACHINE, new ScoringTable());
    }

    public void updateMinigameScore(final MinigameType minigameType, final MinigameDifficulty difficulty, final int score) {
        ScoringTable table = scoringTables.get(minigameType);
        if (table != null) {
            table.updateScore(difficulty, score);
        } else {
            throw new IllegalArgumentException("Unsupported minigame type: " + minigameType);
        }
    }
}
