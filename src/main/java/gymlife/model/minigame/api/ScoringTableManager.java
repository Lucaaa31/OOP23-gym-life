package gymlife.model.minigame.api;

import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameType;

import java.util.List;

public interface ScoringTableManager {
    /**
     * Updates the score of a minigame in the scoring table.
     *
     * @param minigameType the type of the minigame
     * @param difficulty   the difficulty of the minigame
     * @param score        the score to update
     */
    void updateMinigameScore(MinigameType minigameType,
                             MinigameDifficulty difficulty,
                             long score);

    /**
     * Returns the scores of a minigame.
     *
     * @param minigameType the type of the minigame
     * @param difficulty   the difficulty of the minigame
     * @return the scores of the minigame
     */
    List<Integer> getMinigameScore(MinigameType minigameType, MinigameDifficulty difficulty);
}
