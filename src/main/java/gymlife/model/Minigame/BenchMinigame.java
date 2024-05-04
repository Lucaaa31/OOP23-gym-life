package gymlife.model;

import gymlife.model.api.Minigame;
import gymlife.utility.MinigameDifficulty;

public class BenchMinigame implements Minigame {
    private int counterReps;
    private int counterMistakes;
    private boolean keyPressed;
    private MinigameDifficulty difficulty;



    public BenchMinigame(final MinigameDifficulty difficulty){
        this.difficulty = difficulty;
        start();
    }

    public void start() {

    }


    //metodo astratto (?)
    public void notifyTimerExpired() {
        counterMistakes++;
        if (counterMistakes > difficulty.getMaxMistakes()) {
            System.out.println("Game Over");
        }
    }


}
