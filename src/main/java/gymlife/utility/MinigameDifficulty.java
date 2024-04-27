package gymlife.utility;

import java.util.HashSet;
import java.util.Set;

public enum MinigameDifficulty {
    EASY(
            2,
            3,
            7000,
            new HashSet<Character>(Set.of('Q', 'W', 'E', 'R', 'T', 'Y'))
    ),
    MEDIUM(
            3,
            1,
            5000,
            new HashSet<Character>(Set.of('Q', 'W', 'E', 'R', 'T', 'Y',
                    'A', 'S', 'D', 'F', 'Z', 'X', 'C'))
    ),
    HARD(
            5,
            0,
            3000,
            new HashSet<Character>(Set.of('Q', 'W', 'E', 'R', 'T', 'Y',
                    'A', 'S', 'D', 'F', 'Z', 'X', 'C', 'U', 'I',
                    'O', 'P', 'G', 'H', 'J', 'K', 'L', 'B', 'N', 'M'))
    );

    private Set<Character> keys;
    private int nReps;
    private int nMistakes;
    private int reactionTime;

    MinigameDifficulty(final int nReps, final int nMistakes, final int reactionTime, final Set<Character> keys) {
        this.keys = keys;
        this.nReps = nReps;
        this.nMistakes = nMistakes;
        this.reactionTime = reactionTime;
    }

    public Set<Character> getKeys() {
        return keys;
    }

    public int getnReps() {
        return nReps;
    }

    public int getnMistakes() {
        return nMistakes;
    }

    public int getReactionTime() {
        return reactionTime;
    }
}
