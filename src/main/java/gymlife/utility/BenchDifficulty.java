package gymlife.utility;

import java.util.Set;

public enum BenchDifficulty {
    EASY(
        10000,
        10,
        Set.of("Q", "W", "E", "R", "T", "Y"),
        3
    ),
    MEDIUM(
        5000,
        15,
        Set.of("Q", "W", "E", "R", "T", "Y", "A", "S", "D", "F", "Z", "X", "C"),
        2
    ),
    HARD(
        3000,
        20,
        Set.of("Q", "W", "E", "R", "T", "Y", "A", "S", "D", "F", "Z", "X", "C", "U", "I", "O", "P", "H", "J", "K", "L"),
        0
    );

    private final int timer;
    public int getTimer() {
        return timer;
    }

    private final int nReps;
    public int getnReps() {
        return nReps;
    }

    private final Set<String> keys;
    public Set<String> getKeys() {
        return keys;
    }

    private final int nMistakes;

    public int getnMistakes() {
        return nMistakes;
    }

    private BenchDifficulty(final int timer,final int nReps, Set<String> keys, final int nMistakes) {
        this.timer = timer;
        this.nReps = nReps;
        this.keys = keys;
        this.nMistakes = nMistakes;
    }

    


}
