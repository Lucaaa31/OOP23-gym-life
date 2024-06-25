package gymlife.model.minigame;

import gymlife.utility.minigame.MinigameDifficulty;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The ScoringTable class represents the scoring table of a minigame.
 * It shows the scores of the players in the minigame, and updates the scores when a player finishes the minigame.
 * It keeps track of the top 5 scores of the players in all the three difficulties.
 */
public class ScoringTable {
    private final Map<MinigameDifficulty, List<Integer>> scores;

    /**
     * Constructs a new ScoringTable object.
     * Initializes the scores map with the three difficulties and sets the scores to null.
     */
    public ScoringTable() {
        scores = new HashMap<>();
        for (final MinigameDifficulty difficulty : MinigameDifficulty.values()) {
            scores.put(difficulty, null);
        }
    }

    /**
     * Updates the score of the player in the scoring table.
     * Adds the score to the list of scores of the given difficulty.
     * If the list of scores is greater than 5, removes the minimum score from the list.
     *
     * @param difficulty the difficulty of the minigame that has been played
     * @param score      the score of the player
     */
    public void updateScore(final MinigameDifficulty difficulty, final int score) {
        final int maxScores = 5;
        List<Integer> tmpScores = scores.get(difficulty);
        if (tmpScores == null) {
            tmpScores = new LinkedList<>();
        }
        tmpScores.add(score);

        final List<Integer> updatedScores = tmpScores.stream()
                .sorted()
                .limit(maxScores)
                .collect(Collectors.toList());
        scores.remove(difficulty);
        scores.put(difficulty, updatedScores);
    }

    /**
     * Returns the list of scores of the given difficulty.
     *
     * @param difficulty the difficulty of the minigame
     * @return the list of scores
     */
    public List<Integer> getScore(final MinigameDifficulty difficulty) {
        if (scores.get(difficulty) == null) {
            return List.of();
        }
        return scores.get(difficulty).stream()
                .sorted()
                .toList();
    }

}
