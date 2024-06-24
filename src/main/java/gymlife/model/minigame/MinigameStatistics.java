package gymlife.model.minigame;

import java.util.concurrent.TimeUnit;


public class MinigameStatistics {
    private int interactions;
    private int mistakes;
    private int numReps;
    private long startMinigame;
    private long minigameTime;
    private long startReactionTime;


    public void incrementInteractions() {
        interactions++;
    }

    public void incrementMistakes() {
        mistakes++;
    }

    public void incrementNumReps() {
        numReps++;
    }

    public void setStartMinigame() {
        startMinigame = System.nanoTime();
    }

    public void setMinigameTime() {
        minigameTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startMinigame);
    }

    public void resetReactionTime() {
        startReactionTime = System.nanoTime();
    }


    public long getStartReactionTime() {
        return startReactionTime;
    }

    public int getInteractions() {
        return interactions;
    }

    public void resetInteractions() {
        this.interactions = 0;
    }

    public int getMistakes() {
        return mistakes;
    }

    public int getNumReps() {
        return numReps;
    }

    public int getMinigameTime() {
        return Math.toIntExact(minigameTime);
    }


}
