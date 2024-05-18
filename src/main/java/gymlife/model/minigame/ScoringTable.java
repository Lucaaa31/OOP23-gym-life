package gymlife.model.minigame;

import gymlife.utility.minigame.MinigameDifficulty;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The ScoringTable class represents the scoring table of a minigame.
 * It shows the scores of the players in the minigame, and updates the scores when a player finishes the minigame.
 * It keeps track of the top 5 scores of the players in all the three difficulties.
 */
public class ScoringTable {
    private Map<MinigameDifficulty, List<Integer>> scores;

    /**
     * Constructs a new ScoringTable object.
     * Initializes the scores map with the three difficulties and sets the scores to null.
     */
    public ScoringTable() {
        scores = new HashMap<>();
        for (MinigameDifficulty difficulty : MinigameDifficulty.values()) {
            scores.put(difficulty, null);
        }
        scores.put(MinigameDifficulty.EASY, List.of(20000, 10000, 40000));
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
        List<Integer> tmpList = new LinkedList<>(scores.get(difficulty));
        tmpList.add(score);
        if (tmpList.size() > 5) {
            scores.get(difficulty).remove(findMax(difficulty));
        }
        scores.remove(difficulty, scores.get(difficulty));
        scores.put(difficulty, tmpList);
    }

    /**
     * Finds the minimum score in the list of scores of the given difficulty.
     *
     * @param difficulty the difficulty of the minigame
     * @return the minimum score in the list of scores
     */
    public int findMax(final MinigameDifficulty difficulty) {
        int max = scores.get(difficulty).get(0);
        for (int numero : scores.get(difficulty)) {
            if (numero > max) {
                max = numero;
            }
        }
        return max;
    }

    /**
     * Returns the list of scores of the given difficulty.
     *
     * @param difficulty the difficulty of the minigame
     * @return the list of scores
     */
    public List<Integer> getScores(final MinigameDifficulty difficulty) {
        if (scores.get(difficulty) == null) {
            return null;
        }
        return scores.get(difficulty).stream()
                .sorted()
                .toList();
    }

    /**
     * Returns the size of the list of scores of the given difficulty.
     *
     * @param difficulty the difficulty of the minigame
     * @return the size of the list of scores
     */
    public int getSize(final MinigameDifficulty difficulty) {
        return scores.get(difficulty).size();
    }
}
