package gymlife.model.Minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.MinigameDifficulty;
import gymlife.utility.MinigameType;

public class BenchMinigame implements Minigame, Runnable {
    private MinigameDifficulty difficulty;
    private Thread timerThread;
    private boolean isPressed;
    private int nTimesPressed;
    private int state;
    private int numReps;


    public BenchMinigame() {
        this.isPressed = false;
        this.nTimesPressed = 0;
        this.state = 0;
        this.numReps = 0;
    }


    @Override
    public void notifyButtonPressed() {
        this.isPressed = true;
    }

    @Override
    public void setTimer(Timer timer) {
        timerThread = new Thread(timer);
        timer.setRunningTime(difficulty.getReactionTime());
    }

    @Override
    public void run() {
        timerThread.start();
        while(timerThread.isAlive() && numReps < difficulty.getRequiredReps() ){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {}
            if (isPressed) {
                nTimesPressed++;
                System.out.println("Pressed");
                isPressed = false;
                if (nTimesPressed == difficulty.getnRepsForSwitchState()) {
                    System.out.println("Switch state");
                    state++;
                    if (state == 4) {
                        numReps++;
                        state=0;
                    }
                    nTimesPressed = 0;
                }
            }
        }
    }

    public int getState() {
        return state;
    }

    @Override
    public void setDifficulty(MinigameDifficulty selectedDifficulty) {
        this.difficulty = selectedDifficulty;
    }


}