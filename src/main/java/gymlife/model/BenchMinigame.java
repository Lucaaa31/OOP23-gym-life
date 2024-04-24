package gymlife.model;

import gymlife.model.api.Minigame;
import gymlife.utility.MinigameDifficulty;

import java.util.HashSet;
import java.util.Set;

public class BenchMinigame implements Minigame {
    private int nReps;
    private int nMistakes;
    private TimerImpl reactionTime;
    private Set<Character> keys = new HashSet<>();;


    public BenchMinigame(final MinigameDifficulty difficulty){

        switch (difficulty) {
            case EASY:
                this.nReps = 5;
                this.nMistakes = 3;
                this.reactionTime = new TimerImpl(10000);
                this.keys.addAll(Set.of('Q', 'W', 'E', 'R', 'T', 'Y'));
                break;
            case MEDIUM:
                this.nReps = 10;
                this.nMistakes = 1;
                this.reactionTime = new TimerImpl(5000);
                this.keys.addAll(Set.of('Q', 'W', 'E', 'R', 'T', 'Y', 'A', 'S', 'D', 'F', 'Z', 'X', 'C'));
                break;
            case HARD:
                this.nReps = 15;
                this.nMistakes = 0;
                this.reactionTime = new TimerImpl(3000);
                this.keys.addAll(Set.of('Q', 'W', 'E', 'R', 'T', 'Y',
                        'A', 'S', 'D', 'F', 'Z', 'X', 'C', 'U', 'I',
                        'O', 'P', 'G', 'H', 'J', 'K', 'L', 'B', 'N', 'M'
                ));
                break;
            default:
                break;
        }

    }

    public void start() {

    }

    

}
