package gymlife.model.minigame;

import gymlife.utility.minigame.MinigameDifficulty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoringTable {
    private final Map<MinigameDifficulty, List<Integer>> scores;

    public ScoringTable() {
        scores = new HashMap<>();
        for (MinigameDifficulty difficulty : MinigameDifficulty.values()) {
            scores.put(difficulty, null);
        }
    }

    public void updateScore(final MinigameDifficulty difficulty, final int score) {
        scores.get(difficulty).add(score);
        if (scores.get(difficulty).size() > 5){
            scores.get(difficulty).remove(findMinimun(difficulty));
        }
    }

    public int findMinimun(final MinigameDifficulty difficulty) {
        int min = scores.get(difficulty).get(0);
        for (int numero : scores.get(difficulty)) {
            if (numero < min) {
                min = numero;
            }
        }
        return min;
    }

    public String toString(final int index) {
        //return String.format("%3d:%02d", scores.get(index) / 60, scores.get(index) % 60);
        return null;
    }

    public int getSize(){
        return scores.size();
    }
}
