package gymlife.model.Minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.MinigameDifficulty;

public class BenchMinigame implements Minigame, Runnable {
    private MinigameDifficulty difficulty = MinigameDifficulty.EASY;
    private Thread timerThread;
    private boolean isPressed;
    private int nTimesPressed;
    private int state;


    public BenchMinigame(MinigameDifficulty difficulty) {
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
        for (int numReps = 0; numReps < difficulty.getRequiredReps(); numReps++) {
            //timerThread.start();
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
                if (isPressed) {
                    nTimesPressed++;
                    System.out.println("Pressed");
                    isPressed = false;
                    if (nTimesPressed == difficulty.getnRepsForSwitchState()) {
                        System.out.println("Switch state");
                        state++;
                        nTimesPressed = 0;

                        break;
                    }
                }
            }
        }
    }

    public int getState() {
        return state;
    }
}