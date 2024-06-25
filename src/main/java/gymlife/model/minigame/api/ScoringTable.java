package gymlife.model.minigame.api;

import gymlife.utility.minigame.MinigameDifficulty;

import java.util.List;

/**
 * Represents a scoring table that stores the scores of the players.
 */
public interface ScoringTable {
    /**
     * Updates the score of the player in the scoring table.
     * Adds the score to the list of scores of the given difficulty.
     * If the list of scores is greater than 5, removes the minimum score from the list.
     *
     * @param difficulty the difficulty of the minigame that has been played
     * @param score      the score of the player
     */
    void updateScore(MinigameDifficulty difficulty, int score);

    /**
     * Returns the list of scores of the given difficulty.
     *
     * @param difficulty the difficulty of the minigame
     * @return the list of scores
     */
    List<Integer> getScore(MinigameDifficulty difficulty);
}
